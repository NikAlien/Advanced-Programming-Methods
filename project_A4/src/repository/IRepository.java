package repository;
import controller.MyException;
import model.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCrtPrg();
    void logPrgStateExec() throws MyException;
    void add(PrgState program);
}
