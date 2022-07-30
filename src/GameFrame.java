import javax.swing.*;


public class GameFrame extends JFrame {

    GameFrame() {

        this.add(new GamePanel());
        this.setTitle("Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();            // fits jframe snuggly around all components added
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


}

