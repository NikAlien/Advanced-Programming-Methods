package repository;

import controller.MyException;
import model.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
public class Repository implements IRepository {
    private List<PrgState> programs;
    private String logFilePath;

    public Repository(String fileName){
        programs = new ArrayList<PrgState>();
        logFilePath = fileName;
    }

    public void add(PrgState program){
        programs.add(program);
    }

    @Override
    public List<PrgState> getPrgState() {
        return programs;
    }

    @Override
    public void setPrgState(List<PrgState> lps) {
        programs = lps;
    }

    @Override
    public void logPrgStateExec(PrgState prs) throws MyException {
        try{
            FileWriter writer = new FileWriter(logFilePath, true);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(writer));
            printWriter.printf(prs.toString());
            printWriter.close();
        } catch (IOException e) {
            throw new MyException("Problems with opening of file in repo");
        }

    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
