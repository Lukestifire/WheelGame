import javax.swing.*;
import java.io.IOException;


public class GameFrame extends JFrame {

    GameFrame() throws IOException {

        this.add(new GamePanel());
        this.setTitle("Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();            // fits jframe snuggly around all components added
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}

