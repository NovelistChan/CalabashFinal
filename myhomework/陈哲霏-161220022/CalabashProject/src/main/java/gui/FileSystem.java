package gui;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import creature.*;
public class FileSystem {
    private static FileSystem instance = new FileSystem();
    private ArrayList<ArrayList<String>> history;
    public FileSystem(){
        history = new ArrayList<>();
        for(int i = 0; i < 16; i++){
            history.add(new ArrayList<String>());
        }
    }
    public static FileSystem getInstance(){
        return instance;
    }

    public void setHistory(){
        history = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < 16; i++){
            history.add(BattlePhase.getPhase().creatures[i].getHistory());
        }
    }

    public void saveToHistory(File file){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            Iterator<ArrayList<String>> iterator = history.iterator();
            while(iterator.hasNext()){
                for(String s : iterator.next()){
                    fileWriter.write(s);
                    System.out.print(s);
                }
                System.out.println();
                fileWriter.write("\n");
                fileWriter.flush();
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void readFromHistory(File file){
        BufferedReader bufferedReader = null;
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            Iterator<ArrayList<String>> iterator = history.iterator();
            for(Being ob: BattlePhase.getPhase().creatures){
                ArrayList<String> newHistory = iterator.next();
                newHistory.clear();
                newHistory.add(bufferedReader.readLine());
                ob.setHistory(newHistory);
            }
           /* instance.setHistory();*/
           // Iterator<ArrayList<String>> iterator = history.iterator();
            while(iterator.hasNext()){
                for(String s : iterator.next()){
                    System.out.print(s);
                }
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                bufferedReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
