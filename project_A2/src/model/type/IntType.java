package model.type;
import model.type.Type;

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
}
