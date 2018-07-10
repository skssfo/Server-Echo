import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by rishi on 7/9/18.
 */
public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private DataInputStream serverInput;

    public Client(String address, int port) {
        //establish connection to server
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");
            in = new DataInputStream(System.in);
            out = new DataOutputStream(socket.getOutputStream());
            serverInput = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        }
        catch(IOException i) {
            System.out.println(i);
        }

        //keep reading input until it is "Done"
        String line = "";

        while (!line.equals("Done")) {
            try {
                line = in.readLine();
                out.writeUTF(line);
                System.out.println(serverInput.readUTF());
            }
            catch(IOException i) {
                System.out.println(i);
            }
        }

        //close connection to server
        try {
            in.close();
            out.close();
            socket.close();
        }
        catch(IOException i) {
            System.out.println(i);
        }

    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",5000);
    }


}
