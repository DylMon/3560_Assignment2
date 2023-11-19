package app;

/**
 * Subject interface for Observer pattern.
 * @author Dylan Monge 
 *
 */

public interface Subject {

    public void attach(Observer observer);
    public void notifyObservers();

}
