import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите своё ФИО и дату рождения через пробел на русском языке: ");
        Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
        try{
            System.out.println(new Person(enter));
        }
        catch(InvalidParameterException ex) {
            System.out.println(ex);
        }
    }
}