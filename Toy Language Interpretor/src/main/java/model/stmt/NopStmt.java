package model.stmt;

import controller.MyException;
import model.PrgState;
import model.type.Type;
import utils.MyIDictionary;

public class NopStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }
}
