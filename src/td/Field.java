
package td;

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
                for(int i=0;i<20;i++){
                    boolean Default = false;
                    Mob mob = new MobLvl2(frame);
                    switch(currentMobsLvl){
                        case 1:
                            mob = new MobLvl1(frame);
                            break;
                        case 2:
                            mob = new MobLvl2(frame);
                            break;
                        case 3:
                            mob = new MobLvl3(frame);
                            break;
                        case 4:
                            mob = new MobLvl4(frame);
                            break;
                        case 5:
                            mob = new MobLvl5(frame);
                            break;
                        case 6:
                            mob = new MobLvl6(frame);
                            break;
                        case 7:
                            mob = new MobLvl7(frame);
                            break;
                        case 8:
                            mob = new MobLvl8(frame);
                            break;
                        case 9:
                            mob = new MobLvl9(frame);
                            break;
                        default:
                            Default = true;
                            break;
                    }
                    if(!Default){
                        mob.start();
                        mobs.add(mob);
                    }
                    try {
                        Thread.sleep((long) (30*mob.getSize().getX()/3));
                    } catch (InterruptedException ex) {}
                }
            }
        }).start();
    }
    
    public void createTower(double x, double y){
        boolean willDo = true;
        if(!roadForMobs.contains(x - Tower.getSize().getX()/2, y)&&
           !roadForMobs.contains(x, y - Tower.getSize().getY()/2+10)&&
           !roadForMobs.contains(x + Tower.getSize().getX()/2, y)&&
           !roadForMobs.contains(x, y + Tower.getSize().getY()/2)){
           
            for(int i=0;i<towers.size();i++){
                if(towers.get(i).containsPoint(new Point(x, y))){
                    willDo = false;
                    break;
                }
            }
            if(willDo){
                Tower t = new TowerLvl1(frame, new Point(x, y), mobs);
                t.start();
                towers.add(t);
                information.setCurrentGold(information.getCurrentGold()-50);
            }
        }
    }
    
    public void createInformPanel(){
        information = new InfoPanel(frame);
    }
    
    public void createCastle(){
        castle = new Castle(frame);
    }
    
    public Field(MFrame frame){
        this.frame = frame;
        this.currentMobsLvl = 0;
        initRoadForMobs();
        timer = new TimerForSpawn(this);
        timer.start();
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
    
    public void paint(Graphics g){
        Image img;
        String adr = "images\\field\\Field.png";
            
        img = Toolkit.getDefaultToolkit().getImage(adr);
            
        g.drawImage(img, 0, 0, frame.getWidth(), frame.getHeight()-40, frame.getGamePanel().getPanel());
        
        if(!mobs.isEmpty())
            for(int i=0;i<mobs.size();i++){
                if(mobs.get(i).getDead()){
                    information.setCurrentGold(information.getCurrentGold()+mobs.get(i).getPriceForKill());
                    mobs.remove(i);
                }
                else{
                    mobs.get(i).paint(g);
                }
            }
        if(mobs.size()==1&&currentMobsLvl == 10){
            win = true;
        }
        if(!towers.isEmpty())
            for(int i=0;i<towers.size();i++){
                towers.get(i).paint(g);
            }
        if(castle!=null)
            castle.paint(g);
        if(information!=null)
            information.paint(g);
    }
}
