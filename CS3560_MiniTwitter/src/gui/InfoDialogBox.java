package gui;

import javax.swing.JOptionPane;

/**
 * Message dialog for displaying user-specific information
 * and error messages.
 *
 * @author Dylan Monge  
 *
 */

public class InfoDialogBox {

    /**
     * Create the message dialog box with specified title and message.
     */
    public InfoDialogBox(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, title, messageType);
    }

}
