package model.stmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public class NewStmt implements IStmt{
    private String varName;
    private Expression exp;
    public NewStmt(String var, Expression e){
        this.varName = var;
        this.exp = e;
    }
    @Override
    public String toString() {
        return varName + " ref. for " + exp.toString();
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heapMemo = state.getHeapMemory();

        if(symTbl.isDefined(varName)){
            Type typeId = (symTbl.lookup(varName)).getType();
            if(typeId instanceof RefType){
                Value val = exp.eval(symTbl, heapMemo);
                if(val.getType().equals(((RefType) typeId).getInnerComponent())){
                    int newAddr = heapMemo.nextAvailablePosition();
                    heapMemo.put(newAddr, val);
                    symTbl.update(varName, new RefValue(newAddr, val.getType()));
                }
                else{
                    throw new MyException("Smth went wrong in your declaration");
                }
            }
            else{
                throw new MyException("Not a RefType instance");
            }
        }
        else{
            throw new MyException("Name not defined in symbol table.");
        }
        return state;
    }
}
