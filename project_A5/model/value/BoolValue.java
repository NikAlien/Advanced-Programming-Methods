package model.value;
import model.type.BoolType;
import model.type.IntType;
import model.type.Type;

public class BoolValue implements Value{
    private boolean val;
    public BoolValue(boolean v)
    {
        val = v;
    }
    public boolean getVal(){return val;}

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof BoolValue && ((BoolValue) another).getVal() == this.val)
            return true;
        else
            return false;
    }
}
