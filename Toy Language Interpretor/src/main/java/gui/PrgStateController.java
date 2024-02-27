package gui;

import controller.MyException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;
import model.PrgState;
import model.stmt.IStmt;
import model.type.Type;
import model.value.StringValue;
import model.value.Value;
import utils.*;
import gui.dataTransferTableView.HeapEntry;
import gui.dataTransferTableView.SymTableEntry;
import view.Command;
import view.RunExample;

import java.io.BufferedReader;
import java.util.List;

public class PrgStateController {
    private SelectController selectController;
    private PrgState currentState;
    private PrgState selectedProgram;

    @FXML
    private TextField PrgStateIdField;

    @FXML
    private TableView<HeapEntry> heapTableView;
    @FXML
    private TableColumn<HeapEntry, String> addressColumnHeap;
    @FXML
    private TableColumn<HeapEntry, String> valueColumnHeap;

    @FXML
    private TableView<SymTableEntry> symTableView;
    @FXML
    private TableColumn<SymTableEntry, String> varNameColumnSymTable;
    @FXML
    private TableColumn<SymTableEntry,String> valueColumnSymTable;


    @FXML
    private ListView<Value> outputListView;

    @FXML
    private ListView<StringValue> fileTableListView;

    @FXML
    private ListView<PrgState> PrgStateListView;

    @FXML
    private ListView<IStmt> exeStackListView;

    @FXML
    private Button oneStepButton;



    @FXML
    private void initialize(){
        this.PrgStateIdField.setEditable(false);

        this.addressColumnHeap.setCellValueFactory(new PropertyValueFactory<HeapEntry, String>("heapAddress"));
        this.valueColumnHeap.setCellValueFactory(new PropertyValueFactory<HeapEntry, String >("heapValue"));

        this.varNameColumnSymTable.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("variableName"));
        this.valueColumnSymTable.setCellValueFactory(new PropertyValueFactory<SymTableEntry, String>("value"));


        this.outputListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Value>() {
            @Override
            public String toString(Value valueInterface) {
                return valueInterface.toString();
            }

            @Override
            public Value fromString(String s) {
                return null;
            }
        }));

        this.fileTableListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<StringValue>() {
            @Override
            public String toString(StringValue stringValue) {
                return stringValue.toString();
            }

            @Override
            public StringValue fromString(String s) {
                return null;
            }
        }));

        this.exeStackListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<IStmt>() {
            @Override
            public String toString(IStmt stmt) {
                return stmt.toString();
            }

            @Override
            public IStmt fromString(String s) {
                return null;
            }
        }));

        this.PrgStateListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<PrgState>() {
            @Override
            public String toString(PrgState programState) {
                return Integer.toString(programState.getId());
            }

            @Override
            public PrgState fromString(String s) {
                return null;
            }
        }));


        this.PrgStateListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        this.PrgStateListView.getSelectionModel().selectedItemProperty().addListener((a,b,state)-> {
            if(state != null) {
                currentState = state;
                showDataForSelectedProgramState(state);
            }
        });

        this.oneStepButton.setOnAction(actionEvent -> {
            try {
                runOneStep(this.selectController.getCommandsListView().getSelectionModel().getSelectedItems().get(0));
                showDataForSelectedProgramState(currentState);
            } catch (MyException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error in program");
                errorAlert.setContentText(e.toString());
                errorAlert.showAndWait();
            }
        });

    }

    private void runOneStep(RunExample runExample) throws MyException {
        try{
            runExample.getController().runOneStepProg();
        }
        catch (Exception e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Error in program");
            errorAlert.setContentText(e.toString());
            errorAlert.showAndWait();
        }
        inputDataForExample(runExample);
    }

    private void showDataForSelectedProgramState(PrgState prog) {
        this.symTableView.getItems().clear();
        this.exeStackListView.getItems().clear();

        MyIStack<IStmt> executionStack = prog.getExeStack();
        MyIDictionary<String, Value> symbolTable = prog.getSymTable();


        executionStack.reverese().forEach((statement)->this.exeStackListView.getItems().add(statement));
        symbolTable.getContent().forEach((name, value)->this.symTableView.getItems().add(new SymTableEntry(name, value)));
    }

    public void setSelectController(SelectController newSelectController){
        this.selectController = newSelectController;

        this.selectController.getCommandsListView().getSelectionModel().selectedItemProperty().addListener((a,b,ex)-> {
            try {
                this.inputDataForExample(ex);
            } catch (MyException e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Error in program");
                errorAlert.setContentText(e.toString());
                errorAlert.showAndWait();
            }
        });
    }

    private void inputDataForExample(RunExample ex) throws MyException {
        this.heapTableView.getItems().clear();
        this.outputListView.getItems().clear();
        this.fileTableListView.getItems().clear();
        this.PrgStateListView.getItems().clear();
        this.symTableView.getItems().clear();
        this.exeStackListView.getItems().clear();
        ex.getProg().typeCheck(new MyDictionary<String, Type>());


        List<PrgState> prgStateList = ex.getController().getRepo().getPrgState();

        if(prgStateList.size() != 0)
            this.selectedProgram = prgStateList.get(0);


        MyIHeap<Integer, Value> sharedHeap = this.selectedProgram.getHeapMemory();
        sharedHeap.getContent().forEach((address, value) -> this.heapTableView.getItems().add(new HeapEntry(address, value)));

        MyIList<Value> output = this.selectedProgram.getOut();
        output.getContent().forEach((value)->this.outputListView.getItems().add(value));

        MyIDictionary<StringValue, BufferedReader> fileTable = this.selectedProgram.getFileTable();
        fileTable.getContent().forEach((fileName, filePath)->this.fileTableListView.getItems().add(fileName));

        prgStateList.forEach((programState)->this.PrgStateListView.getItems().add(programState));

        MyIStack<IStmt> executionStack = selectedProgram.getExeStack();
        executionStack.reverese().forEach((statement)->this.exeStackListView.getItems().add(statement));

        MyIDictionary<String, Value> symbolTable = selectedProgram.getSymTable();
        symbolTable.getContent().forEach((name, value)->this.symTableView.getItems().add(new SymTableEntry(name, value)));

        currentState = selectedProgram;

        String str = "Nr of PrgStates: ";
        this.PrgStateIdField.setText(str + Integer.toString(prgStateList.size()));
    }


}
