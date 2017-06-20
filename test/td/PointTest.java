/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package td;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1
 */
public class PointTest {
    
    public PointTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getX method, of class Point.
     */
    @Test
    public void testGetX1() {
        System.out.println("getX");
        Point instance = new Point(1, 0);
        double expResult = 1;
        double result = instance.getX();
        assertEquals(new Double(expResult), new Double(result));
    }

    /**
     * Test of getX method, of class Point.
     */
    @Test
    public void testGetX2() {
        System.out.println("getX");
        Point instance = new Point(100, 0);
        double expResult = 100;
        double result = instance.getX();
        assertEquals(new Double(expResult), new Double(result));
    }
    
    /**
     * Test of getY method, of class Point.
     */
    @Test
    public void testGetY1() {
        System.out.println("getY");
        Point instance = new Point(0, 1);
        double expResult = 1;
        double result = instance.getY();
        assertEquals(new Double(expResult), new Double(result));
    }
    
    /**
     * Test of getY method, of class Point.
     */
    @Test
    public void testGetY2() {
        System.out.println("getY");
        Point instance = new Point(0, 50);
        double expResult = 50;
        double result = instance.getY();
        assertEquals(new Double(expResult), new Double(result));
    }

    /**
     * Test of rotatePointByCenter method, of class Point.
     */
    @Test
    public void testRotatePointByCenter1() {
        System.out.println("rotatePointByCenter");
        Point obj = new Point(1,1);
        Point center = new Point(1,3);
        double angle = Math.PI/2;
        Point expResult = new Point(3, 3);
        Point result = Point.rotatePointByCenter(obj, center, angle);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of rotatePointByCenter method, of class Point.
     */
    @Test
    public void testRotatePointByCenter2() {
        System.out.println("rotatePointByCenter");
        Point obj = new Point(5,2);
        Point center = new Point(5,5);
        double angle = -Math.PI/2;
        Point expResult = new Point(2, 5);
        Point result = Point.rotatePointByCenter(obj, center, angle);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Point.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Point(1, 1);
        Point instance = new Point(1, 1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}
