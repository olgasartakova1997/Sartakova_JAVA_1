package st.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static st.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTests {
  @Test
  public void testDistance(){
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 5;
    p1.y = 8;
    p2.x = 5;
    p2.y = 6;
    Assert.assertEquals(distance(p1,p2),2.0);
  }

}
