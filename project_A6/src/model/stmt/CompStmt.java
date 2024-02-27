package model.stmt;
import controller.MyException;
import model.PrgState;
import model.type.Type;
import utils.MyIDictionary;
import utils.MyIStack;

public class CompStmt implements IStmt {

    private IStmt head;
    private IStmt tail;

    public CompStmt(IStmt head, IStmt tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public String toString() {
        return " ("+head.toString()+"; "+tail.toString()+") ";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        stk.push(tail);
        stk.push(head);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return tail.typeCheck(head.typeCheck(typeEnv));
    }
}
