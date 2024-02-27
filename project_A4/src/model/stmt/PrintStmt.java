package model.stmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.value.Value;
import utils.MyIList;

public class PrintStmt implements IStmt{
    private Expression exp;
    public PrintStmt(Expression exp){
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() +")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> outStream = state.getOut();
        outStream.add(exp.eval(state.getSymTable(), state.getHeapMemory()));
        return state;
    }
}
