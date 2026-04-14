import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        
        final String HOST = "127.0.0.1";
        final int PUERTO = 6000;

        DataInputStream in;
        DataOutputStream out;

        try {

            Socket sc = new Socket(HOST,PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            out.writeUTF("Hola mundo desde el cliente!");
            String mensaje = in.readUTF();
            System.out.println(mensaje);

            sc.close();


        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }   

    }
}
