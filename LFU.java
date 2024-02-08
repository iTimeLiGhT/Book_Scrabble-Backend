package test;
import java.util.HashMap;

public class LFU implements CacheReplacementPolicy{
    private HashMap<String,Integer> catalogue = new HashMap<String,Integer>();

    public LFU(){}
    public void add(String temp_word){
        if (catalogue.containsKey(temp_word))
            catalogue.put(temp_word,catalogue.get(temp_word)+1);
        else
            catalogue.put(temp_word,1);
    }
    public String remove(){
        int min = 9999;
        String minWord = " ";
        for (String itr : catalogue.keySet()){
            if (catalogue.get(itr) <= min) {
                min = catalogue.get(itr);
                minWord = itr;
            }
        }
        return minWord;
    }

}
