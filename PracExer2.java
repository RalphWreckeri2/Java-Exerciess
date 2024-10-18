package PracticePackage;

import java.util.Scanner;

public class PracExer2 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);

		System.out.println("Enter details for your first product: ");
		
		System.out.print("Price: ");
		double price1 = myObj.nextDouble();
		while (price1 <= 0) {
			System.out.print("Please enter a positive number for price: ");
			price1 = myObj.nextDouble();
		}
		
		System.out.print("Quantity: ");
		int quantity1 = myObj.nextInt();
		while (quantity1 <= 0) {
			System.out.print("Please enter a positive number for quantity: ");
			quantity1 = myObj.nextInt();
		}

		System.out.println("Enter details for your second product: ");
		
		System.out.print("Price: ");
		double price2 = myObj.nextDouble();
		while (price2 <= 0) {
			System.out.print("Please enter a positive number for price: ");
			price2 = myObj.nextDouble();
		}
		
		System.out.print("Quantity: ");
		int quantity2 = myObj.nextInt();
		while (quantity2 <= 0) {
			System.out.print("Please enter a positive number for quantity: ");
			quantity2 = myObj.nextInt();
		}

		System.out.println("Enter details for your third product: ");
		
		System.out.print("Price: ");
		double price3 = myObj.nextDouble();
		while (price3 <= 0) {
			System.out.print("Please enter a positive number for price: ");
			price3 = myObj.nextDouble();
		}
		
		System.out.print("Quantity: ");
		int quantity3 = myObj.nextInt();
		while (quantity3 <= 0) {
			System.out.print("Please enter a positive number for quantity: ");
			quantity3 = myObj.nextInt();
		}

		double revenue1 = price1 * quantity1;
		double revenue2 = price2 * quantity2;
		double revenue3 = price3 * quantity3;
		double totalRevenue = revenue1 + revenue2 + revenue3;

		double discount;

		if (totalRevenue <= 5000) {
			discount = 0.0;
		} else if (totalRevenue >= 5001 && totalRevenue <= 10000) {
			discount = 0.05;
		} else {
			discount = 0.10;
		}

		double netRevenue1 = revenue1 - (revenue1 * discount);
		double netRevenue2 = revenue2 - (revenue2 * discount);
		double netRevenue3 = revenue3 - (revenue3 * discount);
		double totalDiscount = (revenue1 + revenue2 + revenue3) - (netRevenue1 + netRevenue2 + netRevenue3);
		double totalNetRevenue = netRevenue1 + netRevenue2 + netRevenue3;

		System.out.printf("Total Revenue: %.2f\n", totalRevenue);
		System.out.printf("Total Discount: %.2f\n", totalDiscount);
		System.out.printf("Total Net Revenue: %.2f\n", totalNetRevenue);

		if (totalNetRevenue > 15000) {
			System.out.println("The revenue is excellent!");
		} else {
			System.out.println("The revenue is below expectations.");
		}
	}

}
