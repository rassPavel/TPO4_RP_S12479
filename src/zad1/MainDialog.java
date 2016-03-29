package zad1;

import layout.TableLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by pavel on 28.03.16.
 */
public class MainDialog {

    private JFrame frame;
    private JPanel panel;
    private JTextField yourMesage;
    private JTextArea dialog;
    private JButton logOut, sendMesage;
    private Client client;

    public MainDialog(Client client){
        this.client = client;
                create();
    }


    private void create(){

        frame = new JFrame("Dialog");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setBounds(500, 200, 400, 500);

        panel = new JPanel();

        dialog = new JTextArea();
        dialog.setEditable(false);

        yourMesage = new JTextField();

        logOut = new JButton("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.stopClient();
                frame.dispose();
            }
        });

        sendMesage = new JButton("Send");
        sendMesage.setToolTipText("Alt+Enter");
        sendMesage.setMnemonic(KeyEvent.VK_ENTER);
        sendMesage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMesage(yourMesage.getText() + "\n");
                yourMesage.setText("");
            }
        });

        double[][] size = {
                {5, 0.74, TableLayout.FILL, 0.24, 5},
                {5,0.2, 0.6,TableLayout.FILL,0.18 ,5}
        };

        TableLayout tl = new TableLayout(size);

        panel.setLayout(tl);
        panel.add(dialog, "1,1,1,2");
        panel.add(logOut, "3,1,3,1");
        panel.add(yourMesage, "1,4,1,4");
        panel.add(sendMesage, "3,4,3,4");

        frame.add(panel);
        frame.setVisible(true);
    }

    public void postMessage(String text){
        dialog.append(text + "\n");
    }

}
