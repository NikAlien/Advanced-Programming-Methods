package model;

import model.stmt.IStmt;
import model.value.StringValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;
import utils.MyIList;
import utils.MyIStack;

import java.io.BufferedReader;

public class PrgState{
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heapMemory;
    IStmt originalProgram; //optional field, but good to have
    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> ft, MyIHeap<Integer, Value> hp, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        fileTable = ft;
        heapMemory = hp;
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

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<StringValue, BufferedReader> ft) {
        this.fileTable = ft;
    }

    public MyIHeap<Integer, Value> getHeapMemory(){return heapMemory;}

    public void setHeap(MyIHeap<Integer, Value> hp){this.heapMemory = hp;}

    public MyIList<Value> getOut() {
        return out;
    }

    public void setOut(MyIList<Value> out) {
        this.out = out;
    }

    @Override
    public String toString() {
        String output = "\n\n---- PROGRAM STATE ----";
        output += "\n\nExecution Stack:\n";
        output += exeStack.toString();
        output += "\n\nSymbol Table:\n";
        output += symTable.toString();
        output += "\n\nHeap Memory:\n";
        output += heapMemory.toString();
        output += "\n\nOutput:\n";
        output += out.toString();
        output += "\n\nFile Table:\n";
        output += fileTable.toString();
        output += "\n----------------------------------\n";
        return  output;
    }
}
