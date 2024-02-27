package model.expr;
import controller.MyException;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

import java.util.Objects;

public class RelationalExp implements Expression{
    private Expression exp1;
    private Expression exp2;
    private String op;
    public RelationalExp(String o, Expression e1, Expression e2){
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = o;
    }

    @Override
    public String toString() {
        return exp1.toString() + " " + op + " " + exp2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = exp1.eval(tbl, hp);
        if(v1.getType().equals(new IntType())){
            v2 = exp2.eval(tbl, hp);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();

                if(Objects.equals(op, "<"))
                    return new BoolValue(n1 < n2);
                if(Objects.equals(op, "<="))
                    return new BoolValue(n1 <= n2);
                if(Objects.equals(op, "=="))
                    return new BoolValue(n1 == n2);
                if(Objects.equals(op, "!="))
                    return new BoolValue(n1 != n2);
                if(Objects.equals(op, ">"))
                    return new BoolValue(n1 > n2);
                if(Objects.equals(op, ">="))
                    return new BoolValue(n1 >= n2);
            }
            else
                throw new MyException("Second operand not a Integer");
        }
        else
            throw new MyException("First operand not a Integer");
        return new IntValue(-1);
    }
}
