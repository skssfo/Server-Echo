import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by rishi on 7/9/18.
 */
public class Server {
    private Socket socket;
    private ServerSocket server;
    private DataInputStream in;
    private DataOutputStream out;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            String line = in.readUTF();
            while (!line.equals("Done")) {
                try {
                    out.writeUTF("Server: " + line);
                    line = in.readUTF();
                }
                catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
            socket.close();
            in.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }


    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }

}
