package gui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import app.GroupUser;
import app.Observer;
import app.User;

public class AdminControlPanel extends ControlPanel {

    private static AdminControlPanel INSTANCE;

    private static JFrame frame;
    private JPanel treePanel;
    private JPanel addUserPanel;
    private JPanel openUserViewPanel;
    private JPanel showInfoPanel;

    private DefaultMutableTreeNode root;
    private Map<String, Observer> allUsers;

    public static AdminControlPanel getInstance() {
        if (INSTANCE == null) {
            synchronized (AdminControlPanel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AdminControlPanel();
                }
            }
        }
        return INSTANCE;
    }

    private AdminControlPanel() {
        super();
        initializeComponents();
        addComponents();
    }

    private void addComponents() {
        addComponent(frame, treePanel, 0, 0, 1, 6, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, addUserPanel, 1, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, openUserViewPanel, 1, 2, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(frame, showInfoPanel, 1, 4, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // Add the new button for ID validation
        JButton validateIDsButton = new JButton("Validate User IDs");
        validateIDsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean unique = areUserIDsUnique();
                String message = unique ? "All User IDs are Unique" : "User IDs are not Unique";
                JOptionPane.showMessageDialog(frame, message);
            }
        });
        
        // Adjust the position and size of the button
        addComponent(frame, validateIDsButton, 1, 6, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    
        JButton findLastUpdatedUserButton = new JButton("Find Last Updated User");
        findLastUpdatedUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastUpdatedUserId = findLastUpdatedUserID();
                if (lastUpdatedUserId != null) {
                    JOptionPane.showMessageDialog(frame, "Last updated user ID: " + lastUpdatedUserId);
                } else {
                    JOptionPane.showMessageDialog(frame, "No updates found.");
                }
            }
        });
        addComponent(frame, findLastUpdatedUserButton, 1, 7, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
    }

    private String findLastUpdatedUserID() {
        String lastUpdatedUserId = null;
        long lastUpdateTime = 0;

        for (Observer user : allUsers.values()) {
            User u = (User) user;
            if (lastUpdatedUserId == null || u.getLastUpdateTime() > lastUpdateTime) {
                lastUpdatedUserId = u.getID();
                lastUpdateTime = u.getLastUpdateTime();
            }
        }

        return lastUpdatedUserId;
    }
    
    
    
    
    
    
    

    private void initializeComponents() {
        frame = new JFrame("Mini Twitter App");
        formatFrame();

        allUsers = new HashMap<>();
        root = new GroupUser("Root");
        allUsers.put(((User) root).getID(), (Observer) root);

        treePanel = new TreePanel(root);
        addUserPanel = new AddUserPanel(treePanel, allUsers);
        openUserViewPanel = new OpenUserViewPanel(treePanel, allUsers);
        showInfoPanel = new ShowInfoPanel(treePanel);

        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);
        InputMap im = (InputMap) UIManager.get("Button.focusInputMap");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "none");
    }

    private void formatFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(800, 450); // Adjust the frame size as needed
        frame.setVisible(true);
    }

    private boolean areUserIDsUnique() {
        Set<String> uniqueIDs = new HashSet<>();
        for (Observer user : allUsers.values()) {
            if (!uniqueIDs.add(((User) user).getID())) {
                return false;
            }
        }
        return true;
    }
    
    
}
