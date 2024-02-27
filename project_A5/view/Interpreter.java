package view;

import model.expr.*;
import model.stmt.*;
import model.stmt.fileStmt.CloseReadFile;
import model.stmt.fileStmt.OpenReadFile;
import model.stmt.fileStmt.ReadFile;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.StringValue;
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

        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewStmt("a", new VarLookUpExp("v")),
                new CompStmt(new PrintStmt(new VarLookUpExp("v")), new PrintStmt(new VarLookUpExp("a")))))));

        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewStmt("a", new VarLookUpExp("v")),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarLookUpExp("v"))),
                new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarLookUpExp("a"))), new ValueExp(new IntValue(5)))))))));

        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarLookUpExp("v"))),
                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarLookUpExp("v")), new ValueExp(new IntValue(5))))))));

        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(4))),
                new CompStmt(new WhileStmt(
                new RelationalExp(">", new VarLookUpExp("v"), new ValueExp(new IntValue(0))),
                new CompStmt(new PrintStmt(new VarLookUpExp("v")),
                new AssignStmt("v", new ArithExp('-', new VarLookUpExp("v"), new ValueExp(new IntValue(1)))))),
                new PrintStmt(new VarLookUpExp("v")))));

        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                new CompStmt(new NewStmt("a", new VarLookUpExp("v")),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarLookUpExp("v"))),
                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarLookUpExp("v"))),
                new CompStmt(new NewStmt("a", new VarLookUpExp("v")),
                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarLookUpExp("a"))))))))))));

        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(
                        new CompStmt(new WriteHeapStmt("a",  new ValueExp(new IntValue(30))),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarLookUpExp("v")),
                        new PrintStmt(new ReadHeapExp(new VarLookUpExp("a"))))))),
                new CompStmt(new PrintStmt(new VarLookUpExp("v")),
                new PrintStmt(new ReadHeapExp(new VarLookUpExp("a")))))))));

        IStmt ex11 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                new CompStmt(new ForkStmt(
                        new CompStmt(new NewStmt("a", new VarLookUpExp("v")),
                         new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                         new CompStmt(new PrintStmt(new VarLookUpExp("v")),
                         new PrintStmt(new ReadHeapExp(new VarLookUpExp("a"))))))),
                new CompStmt(new PrintStmt(new VarLookUpExp("v")),
                new PrintStmt(new ReadHeapExp(new VarLookUpExp("a")))))))));


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),ex1));
        menu.addCommand(new RunExample("2",ex2.toString(),ex2));
        menu.addCommand(new RunExample("3",ex3.toString(),ex3));
        menu.addCommand(new RunExample("4",ex4.toString(),ex4));
        menu.addCommand(new RunExample("5",ex5.toString(),ex5));
        menu.addCommand(new RunExample("6",ex6.toString(),ex6));
        menu.addCommand(new RunExample("7",ex7.toString(),ex7));
        menu.addCommand(new RunExample("8",ex8.toString(),ex8));
        menu.addCommand(new RunExample("9",ex9.toString(),ex9));
        menu.addCommand(new RunExample("10",ex10.toString(),ex10));
        menu.addCommand(new RunExample("11",ex11.toString(),ex11));
        menu.show();
    }
}
