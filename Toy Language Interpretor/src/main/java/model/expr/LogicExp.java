package model.expr;

import controller.MyException;
import model.type.BoolType;
import model.type.Type;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.Value;
import utils.MyIDictionary;
import utils.MyIHeap;

import java.util.Objects;

public class LogicExp implements Expression {
    private Expression exp1;
    private Expression exp2;
    String op;
    public LogicExp(String op, Expression e1, Expression e2)
    {
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }
    @Override
    public String toString() {
        return exp1.toString() + " " + op + " " + exp2.toString();
    }
    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = exp1.eval(tbl, hp);
        if(v1.getType().equals(new BoolType())){
            v2 = exp2.eval(tbl, hp);
            if(v2.getType().equals(new BoolType())){
                BoolValue i1 = (BoolValue) v1;
                BoolValue i2 = (BoolValue) v2;
                boolean n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();

                if(Objects.equals(op, "&&"))
                    return new BoolValue(n1 && n2);
                if(Objects.equals(op, "||"))
                    return new BoolValue(n1 || n2);
            }
            else
                throw new MyException("Second operand not a Boolean");
        }
        else
            throw new MyException("First operand not a Boolean");
        return new IntValue(-1);
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type1, type2;
        type1 = exp1.typeCheck(typeEnv);
        type2 = exp2.typeCheck(typeEnv);

        if(type1.equals(new BoolType())){
            if(type2.equals(new BoolType()))
                return new BoolType();
            else
                throw new MyException("Second operand is not bool");
        }else
            throw new MyException("First operand is not bool");
    }
}
