package test;
import java.util.Arrays;
import java.util.HashMap;


public class DictionaryManager {

    HashMap<String,Dictionary> _dictionaryMap;
    private static DictionaryManager DM = null;

    DictionaryManager(){
        _dictionaryMap = new HashMap<String,Dictionary>();
    }

    public boolean query(String... args){
        IsDicExist(args);
        String word = args[args.length-1];
        boolean found = false;
        for (int i=0; i<args.length-1;i++){
            if (_dictionaryMap.get(args[i]).query(word))
                found = true;
        }
        return found;
    }

    public boolean challenge(String... args){
        IsDicExist(args);
        String word = args[args.length-1];
        boolean found = false;
        for (int i=0; i<args.length-1;i++){
            if (_dictionaryMap.get(args[i]).challenge(word))
                found = true;
        }
        return found;
    }

    public int getSize(){
        return _dictionaryMap.size();

    }

    public static DictionaryManager get() {
        if (DM == null)
            return DM = new DictionaryManager();
        return DM;
    }

    private void IsDicExist(String... args){
        for (int i=0;i<args.length-1;i++){
            if(!_dictionaryMap.containsKey(args[i]))
                _dictionaryMap.put(args[i],new Dictionary(args[i]));
        }
    }










}
