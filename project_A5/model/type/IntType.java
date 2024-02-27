package model.type;
import model.type.Type;
import model.value.IntValue;
import model.value.Value;

public class IntType implements Type {
    @Override
    public boolean equals(Object another)
    {
        if(another instanceof IntType)
            return true;
        else
            return false;
    }
    public IntType() {
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }
}
