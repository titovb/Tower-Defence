
package td;


import java.awt.*;
import javax.swing.*;

public class MFrame extends JFrame{
    private GamePanel gamePanel;
    
    public GamePanel getGamePanel(){
        return gamePanel;
    }
    
    public MFrame(){
        Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(sSize);
        this.setExtendedState(MAXIMIZED_BOTH);
        gamePanel = new GamePanel(this);
        gamePanel.getField().createCastle();
        gamePanel.getField().createInformPanel();
    }
    
    public static void main(String[] args) {
        MFrame frame = new MFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }
    
}
