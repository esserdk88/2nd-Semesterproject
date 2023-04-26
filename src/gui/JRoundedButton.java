package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

	public JRoundedButton(String label) {
		this(label, DEFAULT_ROUNDING);
	}

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

	public double getRounding() {
		return rounding;
	}

	public void setRounding(double rounding) {
		this.rounding = rounding;
	}
	
	public void setColorDefault(Color normal) {
		this.normal = normal;
	}
	
	public void setColorClick(Color hover) {
		this.onClick = hover;
	}

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

	protected void paintBorder(Graphics g) {
		if (isBorderPainted()) {
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			int minSide = Math.min(getSize().height, getSize().width);
			sizeRounding = (int) (minSide * rounding);
			
			g.setColor(Color.black);
			g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, sizeRounding, sizeRounding);
		}
	}

	public boolean contains(int x, int y) {
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
//			shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
			shape = new Rectangle2D.Float(0, 0, getWidth(), getHeight());
		}
		return shape.contains(x, y);
	}
	
}
