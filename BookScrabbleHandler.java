package test;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler{

    String _QorD;
    PrintWriter _stringOut;
    Scanner _stringIn;

        @Override
        public void handleClient (InputStream inFromclient, OutputStream outToClient){
            _stringOut = new PrintWriter(outToClient);
            _stringIn = new Scanner(inFromclient);
            String[] buffer = _stringIn.next().split(",");
            String[] arr = new String[buffer.length-1];
            _QorD = buffer[0];
            System.arraycopy(buffer, 1, arr, 0, buffer.length-1);

            if (_QorD.equals("Q")) {
                DictionaryManager DM = new DictionaryManager();
                    if(DM.query(arr))
                        _stringOut.println("true");
                    else
                        _stringOut.println("false");

            } else if (_QorD.equals("C")){
                DictionaryManager DM = new DictionaryManager();
                if(DM.challenge(arr))
                    _stringOut.println("true");
                else
                    _stringOut.println("false");
                }

            _stringOut.flush();
        }

        @Override
        public void close () {
            _stringOut.close();
            _stringIn.close();
        }
}
