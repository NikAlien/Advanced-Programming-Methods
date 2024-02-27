package repository;
import model.PrgState;
public interface IRepository {
    PrgState getCrtPrg();

    void add(PrgState program);
}
