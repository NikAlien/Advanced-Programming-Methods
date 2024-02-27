package view;

import controller.Controller;
import controller.MyException;
import model.*;
import model.expr.ArithExp;
import model.stmt.*;
import model.expr.VarLookUpExp;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.Value;
import model.expr.ValueExp;
import model.value.IntValue;
import repository.IRepository;
import repository.Repository;
import utils.*;

public class View {

    private IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                    new PrintStmt(new VarLookUpExp("v"))));

    private IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
            new CompStmt(new VarDeclStmt("b",new IntType()),
            new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                    new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
            new CompStmt(new AssignStmt("b",new ArithExp('+',new VarLookUpExp("a"), new ValueExp(new IntValue(1)))),
                    new PrintStmt(new VarLookUpExp("b"))))));

    private IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
            new CompStmt(new IfStmt(new VarLookUpExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                    new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarLookUpExp("v"))))));

    public View() {
    }

    public void menu() {
        System.out.println("0. Exit");
        System.out.println("1. Example 1 " + ex1);
        System.out.println("2. Example 2 " + ex1); // TODO: ex2 etc...
    }

    private void runEx1() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex1);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    private void runEx2() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex2);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    private void runEx3() {

        MyIStack<IStmt> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();

        PrgState program = new PrgState(exeStack, symTable, output, ex3);

        IRepository repository = new Repository();
        repository.add(program);

        Controller controller = new Controller(repository);
        try {
            controller.allStep();
        } catch (MyException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        View view = new View();
        System.out.println("Exemple 1:\n");
        view.runEx1();
        System.out.println("\nExemple 2:\n");
        view.runEx2();
        System.out.println("\nExemple 3:\n");
        view.runEx3();
    }
}
