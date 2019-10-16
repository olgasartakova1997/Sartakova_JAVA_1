package st.stqa.pft.sandbox;

public class MyFirstProgram
{
  public static void main(String[] args) {
    Point p1 = new Point();
    Point p2 = new Point();
    p1.x = 4;
    p1.y = 6;
    p2.x = 12;
    p2.y = 8;
    System.out.println("Расстояние между двумя точками = " + distance(p1,p2));
  }
  public static double distance(Point p1, Point p2){
    return Math.sqrt(Math.pow(p2.x - p1.x, 2) + (Math.pow(p2.y - p1.y, 2)));
  }
}
