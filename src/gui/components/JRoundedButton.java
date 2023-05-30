package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;


public class JRoundedButton extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Hit detection.
	private Shape shape;
	private double rounding;
	private int sizeRounding;
	private static double DEFAULT_ROUNDING = 0.55;
	
	private Color normal = new Color(68, 114, 196);
	private Color onClick = new Color(4, 138, 191);

	// This is a constructor for the JRoundedButton class that takes a label as a parameter. It calls
	// another constructor of the same class with two parameters: the label and a default rounding value.
	// This is a way to provide a default value for the rounding parameter without requiring the user to
	// specify it every time they create a new JRoundedButton object.
	public JRoundedButton(String label) {
		this(label, DEFAULT_ROUNDING);
	}

	// This is a constructor for the JRoundedButton class that takes a label and a rounding value as
	// parameters. It calls the constructor of the same class with only the label parameter and sets the
	// rounding value for the button. It also sets the background color to light gray, makes the button
	// not focusable, sets the foreground color to white, sets the font to Tahoma with a bold style and
	// size 16, and sets the content area filled to false, which allows the button to have a round
	// background.
	public JRoundedButton(String label, double rounding) {
		super(label);
		this.rounding = rounding;

		setBackground(Color.lightGray);
		setFocusable(false);
		setForeground(Color.white);
		setFont(new Font("Tahoma", Font.BOLD, 16));
		/*
		 * This call causes the JButton not to paint the background. This allows us to
		 * paint a round background.
		 */
		setContentAreaFilled(false);
	}

	/**
	 * The function returns the value of the variable "rounding" as a double.
	 * 
	 * @return The method is returning a double value, which is the value of the variable "rounding".
	 */
	public double getRounding() {
		return rounding;
	}

	/**
	 * The function sets the value of the rounding variable.
	 * 
	 * @param rounding The parameter "rounding" is a double data type that represents the value to which a
	 * number should be rounded. This method sets the value of the rounding variable to the specified
	 * value.
	 */
	public void setRounding(double rounding) {
		this.rounding = rounding;
	}
	
	/**
	 * The function sets the default color to a specified normal color.
	 * 
	 * @param normal The parameter "normal" is a Color object that represents the default color that will
	 * be set for an object. This method is likely part of a class that has a color property that can be
	 * customized, and this method allows the user to set the default color for that property.
	 */
	public void setColorDefault(Color normal) {
		this.normal = normal;
	}
	
	/**
	 * The function sets the onClick color to the hover color.
	 * 
	 * @param hover The hover parameter is a Color object that represents the color that will be set as
	 * the onClick color when the setColorClick method is called.
	 */
	public void setColorClick(Color hover) {
		this.onClick = hover;
	}

	/**
	 * This function paints a rounded rectangle with either a normal or onClick color based on the
	 * button's state.
	 * 
	 * @param g The Graphics object used for painting the component. It provides methods for drawing
	 * shapes, text, and images on the component.
	 */
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			g.setColor(onClick);
		} else {
			g.setColor(normal);
		}
		
		int minSide = Math.min(getSize().height, getSize().width);
		sizeRounding = (int) (minSide * rounding);
		g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, sizeRounding, sizeRounding);
		
		super.paintComponent(g);
	}

	/**
	 * This function paints a black rounded rectangle border with antialiasing enabled if the border is
	 * set to be painted.
	 * 
	 * @param g The Graphics object used to paint the border.
	 */
	protected void paintBorder(Graphics g) {
		if (isBorderPainted()) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int minSide = Math.min(getSize().height, getSize().width);
			sizeRounding = (int) (minSide * rounding);
			
			g.setColor(Color.black);
			g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, sizeRounding, sizeRounding);
		}
	}

	/**
	 * This function checks if a given point (x,y) is contained within a shape object, which is either an
	 * ellipse or a rectangle depending on the size of a button.
	 * 
	 * @param x The x-coordinate of the point being checked for containment in the shape.
	 * @param y The parameter "y" represents the vertical coordinate of a point in the coordinate system.
	 * It is used in the method to check if a given point (x,y) is contained within the shape of a button.
	 * @return The method is returning a boolean value indicating whether the specified point (x, y) is
	 * contained within the shape of the button.
	 */
	public boolean contains(int x, int y) {
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
	
}
