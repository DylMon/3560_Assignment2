package visitor;
import app.User;

/**
 * Interface to represent visitor for derived classes
 * of User for Visitor design pattern.
 *
 * @author Dylan Monge  
 *
 */

public interface Visitor {

    public int visitUser(User user);
    public int visitSingleUser(User user);
    public int visitGroupUser(User user);

}
