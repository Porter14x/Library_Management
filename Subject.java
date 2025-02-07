package Library_Management;

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyBookLoaned();
    void notifyBookAvailable();
}
