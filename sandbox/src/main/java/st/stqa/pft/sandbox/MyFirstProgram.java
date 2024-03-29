package st.stqa.pft.sandbox;

public class MyFirstProgram
{
  public static void main(String[] args) {
    Point p1 = new Point(7,8);
    Point p2 = new Point(5,6);
    System.out.println("Расстояние между двумя точками = " + distance(p1,p2));
    System.out.println("Расстояние между двумя точками = " + p1.distance(p2));
  }
  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + (Math.pow(p2.y - p1.y, 2)));
  }
}
