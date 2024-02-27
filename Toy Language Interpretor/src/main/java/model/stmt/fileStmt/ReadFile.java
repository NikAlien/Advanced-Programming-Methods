package model.stmt.fileStmt;

import controller.MyException;
import model.PrgState;
import model.expr.Expression;
import model.stmt.IStmt;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;
import model.value.IntValue;
import model.value.StringValue;
import model.value.Value;
import utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Expression exp;
    private String varName;
    public ReadFile(Expression e, String n){
        exp = e;
        varName = n;
    }
    @Override
    public String toString() {
        return "Read from " + exp + " var " + varName ;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();

        if(symTbl.isDefined(varName)){
            Value val = symTbl.lookup(varName);
            if(val.getType().equals(new IntType())){
                Value fileName = exp.eval(symTbl, state.getHeapMemory());
                if(fileName.getType().equals(new StringType())) {
                    try {
                        BufferedReader br = fileTbl.lookup((StringValue) fileName);
                        String line = br.readLine();
                        if(line == null)
                            symTbl.put(varName, new IntType().defaultValue());
                        else
                            symTbl.put(varName, new IntValue(Integer.parseInt(line)));
                    }
                    catch (IOException e){
                        throw new MyException("Reading error");
                    }
                }
                else
                    throw new MyException("File name is not a string");
            }
            else
                throw new MyException(varName + " is not an integer");
        }
        else
            throw new MyException("the used variable " + varName + " was not declared before");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typeExp = exp.typeCheck(typeEnv);
        Type typeVar = typeEnv.lookup(varName);

        if(typeExp.equals(new StringType())){
            if(typeVar.equals(new IntType()))
                return typeEnv;
            else
                throw new MyException("ReadFile: the variable is not int");
        }
        else
            throw new MyException("ReadFile: not a string type");
    }
}
