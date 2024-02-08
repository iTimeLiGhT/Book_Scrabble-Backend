package test;
import java.util.LinkedList;

public class LRU implements CacheReplacementPolicy{
    private LinkedList<String> catalogue = new LinkedList<String>();

    public LRU(){}
    public void add(String temp_word){
        if (catalogue.indexOf(temp_word) == -1){
            catalogue.addFirst(temp_word);
        } else{
            catalogue.remove(temp_word);
            catalogue.addFirst(temp_word);
        }
    }
    public String remove(){
        return catalogue.getLast();
    }

}