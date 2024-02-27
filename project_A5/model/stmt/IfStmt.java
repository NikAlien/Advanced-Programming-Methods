package model.stmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.value.BoolValue;
import model.value.IntValue;

public class IfStmt implements IStmt{
    private Expression exp;
    private IStmt thenS;
    private IStmt elseS;
    public IfStmt(Expression e, IStmt t, IStmt el){
        this.exp = e;
        this.thenS = t;
        this.elseS = el;
    }

    @Override
    public String toString() {
        return "( If (" + exp.toString() +" ) Then (" + thenS.toString()
                + ") Else (" + elseS.toString() + "))";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        BoolValue i1 = (BoolValue) exp.eval(state.getSymTable(), state.getHeapMemory());
        Boolean value = i1.getVal();
        if(value){
            thenS.execute(state);
        }
        else
            elseS.execute(state);
        return null;
    }
}
