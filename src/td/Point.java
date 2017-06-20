
package td;

/**
*Klasa którą wykorzystuję jako punkt na współrzędnej płaszczyznie.
* @author w55182
* @version 1.0
*/
public class Point {
    
    /**Współrzędna x.*/
    private double x;
    
    /**Współrzędna y.*/
    private double y;
    
    /**
     * Tworzy obiekt klasy {@link Point} za pomocy parametrów x oraz y.
     * @param x współrzędna x
     * @param y współrzędna y
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    
    /**
     * Tworzy obiekt klasy {@link Point} za pomocy parametru obj.
     * @param obj łącze na objekt klasy {@link Point}.
     */
    public Point(Point obj){
        this(obj.x, obj.y);
    }
    
    
    /**
     * Nadaje {@link Point#x} znaczenie parametru x.
     * @param x współrzędna x
     */
    public void setX(double x){
        this.x = x;
    }
    
    /**
     * Zwraca znaczenie {@link Point#x}.
     * @return współrzędna x
     */
    public double getX(){
        return this.x;
    }
    
    /**
     * Nadaje {@link Point#y} znaczenie parametru y.
     * @param y współrzędna y
     */
    public void setY(double y){
        this.y = y;
    }
    
    /**
     * Zwraca znaczenie {@link Point#y}.
     * @return współrzędna y
     */
    public double getY(){
        return this.y;
    }
    
    /**
     * Robi owinięcie punktu na pewny kąt nawkoło innego nunktu.
     * @param obj punkt, który będzie owinięty.
     * @param center punkt, nawkoło jakiego będzie odbyać się owinięcie.
     * @param angle kąt.
     * @return owinięty punkt.
     */
    public static Point rotatePointByCenter(Point obj, Point center, double angle){
        return new Point(center.getX()+(obj.getX()-center.getX())*Math.cos(angle)+
                (center.getY()-obj.getY())*Math.sin(angle),
                         center.getY()+(obj.getX()-center.getX())*Math.sin(angle)+
                (obj.getY()-center.getY())*Math.cos(angle));
    }
    
    /**
     * Zwraca true, jeżeli parametr obj jest równy obiektu, który wywołuję metodę.
     * Zwaraca false w innym przypadku.
     * @param obj obiekt dla porównania.
     * @return wartość typu boolean
     */
    @Override
    public boolean equals(Object obj){
        Point p = (Point)obj;
        return (this.x == p.x && this.y == p.y);
    }
}
