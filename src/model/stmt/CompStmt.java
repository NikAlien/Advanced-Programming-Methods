package model.stmt;
import controller.MyException;
import model.PrgState;
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
}
