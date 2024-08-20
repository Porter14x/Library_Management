package Library_Management;

import java.util.ArrayList;

public class Author {
    
    private static final String AUTHOR_ID_START = "AUT";
    static Integer currId = 0;

    private String authId;
    private String name;
    private ArrayList<Book> publishedBooks = new ArrayList<>();

    public Author(String name) {
        currId++;
        this.authId = (AUTHOR_ID_START + currId.toString());
        this.name = name;
    }

    public Book publishBook(String title, Genre genre, int pages) {
        return new Book(title, genre, this.name, pages);
    }

    public String getAuthId() {
        return authId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getPublishedBooks() {
        return publishedBooks;
    }



}
