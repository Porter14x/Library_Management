package Library_Management;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UserTest {
    
    Library library = new Library();

    @Test
    public void testReleaseBook() {
        User user = new User("test");
        Book book = new Book("test book", Genre.BIOGRAPHY, "author", 10);
        user.loanBook(book);
        user.releaseBook(book);
        assertTrue(!user.getLoanedBooks().contains(book));
    }

    @Test
    public void testSubscribeBook() {
        User user = new User("test");
        Book book = new Book("test book", Genre.BIOGRAPHY, "author", 10);
        user.subscribeBook(book);
        assertTrue(user.getWatchingBooks().contains(book));
    }

    @Test
    public void testUnsubscribeBook() {
        User user = new User("test");
        Book book = new Book("test book", Genre.BIOGRAPHY, "author", 10);
        user.subscribeBook(book);
        user.unsubscribeBook(book);
        assertTrue(!user.getWatchingBooks().contains(book));
    }

    @Test
    public void testLoanBook() {
        User user = new User("test");
        Book book1 = new Book("test book 1", Genre.BIOGRAPHY, "author", 10);
        Book book2 = new Book("test book 2", Genre.BIOGRAPHY, "author", 10);
        Book book3 = new Book("test book 3", Genre.BIOGRAPHY, "author", 10);
        Book book4 = new Book("test book 4", Genre.BIOGRAPHY, "author", 10);
        user.loanBook(book1);
        user.loanBook(book2);
        user.loanBook(book3);
        user.loanBook(book4);

        assertTrue(user.getLoanedBooks().size()==3);
        assertTrue(user.getLoanedBooks().contains(book1));
        assertTrue(user.getLoanedBooks().contains(book2));
        assertTrue(user.getLoanedBooks().contains(book3));
        assertTrue(!user.getLoanedBooks().contains(book4));
    }

}
