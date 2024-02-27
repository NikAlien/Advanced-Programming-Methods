package model.stmt.fileStmt;
import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.stmt.IStmt;
import model.type.StringType;
import model.value.StringValue;
import model.value.Value;
import utils.MyIDictionary;

import java.io.*;

public class OpenReadFile implements IStmt {
    private Expression exp;
    public OpenReadFile(Expression e){
        exp = e;
    }

    @Override
    public String toString() {
        return "Opened Read File: " + exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();
        Value val = exp.eval(symTbl, state.getHeapMemory());
        if(val.getType().equals(new StringType())){
            if(!fileTbl.isDefined((StringValue) val)){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(((StringValue) val).getVal()));
                    fileTbl.put((StringValue) val, br);
                }
                catch (IOException e){
                    throw new MyException("Problems with opening the file, it might not exist");
                }
            }
            else
                throw new MyException("This file is already open");
        }
        else
            throw new MyException("File name must be a string");

        return null;
    }
}
