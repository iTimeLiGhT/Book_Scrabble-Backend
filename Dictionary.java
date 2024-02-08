package test;

import java.io.BufferedReader;
import java.io.FileReader;

public class Dictionary {
    CacheManager existsWords;
    CacheManager nonExistsWords;
    BloomFilter bloomFilter;
    String myFiles[];

    public Dictionary(String...fileNames){
        existsWords = new CacheManager(400, new LRU());
        nonExistsWords = new CacheManager(100, new LFU());
        bloomFilter = new BloomFilter(256, "MD5", "SHA1");
        myFiles = fileNames;
        for (String temp_file:fileNames){
            try{
                FileReader objReader = new FileReader(temp_file);
                BufferedReader objBuffer = new BufferedReader(objReader);
                String check_word;
                while ((check_word = objBuffer.readLine()) != null){
                    String[] newWord = check_word.split(" ");
                    for (String itr:newWord){
                        existsWords.add(itr);
                        bloomFilter.add(itr);
                    }

                }
                objBuffer.close();
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }
    public boolean query(String temp_word){
        if (existsWords.query(temp_word))
            return true;
        else if (nonExistsWords.query(temp_word)) {
            return false;
        } else if (bloomFilter.contains(temp_word)) {
            existsWords.add(temp_word);
            return true;
        } else {
            nonExistsWords.add(temp_word);
            return false;
        }

    }
    public boolean challenge(String temp_word){
        try {
            if (IOSearcher.search(temp_word, myFiles)) {
                existsWords.add(temp_word);
                return true;
            } else {
                nonExistsWords.add(temp_word);
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}
