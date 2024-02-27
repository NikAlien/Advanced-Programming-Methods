package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue implements Value{
    private int address;
    private Type locationType;
    public RefValue(int adr, Type inC){
        this.address = adr;
        this.locationType = inC;
    }
    public int getAddress(){return address;}

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType.toString() + ")";
    }

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof RefValue && ((RefValue) another).getAddress() == this.address)
            return true;
        else
            return false;
    }
}
