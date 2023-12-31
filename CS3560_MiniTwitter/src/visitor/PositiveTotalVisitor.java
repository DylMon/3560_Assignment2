package visitor;
import app.GroupUser;
import app.SingleUser;
import app.User;

/**
 * Concrete visitor for obtaining total number of positive
 * messages sent by Users under the specified User.
 *
 * @author Dylan Monge  
 *
 */

public class PositiveTotalVisitor implements Visitor {

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
        return ((SingleUser) user).getPositiveMessageCount();
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
