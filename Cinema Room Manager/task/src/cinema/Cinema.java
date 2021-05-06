package cinema;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Cinema {
    // fields or properties
    static int numberOfRow;
    static int numberOfSeats;
    static int rowNumber;
    static int seatNumber;
    static int numberOfPurchasedTickets = 0;
    static float percentage = 0f;
    static int currentIncome = 0;
    static int totalIncome;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        numberOfRow = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numberOfSeats = scanner.nextInt();
        String[][] grid = new String[numberOfRow][numberOfSeats];
        createGrid(grid);
        for (int i = 0;; i++) {
            System.out.printf("%d. %s %n%d. %s %n%d. %s %n%d. %s %n", 1, "Show the seats", 2, "Buy a ticket", 3, "Statistics", 0, "Exit");
            switch (scanner.nextInt()) {
                case 1:
                    displayGrid(grid);
                    break;
                case 2:
                    buyTicket(grid);
                    break;
                case 3:
                    statistics();
                    break;
                case 0:
                    return;
            }
        }
    }

    static void menu(String[][] grid) {
        
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");

        int number = new Scanner(System.in).nextInt();
        switch (number) {
            case 1:
                displayGrid(grid);
                break;
            case 2:
                buyTicket(grid);
                break;
            default:
                System.exit(0);
        }
    }

    static void displayGrid(String[][] grid) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < numberOfRow; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < numberOfSeats; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    static void buyTicket(String[][] grid) {
        System.out.println("Enter a row number:");
        rowNumber = new Scanner(System.in).nextInt();
        System.out.println("Enter a seat number in that row:");
        seatNumber = new Scanner(System.in).nextInt();

        if (rowNumber <= numberOfRow && seatNumber <= numberOfSeats) {
            reserveSeat(grid);
        } else {
            System.out.println("Wrong input!");
            buyTicket(grid);
        }
    }
    static void calculateSeatsPrice() {

        System.out.print("Ticket price: ");
        int totalNumberOfSeats = numberOfSeats * numberOfRow;
        if (totalNumberOfSeats <= 60) {
            System.out.println("$" + 10);
            setCurrentIncome(10);
        } else {
            int firstRows = numberOfRow % 2 != 0 ? (numberOfRow - 1) / 2 : numberOfRow / 2;
            System.out.println("$" + (rowNumber <= firstRows ? 10 : 8));
            if (rowNumber <= firstRows) {
                setCurrentIncome(10);
            } else {
                setCurrentIncome(8);
            }

        }
    }

    static void createGrid(String[][] grid) {
        setTotalIncome();
        for (int i = 0; i < numberOfRow; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                grid[i][j] = " S";
            }
        }
    }

    static void reserveSeat(String[][] grid) {
        if (grid[rowNumber - 1][seatNumber - 1].equals(" B") || grid[rowNumber - 1][seatNumber - 1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyTicket(grid);
        } else {
            System.out.println("I am here");
            grid[rowNumber - 1][seatNumber - 1] = " B";
            numberOfPurchasedTickets++;
            calculateSeatsPrice();
//        displayGrid(grid);
        }
    }

    static float calculatePercentage() {
        percentage = 100f * numberOfPurchasedTickets / (float) Math.pow(numberOfSeats, 2);
        return percentage;
    }

    static void statistics() {
        System.out.printf("%s %d%n", "Number of purchased tickets: ", getNumberOfPurchasedTickets());
        System.out.printf("%s %.2f%s%n", "Percentage: ", calculatePercentage(), "%");
        System.out.printf("%s $%d%n", "Current income: ", getCurrentIncome());
        System.out.printf("%s $%d%n", "Total income:", getTotalIncome());
    }

    static int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    static int getCurrentIncome() {
        return currentIncome;
    }

    static void setCurrentIncome(int amount) {
        currentIncome += amount;
    }

    static int getTotalIncome() {
        return totalIncome;
    }

    static void setTotalIncome() {
        if (numberOfRow * numberOfSeats <= 60) {
            totalIncome = numberOfRow * numberOfSeats * 10;
        } else {
            totalIncome = numberOfRow * numberOfSeats * 10;
            int divide = numberOfRow % 2 != 0 ? ((numberOfRow - 1) / 2) + 1 : numberOfRow / 2;
            int newValue = numberOfRow * (divide * 2);
            totalIncome = totalIncome - newValue;
        }
    }
}