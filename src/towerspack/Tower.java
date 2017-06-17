
package towerspack;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.TimerTask;
import mobspack.Mob;
import td.MFrame;
import td.Point;

public abstract class Tower extends Thread{
    protected Point center;
    protected static Point size = new Point(60, 60);
    protected int attackSpeed;
    protected int attackDamage;
    protected final int maxLvl = 9;
    protected int currentLvl;
    protected int currentPriceOfUpdate;
    protected int range;
    protected MFrame frame;
    protected boolean mobIn = false;
    protected ArrayList<Mob> mobs = new ArrayList<Mob>();
    protected boolean updated;
    protected boolean labeled;
     
    public void setUpdated(boolean obj){
        this.updated = obj;
    }
    
    public static Point getSize(){
        return size;
    }
    
    public Tower(MFrame frame, Point center, ArrayList<Mob> mobs){
        this.center = new Point(center.getX(), center.getY());
        this.attackSpeed = 500;
        this.attackDamage = 0;
        this.currentPriceOfUpdate = 0;
        this.currentLvl = 0;
        this.range = 150;
        this.frame = frame;
        this.mobs = mobs;
        this.updated = false;
        this.labeled = false;
    }
   
    public void setLabeled(boolean obj){
        this.labeled = obj;
    }
    
    public boolean getLabeled(){
        return this.labeled;
    }
    
    public int getMaxLvl(){
        return this.maxLvl;
    }
    
    public int getAttackDamage(){
        return attackDamage;
    }
    
    public int getCurrentLvl(){
        return currentLvl;
    }
    
    public Point getCenter(){
        return this.center;
    }
    
    public int getCurrentPriceOFUpdate(){
        return this.currentPriceOfUpdate;
    }
    
    public boolean containsPoint(Point p){
        Polygon polygon = new Polygon();
        polygon.addPoint((int)(center.getX()-size.getX()), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()-size.getY()));
        polygon.addPoint((int)(center.getX()+size.getX()), (int)center.getY());
        polygon.addPoint((int)(center.getX()), (int)(center.getY()+size.getY()));
        if(polygon.contains(p.getX(), p.getY()))
            return true;
        return false;
    }
    
    private class Shoot implements Runnable{

        private Mob mob;
        private Point centerS;
        private int moveSpeed;
        private boolean over = false;
        
        public Shoot(Mob mob, Point center){
            this.mob = mob;
            this.centerS = new Point(center.getX()+15, center.getY()+40);
            this.moveSpeed = 1;
        }
        
        public void move(){
        
            Point centerM = new Point(mob.getCenter().getX()+15, mob.getCenter().getY()+40);
            double a = centerS.getX() - centerM.getX();
            double b = centerS.getY() - centerM.getY();
            double len = Math.sqrt(a * a + b * b);
            a = a / len * moveSpeed;
            b = b / len * moveSpeed;
            centerS.setX(centerS.getX()-a);
            centerS.setY(centerS.getY()-b);
        }
        
        @Override
        public void run() {
            java.util.Timer timer = new java.util.Timer();//создание таймера
            timer.schedule(new TimerTask(){//timer, на 3 секунды, по окончанию которого закончится перезарядка
                @Override
                    public void run() {
                        over = true;
                    }
            },300);
            while(!mob.containsPoint(centerS)&&!over){
                move();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {}
                paint();
            }
            mob.setCurrentHealthPoints(mob.getCurrentHealthPoints()-attackDamage);
        }
        
        public void paint(){
            Graphics2D g2 = (Graphics2D) frame.getGraphics();
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.ORANGE);
            g2.drawLine((int)centerS.getX(), (int)centerS.getY(), (int)centerS.getX(), (int)centerS.getY());
            g2.setStroke(new BasicStroke(1));
        }
        
    }
    
    public boolean mobInRange(Mob mob){
        Ellipse2D rangeEllipse = new Ellipse2D.Double();
        rangeEllipse.setFrameFromCenter(center.getX(), center.getY(), center.getX()+range, center.getY()+range);
        
        if(rangeEllipse.contains(mob.getCenter().getX(), mob.getCenter().getY()))
            return true;
        return false;
    }
    
    @Override
    public void run(){
        while(!updated){
            if(!mobs.isEmpty()){
                for(int i=0;i<mobs.size();i++){
                    if(mobInRange(mobs.get(i))){
                        new Thread(new Shoot(frame.getGamePanel().getField().getMobs().get(i),center)).start();
                        break;
                    }
                }
                try {
                    Thread.sleep(attackSpeed);
                } catch (InterruptedException ex) {}
            }
        }
    }
    
    public abstract void paint(Graphics g);
}
