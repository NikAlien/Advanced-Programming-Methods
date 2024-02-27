package view;
import controller.Controller;
import controller.MyException;
import model.PrgState;
import model.stmt.IStmt;
import model.value.StringValue;
import model.value.Value;
import repository.IRepository;
import repository.Repository;
import utils.*;

import java.io.BufferedReader;

public class RunExample extends Command{
    private IStmt prog;
    public RunExample(String key, String desc, IStmt p){
        super(key, desc);
        this.prog = p;
    }
    @Override
    public void execute() throws MyException {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap<Integer, Value> heap= new MyHeap<>();

        PrgState program = new PrgState(exeStack, symTable, output, fileTable, heap, prog);

        IRepository repository = new Repository("PrgStateOut.txt");
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }
    }
}
