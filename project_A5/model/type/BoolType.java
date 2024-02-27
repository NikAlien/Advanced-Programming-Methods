package model.type;
import model.type.Type;
import model.value.BoolValue;
import model.value.Value;

public class BoolType implements Type{
    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolType)
            return true;
        else
            return false;
    }
    public BoolType() {
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }
}
