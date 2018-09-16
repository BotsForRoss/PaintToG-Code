package com.github.botross.main;

import java.awt.Dimension;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class Window {
	@SuppressWarnings("serial")
	private class FileMenu extends JMenu {
		private JMenuItem save;
		private JMenuItem load;
		private JMenuItem exportGCode;
		
		private JFileChooser fileChooser;
		
		public FileMenu(JFrame windowRef) {
			super("File");
			save = new JMenuItem("Save");
			load = new JMenuItem("Load");
			exportGCode = new JMenuItem("Export G-Code");
			
			fileChooser = new JFileChooser();
			
			save.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int option = fileChooser.showSaveDialog(windowRef);
					if (option == JFileChooser.APPROVE_OPTION) {
						File f = fileChooser.getSelectedFile();
						fileChooser.setCurrentDirectory(f);
						FileInterpreter.savePathToFile(f, canvas.getPaintPath());
					}
				}
			});
			load.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int option = fileChooser.showOpenDialog(windowRef);
					if (option == JFileChooser.APPROVE_OPTION) {
						File f = fileChooser.getSelectedFile();
						fileChooser.setCurrentDirectory(f);
						PaintPath paintPath = FileInterpreter.loadPathFromFile(f);
						canvas.setPaintPath(paintPath);
					}
				}
			});
			exportGCode.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int option = fileChooser.showSaveDialog(windowRef);
					if (option == JFileChooser.APPROVE_OPTION) {
						File f = fileChooser.getSelectedFile();
						fileChooser.setCurrentDirectory(f);
						FileInterpreter.saveGCodeToFile(f, new GCode(canvas.getPaintPath())); // GCode is an object because I'm optimistic for a "loadPathFromGCode" option
					}
				}
			});
			this.add(save);
			this.add(load);
			this.add(exportGCode);
		}
	}
	
	private int width, height, window_x, window_y;
	
	private JFrame frame;
	private JPanel contentPane;
	private Canvas canvas;
	private JMenuBar menuBar;
	private FileMenu fileMenu;
	
	public Window(int width, int height, int window_x, int window_y) {
		this.width = width;
		this.height = height;
		this.window_x = window_x;
		this.window_y = window_y;
		
		frame = new JFrame();
		contentPane = new JPanel();
		canvas = new Canvas(width, height); // Might subtract some later for bar at the bottom/side
		menuBar = new JMenuBar();
		fileMenu = new FileMenu(frame);
		
		frame.addKeyListener(new KeyAdapter() {
			boolean undoTrig = false;
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z && !undoTrig) {
					canvas.undo();
					undoTrig = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_Z) {
					undoTrig = false;
				}
			}
		});
		
		contentPane.setPreferredSize(new Dimension(width, height));
		contentPane.add(canvas);
		frame.setContentPane(contentPane);
		
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		frame.setVisible(true);
	}
	
	public Window(int width, int height) {
		this(width, height, 0, 0);
	}
}
