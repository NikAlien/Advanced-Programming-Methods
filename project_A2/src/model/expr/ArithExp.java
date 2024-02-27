package model.expr;

import controller.MyException;
import model.type.IntType;
import model.value.IntValue;
import model.value.Value;
import utils.MyIDictionary;

public class ArithExp implements Expression {

    private Expression exp1;
    private Expression exp2;
    char op;
    public ArithExp(char op, Expression e1, Expression e2){
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }

    @Override
    public String toString() {
        return exp1.toString() + " " + op + " " + exp2.toString();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws MyException {
        Value v1, v2;
        v1 = exp1.eval(tbl);
        if(v1.getType().equals(new IntType())){
            v2 = exp2.eval(tbl);
            if(v2.getType().equals(new IntType())){
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();

                if(op == '+')
                    return new IntValue(n1 + n2);
                if(op == '-')
                    return new IntValue(n1 - n2);
                if(op == '*')
                    return new IntValue(n1 * n2);
                if(op == '/')
                    if(n2 == 0)
                        throw new MyException("No division by 0");
                    else
                        return new IntValue(n1 / n2);

            }
            else
                throw new MyException("Second operand not an Integer");
        }
        else
            throw new MyException("First operand not an Integer");
        return new IntValue(-1);
    }
}
