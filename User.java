package Library_Management;

import java.util.ArrayList;

public class User implements Observer{
    
    private static final String USER_ID_START = "USR";
    private static final int LOAN_LIMIT = 3;
    static Integer currId = 0;

    private String userId;
    private String username;
    private ArrayList<Book> loanedBooks = new ArrayList<Book>();
    private ArrayList<Book> watchingBooks = new ArrayList<>();

    public User(String username) {
        currId++;
        this.username = username;
        this.userId = (USER_ID_START + currId.toString());
    }

    @Override
    public void update(String message) {
        System.out.println(message);
    }

    public void watchingBooksStatus(){

        if (watchingBooks.isEmpty()){
            System.out.println("You have no books on your watching list");
            return;
        }

        String onLoan = "On Loan:\n";
        String available = "Available:\n";

        for (Book book : watchingBooks) {
            if (book.isOnLoan()) {
                onLoan += book.getTitle() + "\n";
            } else {
                available += book.getTitle() + "\n";
            }
        }

        System.out.println(onLoan);
        System.out.println(available);
    }

    public void loanBook(Book book) {
        if (!book.isOnLoan()) {
            if (loanedBooks.size() < LOAN_LIMIT) {
                loanedBooks.add(book);
                book.setOnLoan(true);
                book.notifyBookLoaned();
            } else {
                System.out.println("You are at the allowed limit of book loans");
                return;
            }
        } else if (loanedBooks.contains(book)) {
            System.out.println("You are already loaning this book");
        } else {
            System.out.println("This book is already on loan");
        }
    }

    public void releaseBook(Book book) {
        if (!loanedBooks.contains(book)) {
            System.out.println("You do not have this book on loan");
        } else {
            loanedBooks.remove(book);
            book.setOnLoan(false);
            book.notifyBookAvailable();
        }
    }

    public void subscribeBook(Book book) {
        if (watchingBooks.contains(book)) {
            System.out.println("You are already watching this book");
        } else {
            book.addObserver(this);
            watchingBooks.add(book);
        }
    }

    public void unsubscribeBook(Book book) {
        if (!watchingBooks.contains(book)) {
            System.out.println("This book is already not on your watchlist");
        } else {
            book.removeObserver(this);
            watchingBooks.remove(book);
        }
    }

    public void displayLoanedBooks() {
        if (loanedBooks.isEmpty()) {
            System.out.println("You have no books on loan");
            return;
        }

        Integer index = 0;

        for (Book book : loanedBooks) {
            index++;
            System.out.print(index.toString() + ". ");
            book.display();
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Book> getLoanedBooks() {
        return loanedBooks;
    }

    public ArrayList<Book> getWatchingBooks() {
        return watchingBooks;
    }

}
