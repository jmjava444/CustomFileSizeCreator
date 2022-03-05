/****************************************************************************
 * Class: View (View.java)
 *
 * DESCRIPTION
 * Creates the GUI window for the user to interact with. Includes a simple
 * label instructing the user what to do, a text field, a combo box, and a
 * "save to..." button, along with an exit button.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import javax.swing.plaf.*;

public class View extends JFrame implements ActionListener
{
	
	public static final int FRAME_WIDTH = 800;
	public static final int FRAME_HEIGHT = 400;
	public static final String FRAME_TITLE = "Custom File Size Maker";
	private Main mMain;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel mInstructionLabel;
	private JTextField mTextField;
	private JButton mSaveButton;
	private JButton mExitButton;
	private JComboBox<String> mSizeSelectionBox;
	
	public View(Main pMain)
	{
		// Save a reference to the Main object
		setMain(pMain);
		
		// Initialize components in the window
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		mInstructionLabel = new JLabel("Write some instructions here.");
		mTextField = new JTextField(10);
		mSaveButton = new JButton("Save...");
		mExitButton = new JButton("Exit");
		mSizeSelectionBox = new JComboBox<String>();
		
		//Set the look and feel of the window
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
			System.out.println("Unsupported L&F");
		}
		catch (ClassNotFoundException e) {
			// handle exception
			System.out.println("Class not Found");
		}
		catch (InstantiationException e) {
			System.out.println("Instantiation Exception");
		}
		catch (IllegalAccessException e) {
			// handle exception
			System.out.println("Illegal access exception");
		}
		
		// Set default properties of the window
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(FRAME_TITLE);
		setResizable(false);
		setLocationByPlatform(true);
		
		// Add components to mainFrame
		mainFrame.setLayout(new FlowLayout());
		mainPanel.add(mInstructionLabel);
		mainPanel.add(mTextField);
		mainPanel.add(mSaveButton);
		mainPanel.add(mExitButton);
		mainFrame.add(mainPanel);
		
		// Last operation set visible
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent pEvent)
	{
	
	}
	
	private Main getMain()
	{
		return mMain;
	}
	
	private void setMain(Main pMain)
	{
		mMain = pMain;
	}
}
