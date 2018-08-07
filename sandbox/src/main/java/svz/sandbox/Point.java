package svz.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  double distance(double x, double y) {
    double ax = this.x - x;
    double ay = this.y - y;

    return Math.sqrt(Math.pow(ax, 2) + Math.pow(ay, 2));
  }

  public double distance(Point p) {
    return distance(p.x, p.y);
  }
}