/**
 *
 *  @author Rasskazov Pavlo S12479
 *
 */

package zad1;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server extends Thread{

  private ServerSocketChannel serverChanel;
  private Selector selector;
  private static String host = "localhost";
  private static int port = 11000;

  public Server(){

    try {

      serverChanel = ServerSocketChannel.open();
      serverChanel.socket().bind(new InetSocketAddress(host, port));
      serverChanel.configureBlocking(false);
      selector = Selector.open();
      serverChanel.register(selector, SelectionKey.OP_ACCEPT);


    } catch (IOException e) {
      e.printStackTrace();
      System.exit(1);
    }
    System.out.println("Server is runing...");
    start();
  }

  public void run() {

    boolean serverIsRunning = true;

    while (serverIsRunning) {
      try {
        selector.select();
        Set keys = selector.selectedKeys();
        Iterator iter = keys.iterator();
        while (iter.hasNext()) {

          SelectionKey key = (SelectionKey) iter.next();
          iter.remove();

          if (key.isAcceptable()) {
            SocketChannel cc = serverChanel.accept();
            cc.configureBlocking(false);
            cc.register(selector, (SelectionKey.OP_READ | SelectionKey.OP_WRITE));
            continue;
          }

          if (key.isReadable()) {
            SocketChannel cc = (SocketChannel) key.channel();
            String mesage = ReadWriteNIO.read(cc);
            sendMesagesToClients(mesage);    // obsluga zlecenia
            continue;
          }
        }
      } catch (Exception exc) {
        exc.printStackTrace();
        continue;
      }

    }
  }

  private void sendMesagesToClients(String mesage){

    try {
      selector.select();
      Set keys = selector.selectedKeys();
      Iterator iter = keys.iterator();
      while (iter.hasNext()) {
        SelectionKey selK = (SelectionKey) iter.next();
        if(selK.isWritable()) {
          SocketChannel chanel = (SocketChannel) selK.channel();
          ReadWriteNIO.write(mesage, chanel);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    new Server();
  }
}
