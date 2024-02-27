package model.expr;

import controller.MyException;
import model.type.RefType;
import model.type.Type;
import model.value.RefValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public class ReadHeapExp implements Expression{
    private Expression exp;
    public ReadHeapExp(Expression ep){
        this.exp = ep;
    }

    @Override
    public String toString() {
        return "Read from heap: " + exp.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value val = exp.eval(tbl, hp);
        if(val instanceof RefValue){
            RefValue refVal = (RefValue) val;
            int addr = refVal.getAddress();
            if(hp.isDefined(addr))
                return hp.lookup(addr);
            else throw new MyException("The address is not valid in the heap");
        }
        else {
            throw new MyException("Given expression is not a RefType");
        }
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type = exp.typeCheck(typeEnv);
        if(type instanceof RefType){
            RefType reftype = (RefType) type;
            return reftype.getInnerComponent();
        } else
            throw new MyException("The readHeap argument is not a ref type");
    }
}
