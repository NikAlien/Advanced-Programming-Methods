package model.stmt;

import controller.MyException;
import model.PrgState;
import model.type.Type;
import model.value.Value;
import utils.MyIDictionary;

public class VarDeclStmt implements IStmt{
    private String symbolName;
    private Type type;

    public VarDeclStmt(String symbolName, Type type) {
        this.symbolName = symbolName;
        this.type = type;
    }

    @Override
    public String toString() {
        return symbolName + " is " + type.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        symTbl.put(symbolName,type.defaultValue());
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.put(symbolName, type);
        return typeEnv;
    }
}
