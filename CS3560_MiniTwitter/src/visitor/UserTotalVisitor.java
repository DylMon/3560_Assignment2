package visitor;
import app.GroupUser;
import app.SingleUser;
import app.User;

/**
 * Concrete visitor for obtaining the total number of
 * SingleUser under the specified User.  Includes selected
 * User if user is an instance of SingleUser.
 *
 * @author Dylan Monge  
 *
 */

public class UserTotalVisitor implements Visitor {

    @Override
    public int visitUser(User user) {
        int count = 0;

        if (user.getClass() == SingleUser.class) {
            count += visitSingleUser(user);
        } else if (user.getClass() == GroupUser.class) {
            count += visitGroupUser(user);
        }

        return count;
    }

    @Override
    public int visitSingleUser(User user) {
        return 1;
    }

    @Override
    public int visitGroupUser(User user) {
        int count = 0;

        for (User u : ((GroupUser) user).getGroupUsers().values()) {
            count += visitUser(u);
        }

        return count;
    }

}
