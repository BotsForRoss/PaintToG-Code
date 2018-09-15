package com.github.botross.main;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel {
	private PaintPath paintPath;
	
	public Canvas() {
		
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
