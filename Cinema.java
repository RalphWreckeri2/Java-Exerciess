package PracticePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Movie {
    private String title;
    private String genre;
    private double price;

    public Movie(String title, String genre, double price) {
        this.title = title;
        this.genre = genre;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }
    
    @Override
    public String toString() {
        return "     Title: " + title + ", Genre: " + genre + ", Price: " + price;
    }
}

class Ticket {
    private int ticketNumber; 
    private int seatNumber; 
    private Movie movie; 

    public Ticket(int ticketNumber, Movie movie, int seatNumber) {
        this.ticketNumber = ticketNumber;
        this.movie = movie;
        this.seatNumber = seatNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket Number: " + ticketNumber + ", Seat: " + seatNumber + ", Movie: " + movie.getTitle();
    }
}

class Cinema {
    private List<Movie> movies;
    private List<Ticket> tickets;

    public Cinema() {
        movies = new ArrayList<>();
        tickets = new ArrayList<>();
    }

    public void addMovie(String title, String genre, double price) {
        Movie movie = new Movie(title, genre, price);
        movies.add(movie);
    }

    public Movie getMovie(int index) {
        return movies.get(index);
    }

    public void bookTicket(String movieTitle, int numOfTickets, Scanner scanner) {
        Movie selectedMovie = null;

        // Find the selected movie
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(movieTitle)) {
                selectedMovie = movie;
                break;
            }
        }

        if (selectedMovie == null) {
            System.out.println("Movie not found.");
            return;
        }

        int ticketNumber = 1; 

        for (int i = 0; i < numOfTickets; i++) {
            int seatNumber = 0;

            while (true) {
                System.out.print("\n --→ Enter seat number for ticket " + (i + 1) + ": ");
                try {
                    seatNumber = Integer.parseInt(scanner.nextLine());
                    if (seatNumber <= 0) {
                        System.out.println("     Seat number must be a positive integer.");
                    } else {
                        boolean seatTaken = false;
                        for (Ticket ticket : tickets) {
                            if (ticket.getSeatNumber() == seatNumber && ticket.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                                seatTaken = true;
                                System.out.println("     Seat number " + seatNumber + " is already taken. Please choose another seat.");
                                break;
                            }
                        }
                        if (!seatTaken) {
                            break; 
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("     Invalid seat number. Please enter a valid number.");
                }
            }

            Ticket ticket = new Ticket(ticketNumber, selectedMovie, seatNumber);
            tickets.add(ticket);

            System.out.println("     Booked: " + ticket);

            ticketNumber++;
        }
    }
}

