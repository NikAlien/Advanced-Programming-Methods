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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;


public class Controller {
    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

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

    public PrgState oneStep(PrgState state) throws MyException{
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty()) throw new MyException("progState stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state);
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();

        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            List<Integer> symAddr = getSymAddr(prg.getSymTable().getContent().values());
            List<Integer> heapAddr = getHeapAddr(prg.getHeapMemory().getContent(), symAddr);
            prg.getHeapMemory().setContent(GarbageCollector(
                    symAddr, heapAddr, prg.getHeapMemory().getContent()));
            repo.logPrgStateExec();
        }
    }

}
