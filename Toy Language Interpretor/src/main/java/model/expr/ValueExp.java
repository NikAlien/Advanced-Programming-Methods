package model.expr;

import controller.MyException;
import model.type.Type;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public class ValueExp implements Expression {
    private Value val;
    public ValueExp(Value value) {
        this.val = value;
    }

    @Override
    public String toString() {
        return val.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        return val;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return val.getType();
    }
}
