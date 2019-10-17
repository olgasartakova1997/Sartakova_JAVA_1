package st.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static st.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTests5 {
  @Test
  public void testDistance5() {
    Point p1 = new Point(4.0,8.0);
    Point p2 = new Point(4.0,8.0);
    Assert.assertEquals(p1.distance(p2),0.0);
  }
}
