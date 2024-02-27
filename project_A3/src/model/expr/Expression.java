package model.expr;

import controller.MyException;
import model.value.Value;
import utils.MyIDictionary;

public interface Expression {

    Value eval(MyIDictionary<String, Value> tbl) throws MyException;
}
