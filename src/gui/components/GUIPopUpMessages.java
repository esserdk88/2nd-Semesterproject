package gui.components;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUIPopUpMessages {
	
	/**
	 * A custom dialog box
	 * @param text The text to be displayed
	 * @param title The title of the Dialog
	 * @param buttons Buttons for the dialog
	 * @param messageType The type of Dialog
	 * @return The answer in int
	 */
	public static int customBox(String text, String title, Object[] buttons, int messageType) {
		return JOptionPane.showOptionDialog(new JFrame(), text, title,
				JOptionPane.YES_NO_OPTION, messageType, null,
				buttons, JOptionPane.YES_OPTION);
	}
	
	/**
	 * A Dialog with Ok And Back options
	 * @param text The text to be displayed
	 * @param title The title of the Dialog
	 * @return The answer in int OK = 0, Back = 1
	 */
	public static int okBackBox(String text, String title) {
		return JOptionPane.showOptionDialog(new JFrame(), text, title,
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "OK", "Tilbage" }, JOptionPane.YES_OPTION);
	}
	
	/**
	 * A Information Message Dialog
	 * @param text The text to be displayed
	 * @param title The Title of the Dialog
	 */
	public static void informationMessage(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title,
				JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * A Warning Message Dialog
	 * @param text The text to be displayed
	 * @param title The Title of the Dialog
	 */
	public static void warningMessage(String text, String title) {
		JOptionPane.showMessageDialog(null, text, title,
				JOptionPane.ERROR_MESSAGE);
	}
}
