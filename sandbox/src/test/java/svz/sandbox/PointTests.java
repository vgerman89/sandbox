package svz.sandbox;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testArea() {
    Point p1 = new Point(-2, -4);
    Point p2 = new Point(-1, 1);
    Assert.assertEquals(Point.distance(p1, p2), 5.0990195135927845);
  }
  @Test
  public void testArea1() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 1);
    Assert.assertEquals(Point.distance(p1, p2), 2.8284271247461903);
  }
  @Test
  public void testArea2(){
    Point p1 = new Point(5, 2);
    Point p2 = new Point(3, 5);
    double res = Math.sqrt(Math.pow(3 - 5, 2) + Math.pow(5 - 2, 2));
    Assert.assertEquals(Point.distance(p1, p2), res);
  }


}