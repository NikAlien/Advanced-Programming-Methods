package controller;

import model.stmt.IStmt;
import model.PrgState;
import repository.IRepository;
import utils.MyIStack;

import java.io.IOException;


public class Controller {
    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    private IRepository repo;
    public Controller(IRepository r) {
        repo = r;
    }

    public PrgState oneStep(PrgState state) throws MyException{
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty()) throw new MyException("progState stack is empty");
        IStmt crtStmt = stk.pop();
        return crtStmt.execute(state); //needs implementation
    }

    public void allStep() throws MyException {
        PrgState prg = repo.getCrtPrg();
        repo.logPrgStateExec();
//        System.out.println(prg);

        while (!prg.getExeStack().isEmpty()){
            oneStep(prg);
            repo.logPrgStateExec();
//            System.out.println(prg);
        }
    }

}
