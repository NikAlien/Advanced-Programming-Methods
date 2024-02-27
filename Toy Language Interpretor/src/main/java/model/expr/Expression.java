package model.expr;

import controller.MyException;
import model.type.Type;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public interface Expression {

    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException;
    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
