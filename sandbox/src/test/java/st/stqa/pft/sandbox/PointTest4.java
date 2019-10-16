package st.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static st.stqa.pft.sandbox.MyFirstProgram.distance;

public class PointTest4 {
  @Test
  public void testDistance3() {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = -6;
    p1.y = 4;
    p2.x = -3;
    p2.y = 6;
    Assert.assertEquals(distance(p1,p2),-4);
  }
}
