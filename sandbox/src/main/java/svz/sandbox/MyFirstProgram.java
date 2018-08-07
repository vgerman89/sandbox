package svz.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
/*  hello("world");
    hello("user");
    hello("Vitaliy");

    Square s = new Square(5);
    System.out.println("Площадь квадарта со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());
*/
    Point p1 = new Point(-2, -4);
    Point p2 = new Point(-1, 1);

    System.out.println("Точка A имеет координаты X = " + p1.x + ", Y = " + p1.y);
    System.out.println("Точка B имеет координаты X = " + p2.x + ", Y = " + p2.y);
    System.out.println("Расстояние между точками A и B составляет " + p1.distance(p2));

  }
/*
  public static void hello(String somebody) {System.out.println("Hello, " + somebody + "!");
  }
*/
}