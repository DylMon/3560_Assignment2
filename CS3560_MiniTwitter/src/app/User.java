package app;
import javax.swing.tree.DefaultMutableTreeNode;

import visitor.Visitor;

/**
 * User represents the Component of Composite design pattern.
 * @author Dylan Monge 
 */

public abstract class User extends DefaultMutableTreeNode implements Observer {

    private String id;
    private int messageCount;
    private long creationTime; // New attribute
    private long lastUpdateTime;
    
    public abstract boolean contains(String id);
    public abstract int getSingleUserCount();
    public abstract int getGroupUserCount();

    public User(String id) {
        super(id);
        this.id = id;
        this.setMessageCount(0);
        this.creationTime = System.currentTimeMillis(); // Set creation time
        this.lastUpdateTime = System.currentTimeMillis(); // Initialize last update time
    }

 // Method to update lastUpdateTime
    public void updateLastUpdateTime() {
        this.lastUpdateTime = System.currentTimeMillis();
    }

    // Getter for lastUpdateTime
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
    
    /**
     * Returns the user ID of this User.
     */
    public String getID() {
        return id;
    }

    /**
     * Returns the total number of messages sent by this User.
     */
    public int getMessageCount() {
        return messageCount;
    }

 // Getter for creationTime
    public long getCreationTime() {
        return creationTime;
    }
    
    
    /**
     * Sets the total number of messages sent by this User
     * to the specified integer.
     */
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    /*
     * Visitor methods
     */

    public abstract void accept(Visitor visitor);

}

