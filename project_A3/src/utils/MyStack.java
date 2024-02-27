package utils;
import model.stmt.IStmt;

import java.util.*;

public class MyStack<T> implements MyIStack<T>{
    private Stack<T> stack;

    public MyStack()
    {
        this.stack = new Stack<T>();
    }
    @Override
    public T pop() {
        return stack.pop();
    }

    @Override
    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> reverese() {
        List<T> items;
        items = Arrays.asList((T[])stack.toArray());

        Collections.reverse(items);
        return items;
    }

    @Override
    public String toString() {
        List<IStmt> stackToList = new ArrayList(stack);
        Collections.reverse(stackToList);
        String expectedOutput = "";
        for(IStmt st : stackToList)
        {
            expectedOutput += st.toString();
            expectedOutput += "\n";
        }
        return expectedOutput;
    }
}
