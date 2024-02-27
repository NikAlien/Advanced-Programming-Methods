package view;

import model.expr.ArithExp;
import model.stmt.*;
import model.expr.VarLookUpExp;
import model.stmt.fileStmt.CloseReadFile;
import model.stmt.fileStmt.OpenReadFile;
import model.stmt.fileStmt.ReadFile;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.StringValue;
import model.expr.ValueExp;
import model.value.IntValue;

public class Interpreter {
    public static void main(String[] v){
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))),
                new PrintStmt(new VarLookUpExp("v"))));

        IStmt ex2 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                new CompStmt(new IfStmt(new VarLookUpExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarLookUpExp("v"))))));

        IStmt ex3 = new CompStmt( new VarDeclStmt("a",new IntType()),
                new CompStmt(new VarDeclStmt("b",new IntType()),
                new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                new CompStmt(new AssignStmt("b",new ArithExp('+',new VarLookUpExp("a"), new ValueExp(new IntValue(1)))),
                new PrintStmt(new VarLookUpExp("b"))))));

        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("text.in"))),
                new CompStmt(new OpenReadFile(new VarLookUpExp("varf")),
                new CompStmt(new VarDeclStmt("varc", new IntType()),
                new CompStmt(new ReadFile(new VarLookUpExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarLookUpExp("varc")),
                new CompStmt(new ReadFile(new VarLookUpExp("varf"), "varc"),
                new CompStmt(new PrintStmt(new VarLookUpExp("varc")), new CloseReadFile(new VarLookUpExp("varf"))))))))));



        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ex1));
        menu.addCommand(new RunExample("2",ex2.toString(),ex2));
        menu.addCommand(new RunExample("3",ex3.toString(),ex3));
        menu.addCommand(new RunExample("4",ex4.toString(),ex4));
        menu.show();
    }
}
