import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class GameFrame extends JFrame {

    GameFrame() throws IOException {

        GamePanel gamePanel = new GamePanel();
        super.add(gamePanel);
        this.setPreferredSize(new Dimension(1000,600));
        this.setTitle("Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();            // fits jframe snuggly around all components added

        this.setLocationRelativeTo(null);
        this.setVisible(true);

        gamePanel.startGameThread();

    }


}

