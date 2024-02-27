package model.stmt;

import controller.MyException;
import model.PrgState;
import model.type.Type;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIStack;
import utils.MyStack;

public class ForkStmt implements IStmt{
    IStmt stmtToDo;
    public ForkStmt(IStmt stmt){
        this.stmtToDo = stmt;
    }

    @Override
    public String toString() {
        return "ForkStmt{ " + stmtToDo + " }";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        PrgState childPrg = new PrgState(new MyStack<>(), symTbl.deepCopy(), state.getOut(), state.getFileTable(), state.getHeapMemory(), stmtToDo);
        return childPrg;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmtToDo.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }
}
