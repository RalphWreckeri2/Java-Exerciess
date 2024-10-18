package OOP; 
import java.util.Scanner; 
public class PersonalInfoCalculator {
  public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);

    System.out.println("Enter name: ");
    String name = myObj.nextLine();

    System.out.println("Enter age: ");
    int age = myObj.nextInt();

    System.out.println("Enter salary: ");
    double monthlySalary = myObj.nextDouble();

    double annual = monthlySalary * 12;
    double tax = 0.10;
    double taxAmount = monthlySalary * tax;
    double net = monthlySalary - taxAmount;
       
    System.out.println("Name: " + name);
    System.out.println("Age: " + age);
    System.out.println("Monthly Salary: " + monthlySalary);
    System.out.println("Annual Salary: " + annual);
    System.out.println("Tax Amount: " + taxAmount); 
    System.out.println("Net Salary: " + net);       
  }
}
