package view;

import controller.MyException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){ commands=new HashMap<>(); }
    public void addCommand(Command c){ commands.put(c.getKey(),c);}
    private void printMenu(){
        System.out.println("\n--- MENU OPTIONS ---");
        for(Command com : commands.values()){
            String line=String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        }
    }
    public void show(){
        Scanner scanner=new Scanner(System.in);
        while(true){
            printMenu();
            System.out.printf("Input the option --> ");
            String key=scanner.nextLine();
            Command com=commands.get(key);
            if (com==null){
                System.out.println("Invalid Option !!!");
                continue; }
            try {
                com.execute();
            } catch (MyException e) {
                System.out.println(e);
            }
        }
    }
}
