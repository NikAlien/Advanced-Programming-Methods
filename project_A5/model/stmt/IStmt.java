package model.stmt;
import model.PrgState;
import controller.MyException; // We'll see with exceptions ??/

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
