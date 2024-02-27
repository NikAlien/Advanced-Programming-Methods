package repository;
import controller.MyException;
import model.PrgState;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
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
    public PrgState getCrtPrg() {
        return programs.get(0);
    }

    @Override
    public void logPrgStateExec() throws MyException {
        try{
            FileWriter writer = new FileWriter(logFilePath, true);
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(writer));
            printWriter.printf(programs.get(0).toString());
            printWriter.close();
        } catch (IOException e) {
            throw new MyException("Problems with opening of file in repo");
        }

    }

    public String toString(){
        return "Repository={" + programs + "}";
    }
}
