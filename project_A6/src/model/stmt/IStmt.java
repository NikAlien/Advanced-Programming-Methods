package model.stmt;
import model.PrgState;
import controller.MyException; // We'll see with exceptions ??/
import model.type.Type;
import utils.MyIDictionary;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
