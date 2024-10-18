package PracticePackage;

import java.util.Scanner;

public class CinemaTicketingSystem {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			Cinema cinema = new Cinema();

			System.out.println("\n     Welcome to RMAS Cinema Ticketing System!");
			System.out.println(" ------------------------------------------------\n");

			System.out.print(" --→ How many movies would you like to add? ");
			int numMovies = 0;

			while (true) {
				try {
					String input = scanner.nextLine();
					if (input.isEmpty()) {
						System.out.print("     Number of movies cannot be empty. Please enter a valid number. ");
						continue;
					}
					numMovies = Integer.parseInt(input);
					if (numMovies < 0) {
						System.out.print("     Enter a valid number of movies. ");
					} else {
						break;
					}
				} catch (NumberFormatException e) {
					System.out.print("     Invalid input. Numbers only. ");
				}
			}

			for (int i = 0; i < numMovies; i++) {
				System.out.println("\n --→ Details for movie " + (i + 1) + ":");
				String title = "";
				while (true) {
					System.out.print("     Enter movie title: ");
					title = scanner.nextLine();
					if (title.isEmpty()) {
						System.out.print("     Title cannot be empty. Please enter a valid input. --→ ");
						continue;
					} else {
						break;
					}
				}

				String genre = "";
				while (true) {
					System.out.print("     Enter movie genre: ");
					genre = scanner.nextLine();
					if (genre.isEmpty()) {
						System.out.print("     Genre cannot be empty. Please enter a valid input. --→ ");
						continue;
					} else if (!genre.matches("[a-zA-Z ]+")) {
						System.out.print("     Please enter a valid input (letters and spaces only). --→ ");
					} else {
						break;
					}
				}

				double price = 0.0;
				while (true) {
					System.out.print("     Enter movie price: ");
					try {
						String priceStr = scanner.nextLine();
						if (priceStr.isEmpty()) {
							System.out.print("     Input cannot be empty. Please enter a valid price. --→ ");
							continue;
						}
						price = Double.parseDouble(priceStr);
						if (price < 0) {
							System.out.print("     Enter a valid price. --→ ");
						} else {
							break;
						}
					} catch (NumberFormatException e) {
						System.out.print("    Invalid input. Numbers only. --→ ");
					}
				}

				cinema.addMovie(title, genre, price);
				System.out.println(" --→ Movie " + (i + 1) + " added successfully.");
			}

			System.out.println("\n --→ All Available Movies: ");
			for (int i = 0; i < numMovies; i++) {
				System.out.println(cinema.getMovie(i));
			}

			for (int i = 0; i < numMovies; i++) {
				System.out.println(
						"\n --→ Booking tickets for movie " + (i + 1) + ": " + cinema.getMovie(i).getTitle());
				System.out.print("     How many tickets would you like to book for this movie? ");
				int numTickets = 0;

				while (true) {
					try {
						String input = scanner.nextLine();
						if (input.isEmpty()) {
							System.out.print("     Input cannot be empty. Please enter a valid number. --→ ");
							continue;
						}
						numTickets = Integer.parseInt(input);
						if (numTickets <= 0) {
							System.out.print("     Please enter a valid number of tickets. --→ ");
						} else {
							break;
						}
					} catch (NumberFormatException e) {
						System.out.print("     Invalid input. Numbers only. --→ ");
					}
				}

				cinema.bookTicket(cinema.getMovie(i).getTitle(), numTickets, scanner);
			}
		}
		System.out.println("\n     Thank you for using RMAS Cinema Ticketing System! Enjoy watching your movie!");
	}
}
