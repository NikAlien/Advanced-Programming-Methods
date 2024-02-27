package model.stmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.type.RefType;
import model.value.RefValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public class WriteHeapStmt implements IStmt{
    private Expression exp;
    private String varName;
    public WriteHeapStmt(String vn, Expression ep){
        this.varName = vn;
        this.exp = ep;
    }

    @Override
    public String toString() {
        return "Write into heap: " + varName + " -> " + exp.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> hp = state.getHeapMemory();

        if(tbl.isDefined(varName))
        {
            Value val = tbl.lookup(varName);
            if(val.getType() instanceof RefType)
            {
                RefValue refVal = (RefValue) val;
                int key = refVal.getAddress();
                if(hp.isDefined(key))
                {
                    Value expVal = exp.eval(tbl, hp);
                    RefType refType = (RefType) refVal.getType();
                    if(expVal.getType().equals(refType.getInnerComponent()))
                    {
                        hp.update(key, expVal);
                        return null;
                    }
                    else
                        throw new MyException("The inner type is not compatible with the change");
                }
                else
                    throw new MyException("Key is not defined in heap table");
            }
            else
                throw new MyException("Variable is not reference");
        }
        else
            throw new MyException("Variable name not defined in symbol table");
    }
}
