package model;

import controller.MyException;
import model.stmt.IStmt;
import model.value.StringValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;
import utils.MyIList;
import utils.MyIStack;

import java.io.BufferedReader;

public class PrgState{
    private final int id;
    private static int generatedId = 0;
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heapMemory;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value> ot, MyIDictionary<StringValue, BufferedReader> ft, MyIHeap<Integer, Value> hp, IStmt prg){
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        heapMemory = hp;
        stk.push(prg);
        id = generateId();
    }
    public int getId () {return id;}
    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap<Integer, Value> getHeapMemory(){return heapMemory;}

    public MyIList<Value> getOut() {
        return out;
    }

    public synchronized static int generateId(){
        return ++generatedId;
    }

    public boolean isNotCompleted(){
        return !this.exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException {
        if(exeStack.isEmpty()) throw new MyException("Program State stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        String output = "\n\n---- PROGRAM STATE ----\n Program Id -> ";
        output += id;
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
