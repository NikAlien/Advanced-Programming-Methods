package model.expr;

import controller.MyException;
import model.type.Type;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public class VarLookUpExp implements Expression {
    private String id;

    public VarLookUpExp(String id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "Look up " + id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
}
