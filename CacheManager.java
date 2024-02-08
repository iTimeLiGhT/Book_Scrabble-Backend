package test;
import java.util.HashSet;

public class CacheManager {
	int size;
    int counter = 0;
    CacheReplacementPolicy CRP;
    HashSet<String> wordsSet = new HashSet<String>();

    public CacheManager(int temp_size, CacheReplacementPolicy temp_CRP){
        size = temp_size;
        CRP = temp_CRP;
    }
    public boolean query(String temp_word){
        if (wordsSet.contains(temp_word))
            return true;
        else
            return false;
    }
    public void add(String temp_word){
        if (query(temp_word))
            CRP.add(temp_word);
        else {
            if (counter < size){
                wordsSet.add(temp_word);
                CRP.add(temp_word);
                counter++;
            }else {
                String wordToRemove = CRP.remove();
                wordsSet.remove(wordToRemove);
                wordsSet.add(temp_word);
                CRP.add(temp_word);
            }

        }
    }

}
