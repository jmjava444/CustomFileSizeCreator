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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class View extends JFrame implements ActionListener
{
	public static final String FRAME_TITLE = "Custom File Size Maker";
	private Main mMain;
	private JFrame mMainFrame;
	private JPanel mMainPanel;
	private JPanel mButtonPanel;
	private JPanel mTextPanel;
	private JLabel mFileSizeLabel;
	private JLabel mUnitsLabel;
	private JTextField mTextField;
	private JButton mSaveButton;
	private JButton mExitButton;
	private JComboBox<String> mSizeSelectionBox;
	
	public View(Main pMain)
	{
		// Save a reference to the Main object
		setMain(pMain);
		
		// Initialize components in the window
		mMainFrame = new JFrame();
		mMainPanel = new JPanel();
		mButtonPanel = new JPanel();
		mTextPanel = new JPanel();
		mFileSizeLabel = new JLabel("File Size: ");
		mUnitsLabel = new JLabel("Units: ");
		mTextField = new JTextField(10);
		mSizeSelectionBox = new JComboBox<>();
		mExitButton = new JButton("Exit",
				createImageIcon("/img/exit-icon.png"));
		mSaveButton = new JButton("Save...",
				createImageIcon("/img/save-icon.png"));
		
		// Modify some button properties
		mSaveButton.addActionListener(this);
		mExitButton.addActionListener(this);
		mSaveButton.setEnabled(false);
		
		// Set window properties
		setLookAndFeel();
		setWindowProperties();
		mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS));
		mButtonPanel.setLayout(new FlowLayout());
		mTextPanel.setLayout(new FlowLayout());
		
		// Add options to the JComboBox
		mSizeSelectionBox.addItem("Bytes");
		mSizeSelectionBox.addItem("Kilobytes");
		mSizeSelectionBox.addItem("Megabytes");
		mSizeSelectionBox.addItem("Gigabytes");
		mSizeSelectionBox.addActionListener(this);
		
		// Add components to text panel
		mTextPanel.add(mFileSizeLabel);
		mTextPanel.add(mTextField);
		mTextPanel.add(mUnitsLabel);
		mTextPanel.add(mSizeSelectionBox);
		
		// Add components to button panel
		mButtonPanel.add(mSaveButton);
		mButtonPanel.add(new JPanel());
		mButtonPanel.add(mExitButton);
		
		// Add components to main panel
		mMainPanel.add(mTextPanel);
		mMainPanel.add(mButtonPanel);
		
		// Add components to main frame
		mMainFrame.add(mMainPanel);
		
		// Last operation: pack and set visible
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	//TODO Make a method to manage maximum input from the text field
	
	@Override
	public void actionPerformed(ActionEvent pEvent)
	{
		if(pEvent.getSource() == mExitButton)
		{
			System.exit(0);
		}
	}
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	private void setWindowProperties()
	{
		// Set default properties of the window
		mMainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMainFrame.setTitle(FRAME_TITLE);
		mMainFrame.setResizable(false);
		mMainFrame.setLocationByPlatform(true);
	}
	
	private void setLookAndFeel()
	{
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
	}
	
	private Main getMain()
	{
		return mMain;
	}
	
	private void setMain(Main pMain)
	{
		mMain = pMain;
	}
	
	private void setSaveButtonEnabled(boolean b)
	{
		mSaveButton.setEnabled(b);
	}
	private JTextField getTextField()
	{
		return mTextField;
	}
}
