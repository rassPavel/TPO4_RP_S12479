package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by pavel on 29.03.16.
 */
public class ReadWriteNIO {

    private static Charset charset = Charset.forName("ISO-8859-2");
    private static int size = 1024;

    public static void write(String mesage, SocketChannel chanel){

        try {
            ByteBuffer buffer = charset.encode(mesage);
            while (buffer.hasRemaining()) {
                chanel.write(buffer);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(SocketChannel chanel){

        if(!chanel.isOpen()) return null;

        ByteBuffer buffer = ByteBuffer.allocate(size);
        StringBuffer mesage = new StringBuffer();
        try {
            boolean loop = true;
            while (loop) {
                int n = chanel.read(buffer);
                if(n > 0){
                    buffer.flip();
                    CharBuffer charBuffer = charset.decode(buffer);
                    while(charBuffer.hasRemaining()){
                        char ch = charBuffer.get();
                        if(ch == '\r' || ch == '\n'){
                            loop = false;
                            continue;
                        }
                        mesage.append(ch);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mesage.toString();
    }
}
