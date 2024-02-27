package model.stmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.Value;
import utils.MyIStack;

public class WhileStmt implements IStmt{
    private Expression condition;
    private IStmt progStmt;
    public WhileStmt(Expression exp, IStmt stmt){
        this.condition = exp;
        this.progStmt = stmt;
    }

    @Override
    public String toString() {
        return "While (" + condition.toString() + ") do " + progStmt.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Value val = condition.eval(state.getSymTable(), state.getHeapMemory());
        if(val.getType().equals(new BoolType()))
        {
            BoolValue cond = (BoolValue) val;
            if(!cond.getVal())
                return state;
            else {
                MyIStack<IStmt> stack = state.getExeStack();
                stack.push(new WhileStmt(condition, progStmt));
                stack.push(progStmt);
                return state;
            }
        }
        else
            throw new MyException("Condition in while is not boolean");
    }
}
