package gui.dataTransferTableView;

import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class HeapEntry {
    private SimpleStringProperty heapAddress;
    private SimpleStringProperty heapValue;

    public HeapEntry(Integer intNr, Value val){
        this.heapAddress = new SimpleStringProperty(Integer.toString(intNr));
        this.heapValue = new SimpleStringProperty(val.toString());
    }

    public SimpleStringProperty heapAddressProperty(){
        return this.heapAddress;
    }

    public SimpleStringProperty heapValueProperty(){
        return this.heapValue;
    }

    public String getHeapAddress(){
        return this.heapAddress.get();
    }

    public String getHeapValue(){
        return this.heapValue.get();
    }

    public void setHeapAddress(String newVariableName){
        this.heapAddress.set(newVariableName);
    }

    public void setHeapValue(String newValue){
        this.heapValue.set(newValue);
    }
}
