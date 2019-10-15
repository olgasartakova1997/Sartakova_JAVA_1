package st.stqa.pft.sandbox;

public class MyFirstProgram
{
   public static void main(String[] args){
  hello("Ольга");
  hello("Ленка");
  hello("Захар");

  Square s = new Square(5);
     System.out.println("Площадь квадрата со стороной " + s.l + "=" + s.area());

  Rectangle d = new Rectangle(7,8);
     System.out.println("Площадь прямоугольника со сторонами " +d.a+" и "+d.b+ " = " + d.area());
}

  public static void hello(String somebody){
        System.out.println("Helo, " + somebody + "!");
  }

   }