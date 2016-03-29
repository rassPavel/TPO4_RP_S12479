/**
 *
 *  @author Rasskazov Pavlo S12479
 *
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client{

    protected String name;
    private static String host = "localhost";
    private static int port = 11000;
    protected SocketChannel sc;

    public Client(){
        new Login(this);
    }

    public void startClient(String name){
        this.name = name;

        try {
            sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        MainDialog MD = new MainDialog(this);

        String inMessage;
        while(true){
            inMessage = ReadWriteNIO.read(sc);
            if(!inMessage.isEmpty()){
                MD.postMessage(inMessage);
            }
        }

    }

    public void sendMesage(String mesage){
        ReadWriteNIO.write(name + ": " + mesage, sc);
    }

    public void stopClient(){
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
