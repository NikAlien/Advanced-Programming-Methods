package model;

import model.stmt.IStmt;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIList;
import utils.MyIStack;

public class PrgState{
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "\nPrgState:\n" + exeStack + symTable + out;
    }
}
