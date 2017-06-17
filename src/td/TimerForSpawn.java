/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

import javax.swing.JOptionPane;

/**
 *
 * @author 1
 */
public class TimerForSpawn extends Thread{
    private int m;
    private int s;
    private Field f;
    
    public TimerForSpawn(Field f){
        m=0;
        s=0;
        this.f = f;
    }
    
    @Override
    public void run(){
        while(true){
            if(s==60){
                m++;
                s=0;
            }
            if(s%20==0){
                f.upCurrentMobsLvl();
                f.createMobs();
            }
            if(f.getCastle().getCurrentHealthPoints()<=0){
                JOptionPane.showMessageDialog(null, "You Lost!", "Game Over", JOptionPane.OK_OPTION);
                System.exit(0);
            }
            if(f.getWin()){
                JOptionPane.showMessageDialog(null, "You Won!", "Game Over", JOptionPane.OK_OPTION);
                System.exit(0);
            }
            s++;
            try {
                sleep(1000);
            } catch (InterruptedException ex) {}
        }
    }
}
