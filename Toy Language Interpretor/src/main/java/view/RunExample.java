package view;
import controller.Controller;
import controller.MyException;
import model.PrgState;
import model.stmt.IStmt;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;
import repository.IRepository;
import repository.Repository;
import utils.*;

import java.io.BufferedReader;

public class RunExample extends Command{
    private IStmt prog;
    private Controller controller;
    public RunExample(String key, String desc, IStmt p){
        super(key, desc);
        this.prog = p;

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap<Integer, Value> heap= new MyHeap<>();

        PrgState program = new PrgState(exeStack, symTable, output, fileTable, heap, prog);
        IRepository repository = new Repository("PrgStateOut.txt");
        repository.add(program);
        controller = new Controller(repository);
    }

    @Override
    public String toString() {
        return prog.toString();
    }

    public Controller getController() {
        return controller;
    }

    public IStmt getProg() {
        return prog;
    }

    @Override
    public void execute() throws MyException {
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new MyException(e.getMessage());
        }
    }
}
