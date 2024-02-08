package test;
import java.io.BufferedReader;
import java.io.FileReader;

public class IOSearcher {

    static boolean search(String temp_word, String...fileNames){
        for (String temp_file:fileNames){
            try{
                FileReader objReader = new FileReader(temp_file);
                BufferedReader objBuffer = new BufferedReader(objReader);
                String check_word;
                while ((check_word = objBuffer.readLine()) != null){
                    if (check_word.contains(temp_word)) {
                        objBuffer.close();
                        return true;
                    }
                }
                objBuffer.close();

            }catch (Exception e){System.out.println(e);}
        }
        return false;
    }

}
