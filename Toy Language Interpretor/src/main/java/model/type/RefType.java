package model.type;
import model.value.RefValue;
import model.value.Value;

public class RefType implements Type{
    private Type innerComponent;
    public RefType(Type inner){
        this.innerComponent = inner;
    }
    public Type getInnerComponent() {return innerComponent;}

    @Override
    public boolean equals(Object another)
    {
        if(another instanceof RefType)
            return innerComponent.equals(((RefType) another).getInnerComponent());
        else
            return false;
    }
    @Override
    public Value defaultValue() {
        return new RefValue(0, innerComponent);
    }

    @Override
    public String toString() {
        return "reference -> " + innerComponent.toString();
    }
}
