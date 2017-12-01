package javaSwing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class MainSwing {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        createGUI();
                    }

                });

    }

    private static void  createGUI(){
        JFrame  jf = new JFrame("Dodawanie Frame");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Dodawanie Label");
        jf.getContentPane().add(label);

        JButton button = new JButton("");
        button.setBackground(Color.BLUE);
        jf.getContentPane().add(button);

        jf.pack();
        jf.setVisible(true);
    }

}
