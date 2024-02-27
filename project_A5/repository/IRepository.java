package repository;
import controller.MyException;
import model.PrgState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void logPrgStateExec(PrgState prs) throws MyException;
    void add(PrgState program);
    List<PrgState> getPrgState();
    void setPrgState(List<PrgState> lps);
}
