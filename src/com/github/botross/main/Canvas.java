package com.github.botross.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	// Relative position and absolute position (because of camera)
	private PaintPath paintPath; // Stores absolute coordinates
	// Last 10 actions or so for undoing
	
	public Canvas(int width, int height) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Probably not usefull (triggered when mouse released, but the time it's held down is
				// important for the functionality I want)
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// For when dragging around stuff?
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// For when dragging around stuff?
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) { // Left click
					if (e.isAltDown()) {
						// Curve any selected line
					}
					else {
						// Move any selected point
					}
				}
				else if (e.getButton() == MouseEvent.BUTTON2) { // Middle click
					// Create a new point (disconnected from the others)
					// Make the new point the selected point
					// Redraw?
				}
				else if (e.getButton() == MouseEvent.BUTTON3) { // Right click
					// Create a new point (connected to the previously selected point)
					// Make the new point the selected point
					// Redraw?
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
		
		addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
			}
		});
		
		
		this.setPreferredSize(new Dimension(width, height));
	}
	
	public void undo() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		
	}
	
	public PaintPath getPaintPath() {
		return paintPath;
	}

	public void setPaintPath(PaintPath paintPath) {
		// Clear graphics
		// Do other cleanup stuff
		this.paintPath = paintPath;
		// Redraw graphics
	}
}
