package Library_Management;

import java.util.ArrayList;

public class Book implements Subject{

    private static final String BOOK_ID_START = "BK";
    private static final String LOAN_MESSAGE = "The following book has been loaned by another user: ";
    private static final String AVAILABLE_MESSAGE = "The following book is now available: ";
    static Integer currId = 0;

    private String bookId;
    private String title;
    private Genre genre;
    private String authorName;
    private int pages;
    private ArrayList<Observer> observers = new ArrayList<>();
    private Boolean onLoan = false;

    public Book(String title, Genre genre, String authorName, int pages) {
        currId++;
        this.bookId = (BOOK_ID_START + currId.toString());
        this.title = title;
        this.genre = genre; 
        this.authorName =  authorName;
        this.pages = pages;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyBookLoaned() {
        String message = LOAN_MESSAGE + this.title;
        for (Observer ob : observers) {
            ob.update(message);
        }
    }

    @Override
    public void notifyBookAvailable() {
        String message = AVAILABLE_MESSAGE + this.title;
        for (Observer ob : observers) {
            ob.update(message);
        }
    }

    public void display() {
        System.out.println("BookId: " + bookId + ", Title: " + title + ", Genre: " + genre.toString() + ", Author: " + authorName + " Pages: " + pages);
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPages() {
        return pages;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public Boolean isOnLoan() {
        return onLoan;
    }

    public void setOnLoan(Boolean status) {
        onLoan = status;
    }

}