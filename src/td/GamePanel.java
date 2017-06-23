
package td;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import towerspack.*;

public class GamePanel{
    
    private Point size;
    private JPanel panel;
    private Field gameField;
    private Tower pickedTower;
    private int indexOfPickedTower;
    
    private class paintGP extends JPanel{
        
        @Override
        protected void paintComponent(Graphics g){
            if(gameField!=null)
                gameField.paintField(g);
        }
    }
    
    public GamePanel(MFrame frame){
        size = new Point(frame.getWidth(), frame.getHeight());
        panel = new JPanel(new BorderLayout());
        panel.add(new paintGP(), BorderLayout.CENTER);
        panel.setFocusable(true);
        panel.setVisible(true);
        panel.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i=0;i<gameField.getTowers().size();i++){
                    if(gameField.getTowers().get(i).containsPoint(new Point(e.getX(), e.getY()))){
                        if(pickedTower!=null){
                            gameField.getInfoPanel().towerReleased();
                            pickedTower.setLabeled(false);
                        }
                        pickedTower = gameField.getTowers().get(i);
                        pickedTower.setLabeled(true);
                        indexOfPickedTower = i;
                        gameField.getInfoPanel().towerPressed(pickedTower);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(gameField.getInfoPanel().getCurrentGold()>=50){ // 50 - price of buying tower 1 lvl.
                    Point click = new Point(e.getX(), e.getY());
                    gameField.createTower(click);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
        panel.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(KeyEvent.getKeyText(e.getKeyCode()).equals("U")){
                    if(pickedTower.getCurrentLvl()!=pickedTower.getMaxLvl())
                        if(gameField.getInfoPanel().getCurrentGold() >= pickedTower.getCurrentPriceOFUpdate()){
                            gameField.getInfoPanel().setCurrentGold(gameField.getInfoPanel().getCurrentGold()-pickedTower.getCurrentPriceOFUpdate());
                            towerUP();
                        }
                }
                    
            }
            
            private void towerUP(){
                pickedTower.setUpdated(true);
                switch(pickedTower.getCurrentLvl()){
                    case 1:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl2(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 2:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl3(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 3:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl4(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 4:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl5(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 5:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl6(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 6:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl7(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 7:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl8(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    case 8:
                        gameField.getTowers().set(indexOfPickedTower, new TowerLvl9(frame, pickedTower.getCenter(), gameField.getMobs()));
                        break;
                    default:
                        break;
                }
                pickedTower = gameField.getTowers().get(indexOfPickedTower);
                pickedTower.setLabeled(true);
                pickedTower.start();
                gameField.getInfoPanel().towerReleased();
                gameField.getInfoPanel().towerPressed(pickedTower);
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            
        });
        frame.setContentPane(panel);
        frame.repaint();
        gameField = new Field(frame);
    }
    
    public Field getField(){
        return gameField;
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
}
