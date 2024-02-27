package model.stmt;

import controller.MyException;
import model.PrgState;
import model.type.Type;
import utils.MyIDictionary;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
