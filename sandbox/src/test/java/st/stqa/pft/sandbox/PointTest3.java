package st.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static st.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTest3 {
  @Test
  public void testDistance3() {
    Point p1 = new Point(7.0,8.0);
    Point p2 = new Point(5.0,6.0);
    Assert.assertEquals(p1.distance(p2),5);
  }
}
