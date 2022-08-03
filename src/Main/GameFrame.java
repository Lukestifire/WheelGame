package Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


/*
This is the GameFrame class. This class sets up the JFrame that
acts as the window to put all the Java "Swing components" in.

 */

public class GameFrame extends JFrame {

    GameFrame() throws IOException {

        // Adds the game panel to the main game frame and sets
        // attributes of the frame.
        GamePanel gamePanel = new GamePanel();
        super.add(gamePanel);
        this.setPreferredSize(new Dimension(1000,600));
        this.setTitle("Assets.Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();            // fits jFrame snugly around all components added

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startGameThread();




    }


}

