package gui.dialogBoxes;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.components.JRoundedButton;

public class SimpleDialogBox extends JDialog {
    
    /**
	 * SimpleDialogBox @Version 1
	 */
	
	//functions: information, handle action.
	//returns: boolean on whether OK was pressed (can be used in conjunction with cancel button function
	
	//added serial version to stop it crying
	private static final long serialVersionUID = 1L;
	private boolean okPressed = false; // flag to keep track of which button was pressed
    private JLabel messageLabel;
    
    //parent used for contextual positioning, message for what to display, cancleButton if the action needs a cancel function, modal if box needs to be modal, centerOnScreen if box needs to be centered
    public SimpleDialogBox(Frame parent, String message, boolean cancleButton, boolean modal, boolean centerOnScreen) {
        super(parent, modal);
        
        messageLabel = new JLabel(message);
        JButton okButton = new JRoundedButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okPressed = true;
                dispose();
            }
        });
        
        JButton cancelButton = new JRoundedButton("Afbryd");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                okPressed = false;
                dispose();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        
        if (cancleButton) {
            buttonPanel.add(cancelButton);
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(messageLabel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        pack();
        
        if(centerOnScreen) {
        	setLocationRelativeTo(null);
        }
        else {
        	if(parent != null) {
        		setLocationRelativeTo(parent);
        	}
        }
    }
    
    
    //returns whether OK was pressed
    public boolean isOkPressed() {
        return okPressed;
    }
}
