package PracticePackage;

import java.util.Scanner;

public class PracExer3 {

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        System.out.println("Welcome to XYZ Corporation!");
        System.out.print("Enter the number of employees: ");
        int employeeNumber = myObj.nextInt();
        myObj.nextLine(); 

        for (int i = 1; i <= employeeNumber; i++) {

            System.out.println();
            System.out.println("Entering details for employee " + i + ": ");

            System.out.print("Enter employee name: ");
            String employeeName = myObj.nextLine();

            System.out.print("Enter employee ID: ");
            int employeeId = myObj.nextInt();
            myObj.nextLine(); 

            System.out.print("Enter travel order destination: ");
            String employeeDestination = myObj.nextLine();

            System.out.print("Enter travel order number: ");
            String employeeOrderNumber = myObj.nextLine();

            System.out.println();
            System.out.println("Employee Details: ");
            System.out.println("Name: " + employeeName);
            System.out.println("ID: " + employeeId);
            
            System.out.println("Employment Pass: EP0000" + i);		

            System.out.println();
            System.out.println("Travel Order Details: ");
            System.out.println("Destination: " + employeeDestination);
            System.out.println("Order Number: " + employeeOrderNumber);
            System.out.println("Employee: " + employeeName);
        }

        myObj.close();
    }
}
