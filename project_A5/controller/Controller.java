package controller;

import model.stmt.IStmt;
import model.PrgState;
import model.value.RefValue;
import model.value.Value;
import repository.IRepository;
import utils.MyDictionary;
import utils.MyIDictionary;
import utils.MyIHeap;
import utils.MyIStack;

import java.io.IOException;
import java.sql.Ref;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class Controller {
    // methods that help manage and control the execution of threads
    ExecutorService executor;
    private IRepository repo;
    public Controller(IRepository r) {
        repo = r;
    }

    public Map<Integer, Value> GarbageCollector(List<Integer> symTblAddress, List<Integer> heapTblAddress, Map<Integer, Value> heapM){
        return heapM.entrySet().stream()
                .filter(elem -> symTblAddress.contains(elem.getKey()) || heapTblAddress.contains(elem.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getSymAddr(Collection<Value> symTableValues){
        List<Integer> symTableAdrr = symTableValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {RefValue value1 = (RefValue) value; return value1.getAddress();})
                .collect(Collectors.toList());

        return symTableAdrr;
    }

    List<Integer> getHeapAddr(Map<Integer, Value> heapM, List<Integer> symAddr){
        List<Integer> heapTableAdrr = symAddr.stream()
                .filter(key -> heapM.get(key) instanceof RefValue)
                .map(key -> {RefValue value = (RefValue) heapM.get(key); return value.getAddress();})
                .collect(Collectors.toList());

        return heapTableAdrr;
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream().filter(prg -> prg.isNotCompleted()).collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        // here we basically run the one step on all program states
        // in case we get a fork (create a child thread) it collect it as callable beside the null values
        // Callable interface in Java is used to make a class instance run as a thread by implementing it.
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(p::oneStep))
                .collect(Collectors.toList());

        // Executes all the tasks contained in the collection.
        // The list of Future objects is returned which contains the status and return values of the various tasks
        List<PrgState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {try {return future.get();} // gets the results of the oneStep function
                catch (InterruptedException | ExecutionException ex) {
                        throw new RuntimeException(ex);}})
                .filter(p -> p != null)
                .collect(Collectors.toList());

        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        repo.setPrgState(prgList);
    }

    public void allStep() throws MyException {
        executor = Executors.newFixedThreadPool(2);

        // Remove the completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgState());
        while(!prgList.isEmpty()){

            // Garbage collector works here
            List<Integer> symAddr = new ArrayList<>();
            for(PrgState prg : prgList){
                List<Integer> newList = getSymAddr(prg.getSymTable().getContent().values());
                symAddr.addAll(newList);
            }
            List<Integer> heapAddr = getHeapAddr(prgList.get(0).getHeapMemory().getContent(), symAddr);
            prgList.get(0).getHeapMemory().setContent(GarbageCollector(
                    symAddr, heapAddr, prgList.get(0).getHeapMemory().getContent()));

            // Call the execution of all program states
            try {
                oneStepForAllPrg(prgList);
            }
            catch (InterruptedException e){
                throw new MyException(e.getMessage());
            }

            // Remove the completed programs if any available
            prgList = removeCompletedPrg(repo.getPrgState());
        }
        executor.shutdownNow();
        repo.setPrgState(prgList);
    }

}
