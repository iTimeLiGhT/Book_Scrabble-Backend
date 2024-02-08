package test;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;
import java.util.concurrent.TimeoutException;


public class MyServer {

    public int _port;
    public ClientHandler _clientHandler;
    public volatile boolean _stop;

    public MyServer(int tempPort, ClientHandler clientHandler) {
        this._port = tempPort;
        this._clientHandler = clientHandler;
        _stop = false;
    }

    public void start() {
        new Thread(this::RunServer).start();
    }

    public void RunServer() {
            try {
                ServerSocket _serverSocket = new ServerSocket(_port);
                _serverSocket.setSoTimeout(1000);
                while (!_stop) {
                    try{
                        Socket _clientSocket = _serverSocket.accept();
                        _clientHandler.handleClient(_clientSocket.getInputStream(), _clientSocket.getOutputStream());
                        _clientSocket.close();
                        }
                catch (SocketTimeoutException e){}
            }
                _serverSocket.close();
            } catch (IOException e) {}
    }

    public void close() {
        _stop = true;
    }
}
