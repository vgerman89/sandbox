package svz.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    String[] langs = {"JAVA", "C#", "PYTHON", "PHP"};

    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");

    for (String l : languages){
      System.out.println("Я хочу выучить " +l);
    }
  }
}
