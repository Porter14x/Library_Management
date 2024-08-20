package Library_Management;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Author> authors = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    
    public static void displayBooks() {
        for (Book book : books) {
            book.display();
        }
    }

    public static void displayAuthors() {
        String output = null;

        for (int i = 0; i < authors.size(); i++) {
            Integer hold = i+1;
            output = (hold).toString() + ". " + authors.get(i).getName();
            System.out.println(output);
        }
    }

    public static void displayGenres() {
        Genre[] genres = Genre.values();
        for (Genre genre : genres) {
            System.out.println(genre.toString());
        }
    }

    public static void prepareLoan(String loanId, User loggedUser) {
        for (Book book : books) {
            if (book.getBookId().equals(loanId) && !book.isOnLoan()) {
                loggedUser.loanBook(book);
                System.out.println("Loaned book " + book.getTitle());
                return;
            } else if (book.isOnLoan()) {
                System.out.println("Book is already on loan");
                return;
            }
        }
        System.out.println("Book with inputted id not found");
    }

    public static void subscribeAuthorsBooks(String authorSubscribe, User user) {
        for (Book book : books) {
            if (book.getAuthorName().equals(authorSubscribe)) {
                user.subscribeBook(book);
            }
        }
    }

    public static void subscribeGenreBooks(Genre genreSubscribe, User user) {
        for (Book book : books) {
            if (book.getGenre().equals(genreSubscribe)) {
                user.subscribeBook(book);
            }
        }
    }

    public static void main(String[] args) {
        User loggedUser = null;
        Scanner input = new Scanner(System.in);

        books.add(new Book("Give Up", Genre.BIOGRAPHY, "Jimmy Doomer", 10));
        books.add(new Book("Bames Jond", Genre.SPY, "Arnold Coughing", 500));
        books.add(new Book("Lord Of The Ka-Ching", Genre.FANTASY, "Josh Talking", 3600));
        books.add(new Book("Hate", Genre.ROMANCE, "H.R Revenge", 75));
        books.add(new Book("Ster Walk", Genre.SCI_FI, "Donald Glover", 200));
        books.add(new Book("Ster Conflict", Genre.SCI_FI, "Donald Glover", 201));

        authors.add(new Author("Jimmy Doomer"));
        authors.add(new Author("Arnold Coughing"));
        authors.add(new Author("Josh Talking"));
        authors.add(new Author("H.R Revenge"));
        authors.add(new Author("Donald Glover"));

        users.add(new User("user1"));
        users.add(new User("user2"));

        while (loggedUser == null) {
            String choice = null;
            String logUsername = null;
            System.out.println("Please select an operation:");
            System.out.println("1. Log In");
            System.out.println("2. Exit\n");
            choice = input.nextLine();
            // white space for readability
            System.out.println();

            switch (choice) {
                case "1":
                System.out.println("Please enter your username: ");
                logUsername = input.nextLine();
                // white space for readability
                System.out.println();

                for (User user : users) {
                    if (user.getUsername().equals(logUsername)) {
                        loggedUser = user;
                        break;
                    }
                }

                if (loggedUser == null) {
                    System.out.println("User not found");
                }
                break;

                case "2":
                System.out.println("Exiting...");
                return;

                default:
                System.out.println("Unrecognised command, please try again");
            }
        }

        while (loggedUser != null) {
            String desc = null;
            System.out.println("Please select an operation:");
            System.out.println("1. Display Books");
            System.out.println("2. Loan Book");
            System.out.println("3. Watchlist Book(s)");
            System.out.println("4. Return Book");
            System.out.println("5. Logout");

            desc = input.nextLine();
            // white space for readability
            System.out.println();

            switch (desc) {
                case "1":
                displayBooks();
                break;

                case "2":
                String loanId = null;
                System.out.println("Please enter the ID of the book you wish to loan: ");
                loanId = input.nextLine();
                // white space for readability
                System.out.println();
                prepareLoan(loanId, loggedUser);
                break;

                case "3":
                String watchDesc = null;
                System.out.println("Please select how you would like to watchlist: ");
                System.out.println("1. By Author");
                System.out.println("2. By Genre");
                System.out.println("3. By BookId");
                watchDesc = input.nextLine();
                // white space for readability
                System.out.println();

                switch (watchDesc) {
                    case "1":
                    displayAuthors();
                    System.out.println("Please select an author's books to watchlist");
                    int authDesc;
                    authDesc = input.nextInt() - 1;
                    // white space for readability
                    System.out.println();
                    while (authDesc < 0 || authDesc > (authors.size())) {
                        System.out.println("Please enter a valid index: ");
                        authDesc = input.nextInt() - 1;
                        // white space for readability
                        System.out.println();
                    }
                    String authorSubscribe = authors.get(authDesc).getName();
                    System.out.println("Subscribing to author " + authorSubscribe);
                    subscribeAuthorsBooks(authorSubscribe, loggedUser);
                    break;

                    case "2":
                    displayGenres();
                    System.out.println("Please select a genre to watchlist: ");
                    int genDesc;
                    genDesc = input.nextInt() - 1;
                    // white space for readability
                    System.out.println();
                    while (genDesc < 0 || genDesc > (Genre.values().length)) {
                        System.out.println("Please enter a valid index: ");
                        genDesc = input.nextInt() - 1;
                        // white space for readability
                        System.out.println();
                    }
                    Genre genreSubscribe = Genre.values()[genDesc];
                    System.out.println("Subscribing to genre " + genreSubscribe.toString());
                    subscribeGenreBooks(genreSubscribe, loggedUser);
                    break;

                    case "3":
                    System.out.println("Please enter an id eg 'BK1': ");
                    String bkDesc = input.nextLine();
                    // white space for readability
                    System.out.println();
                    for (Book book : books) {
                        if (book.getBookId().equals(bkDesc)) {
                            loggedUser.subscribeBook(book);
                            break;
                        }
                    }
                    System.out.println("BookId not found");
                    break;

                }
                break;

                case "4":
                if (loggedUser.getLoanedBooks().isEmpty()) {
                    System.out.println("You have no books to return");
                    break;
                }

                loggedUser.displayLoanedBooks();
                System.out.println("Please select a book to return: ");
                int rtnDesc;
                rtnDesc = input.nextInt()-1;
                while (rtnDesc < 0 || rtnDesc > (loggedUser.getLoanedBooks().size())) {
                    System.out.println("Please enter a valid index: ");
                    rtnDesc = input.nextInt() - 1;
                    // white space for readability
                    System.out.println();
                }
                System.out.println("Returning book " + loggedUser.getLoanedBooks().get(rtnDesc).getTitle());
                loggedUser.releaseBook(loggedUser.getLoanedBooks().get(rtnDesc));
                break;

                case "5":
                System.out.println("Logging out...");
                loggedUser=null;
                break;

                default:
                System.out.println("Unrecognised command, please enter a correct value");
                break;

            }
        }
    }
}
