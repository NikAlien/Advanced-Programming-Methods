package model.type;
import model.type.Type;

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
}
