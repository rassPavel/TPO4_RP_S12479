package zad1;

import layout.TableLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by pavel on 28.03.16.
 */
public class Login {

    private JFrame frame;
    private JPanel panel;
    private JLabel nameLabel;
    private JTextField nameInput;
    private JButton accepteButton;

    public Login(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                create();
            }
        });
    }

    private void create(){

        frame = new JFrame("Login");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 300, 300, 100);

        panel = new JPanel();

        nameLabel = new JLabel("Enter your nick:");

        nameInput = new JTextField();

        accepteButton = new JButton("Accept");
        accepteButton.setToolTipText("Alt+Enter");
        accepteButton.setMnemonic(KeyEvent.VK_ENTER);
        accepteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Client(nameInput.getText());
                //add new client to server
                frame.dispose();
            }
        });

        double[][] size = {
                {5,0.49 , TableLayout.FILL, 0.49, 5},
                {5,0.49 , TableLayout.FILL, 0.49, 5}
        };

        TableLayout tl = new TableLayout(size);

        panel.setLayout(tl);
        panel.add(nameLabel, "1,1,1,1");
        panel.add(nameInput, "3,1,3,1");
        panel.add(accepteButton, "1,3,3,3");

        frame.add(panel);
        frame.setVisible(true);
    }

}

