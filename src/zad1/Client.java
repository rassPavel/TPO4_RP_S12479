/**
 *
 *  @author Rasskazov Pavlo S12479
 *
 */

package zad1;


public class Client {

    protected String name;

    public Client(String name){
        this.name = name;
        //add this client to server
        new MainDialog();
    }

    public static void main(String[] args) {
        new Login();
    }
}
