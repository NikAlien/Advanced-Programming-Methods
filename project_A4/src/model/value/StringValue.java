package model.value;
import model.type.IntType;
import model.type.StringType;
import model.type.Type;

public class StringValue implements Value{
    private String val;
    public StringValue(String v)
    {
        val = v;
    }
    public String getVal(){return val;}

    @Override
    public String toString() {
        return val;
    }
    @Override
    public Type getType() {
        return new StringType();
    }
    @Override
    public boolean equals(Object another)
    {
        if(another instanceof StringValue && ((StringValue) another).getVal() == this.val)
            return true;
        else
            return false;
    }
}
