package st.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static st.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTests5 {
  @Test
  public void testDistance5() {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 4;
    p1.y = 8;
    p2.x = 4;
    p2.y = 8;
    Assert.assertEquals(distance(p1,p2),0.0);
  }
}
