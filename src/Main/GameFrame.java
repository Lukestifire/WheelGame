package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;



/*
This is the GameFrame class. This class sets up the JFrame that
acts as the window to put all the Java "Swing components" in.

 */



public class GameFrame extends JFrame implements ActionListener {
    JButton button;

    GameFrame() throws IOException {


        button = new JButton();
        button.setBounds(500, 100, 200, 50);
        button.addActionListener(this);
        button.setText("Start Game!");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setSize(1200,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(button);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button) {
            // Adds the game panel to the main game frame and sets
            // attributes of the frame.
            this.remove(button);
            this.setVisible(false);
            GamePanel gamePanel = null;
            try {
                gamePanel = new GamePanel();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            super.add(gamePanel);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(1200,600));
        this.setTitle("Paint Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();            // fits jFrame snugly around all components added
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startGameThread();
        }
    }
}

