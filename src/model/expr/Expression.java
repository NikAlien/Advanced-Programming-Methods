package model.expr;

import controller.MyException;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

public interface Expression {

    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException;
}
