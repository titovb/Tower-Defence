
package td;

import exceptionpack.MobCreateException;
import mobspack.*;
import java.awt.*;
import java.util.*;
import towerspack.Tower;
import towerspack.TowerLvl1;

public class Field {
    private static Polygon roadForMobs;
    private ArrayList<Mob> mobs = new ArrayList<Mob>();
    private ArrayList<Tower> towers = new ArrayList<Tower>();
    private MFrame frame;
    private Castle castle = null;
    private InfoPanel information;
    private int currentMobsLvl;
    private TimerForSpawn timer;
    private boolean win = false;
    
    public Field(MFrame frame){
        this.frame = frame;
        this.currentMobsLvl = 0;
        initRoadForMobs();
        timer = new TimerForSpawn(this);
        timer.start();
    }
    
    private void initRoadForMobs(){
        roadForMobs = new Polygon();
        roadForMobs.addPoint(0, 12);
        roadForMobs.addPoint(1614, 12);
        roadForMobs.addPoint(1614, 1046);
        roadForMobs.addPoint(327, 1046);
        roadForMobs.addPoint(327, 165);
        roadForMobs.addPoint(1412, 165);
        roadForMobs.addPoint(1412, 882);
        roadForMobs.addPoint(517, 882);
        roadForMobs.addPoint(517, 295);
        roadForMobs.addPoint(1258, 295);
        roadForMobs.addPoint(1258, 747);
        roadForMobs.addPoint(680, 747);
        roadForMobs.addPoint(680, 420);
        roadForMobs.addPoint(1110, 420);
        roadForMobs.addPoint(1110, 623);
        roadForMobs.addPoint(832, 623);
        roadForMobs.addPoint(832, 494);
        roadForMobs.addPoint(758, 494);
        roadForMobs.addPoint(758, 688);
        roadForMobs.addPoint(1180, 688);
        roadForMobs.addPoint(1180, 358);
        roadForMobs.addPoint(606, 358);
        roadForMobs.addPoint(606, 810);
        roadForMobs.addPoint(1317, 810);
        roadForMobs.addPoint(1317, 235);
        roadForMobs.addPoint(418, 235);
        roadForMobs.addPoint(418, 965);
        roadForMobs.addPoint(1515, 965);
        roadForMobs.addPoint(1515, 90);
        roadForMobs.addPoint(0, 90);
    }
    
    public void createMobs(){
        new Thread(new Runnable(){
            @Override
            public void run() {
                long waitingForCreateNextMob = 400;
                for(int i=0;i<20;i++){
                    try {
                        Mob mob = chooseMobForCreate();
                        mob.start();
                        mobs.add(mob);
                        Thread.sleep(waitingForCreateNextMob);
                    } catch (InterruptedException | MobCreateException ex){}
                }
            }
        }).start();
    }
    
       private Mob chooseMobForCreate() throws MobCreateException{
        Mob returningMob = new MobLvl1(frame);
        switch(currentMobsLvl){
            case 1:
                returningMob = new MobLvl1(frame);
                break;
            case 2:
                returningMob = new MobLvl2(frame);
                break;
            case 3:
                returningMob = new MobLvl3(frame);
                break;
            case 4:
                returningMob = new MobLvl4(frame);
                break;
            case 5:
                returningMob = new MobLvl5(frame);
                break;
            case 6:
                returningMob = new MobLvl6(frame);
                break;
            case 7:
                returningMob = new MobLvl7(frame);
                break;
            case 8:
                returningMob = new MobLvl8(frame);
                break;
            case 9:
                returningMob = new MobLvl9(frame);
                break;
            default:
                throw new MobCreateException("Can't create, because current level of mobs become too hight!");
        }
        return returningMob;
      }
    
    public void createTower(Point click){
        if(roadForMobsDontContainPoint(click)){
            if(!towersContainsPoint(click)){
                Tower t = new TowerLvl1(frame, click, mobs);
                t.start();
                towers.add(t);
                information.setCurrentGold(information.getCurrentGold() - 50); //50 - currentPrice;
            }
        }
    }
    
       private boolean roadForMobsDontContainPoint(Point click){
        int allowedDifferenceForTowersHeight = 10;
        return !roadForMobs.contains(click.getX() - Tower.getSize().getX()/2, click.getY())&&
               !roadForMobs.contains(click.getX()                           , click.getY() - Tower.getSize().getY()/2 + allowedDifferenceForTowersHeight)&&
               !roadForMobs.contains(click.getX() + Tower.getSize().getX()/2, click.getY())&&
               !roadForMobs.contains(click.getX()                           , click.getY() + Tower.getSize().getY()/2);
     }
     
       private boolean towersContainsPoint(Point click){
        for(int i=0;i<towers.size();i++){
            if(towers.get(i).containsPoint(click)){
                return true;
            }
        }
        return false;
     }
    
    public void createInformPanel(){
        information = new InfoPanel(frame);
    }
    
    public void createCastle(){
        castle = new Castle(frame);
    }
    
    public void upCurrentMobsLvl(){
        if(currentMobsLvl<10)
            currentMobsLvl++;
    }
    
    public boolean getWin(){
        return this.win;
    }
    
    public int getCurrentMobsLvl(){
        return this.currentMobsLvl;
    }
    
    public Castle getCastle(){
        return this.castle;
    }
    
    public ArrayList<Mob> getMobs(){
        return mobs;
    }
    
    public ArrayList<Tower> getTowers(){
        return towers;
    }
    
    public InfoPanel getInfoPanel(){
        return information;
    }
    
    public static Polygon getRoadForMobs(){
        return roadForMobs;
    }
    
    public void paintField(Graphics g){
        Image img;
        String adr = "images\\field\\Field.png";
            
        img = Toolkit.getDefaultToolkit().getImage(adr);
            
        g.drawImage(img, 0, 0, frame.getWidth(), frame.getHeight()-40, frame.getGamePanel().getPanel());
        
        paintMobs(g);
        
        if(mobs.size()==1&&currentMobsLvl == 10){
            win = true;
        }
        
        paintTowers(g);
        
        castle.paintCastle(g);
        information.paint(g);
    }
    
       private void paintMobs(Graphics g){
        if(!mobs.isEmpty()){
            for(int i=0;i<mobs.size();i++){
                if(mobs.get(i).getDead()){
                    information.setCurrentGold(information.getCurrentGold()+mobs.get(i).getPriceForKill());
                    mobs.remove(i);
                }
                else{
                    mobs.get(i).paint(g);
                }
            }
        }
       }
       
       private void paintTowers(Graphics g){
        if(!towers.isEmpty())
            for(int i=0;i<towers.size();i++){
                towers.get(i).paint(g);
            }
       }
}