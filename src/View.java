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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class View extends JFrame implements ActionListener
{
	public static final String FRAME_TITLE = "Custom File Size Maker";
	public static final String BYTES = "Bytes";
	public static final String KILOBYTES = "Kilobytes";
	public static final String MEGABYTES = "Megabytes";
	public static final String GIGABYTES = "Gigabytes";
	public static final long MAX_FILE_SIZE_IN_GB = 64L;
	private Main mMain;
	private JFrame mMainFrame;
	private JPanel mMainPanel;
	private JPanel mButtonPanel;
	private JPanel mTextPanel;
	private JPanel mSpacerPanel;
	private JLabel mFileSizeLabel;
	private JLabel mUnitsLabel;
	private JTextField mTextField;
	private JButton mSaveButton;
	private JButton mExitButton;
	private JComboBox<String> mSizeSelectionBox;
	private JProgressBar mProgressBar;
	private JFileChooser mFileChooser;
	
	public View(Main pMain)
	{
		// Save a reference to the Main object
		setMain(pMain);
		
		// Initialize components in the window
		mMainFrame = new JFrame();
		mMainPanel = new JPanel();
		mButtonPanel = new JPanel();
		mTextPanel = new JPanel();
		mSpacerPanel = new JPanel();
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
		
		// Add a key listener to the text field
		mTextField.setFocusTraversalKeysEnabled(true);
		KeyListener keyListener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				// Do nothing
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				//do nothing
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				try
				{
					handleTextFieldInput();
				}
				catch(NumberFormatException nfe)
				{
					clearTextField();
				}
				detectEnableSaveButton();
			}
		};
		mTextField.addKeyListener(keyListener);
		
		// Set window properties
		setLookAndFeel();
		setWindowProperties();
		mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS));
		mSpacerPanel.setLayout(new FlowLayout());
		mButtonPanel.setLayout(new FlowLayout());
		mTextPanel.setLayout(new FlowLayout());
		
		// Add options to the JComboBox
		mSizeSelectionBox.addItem(BYTES);
		mSizeSelectionBox.addItem(KILOBYTES);
		mSizeSelectionBox.addItem(MEGABYTES);
		mSizeSelectionBox.addItem(GIGABYTES);
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
		mMainPanel.add(mSpacerPanel);
		mMainPanel.add(mButtonPanel);
		
		// Add components to main frame
		mMainFrame.add(mMainPanel);
		
		// Last operation: pack and set visible
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	private void handleTextFieldInput() throws NumberFormatException
	{
		if(!mTextField.getText().isEmpty())
		{
			if(mSizeSelectionBox.getSelectedItem().toString().equals(BYTES))
			{
				showInvalidMessageBox(BYTES);
			}
			else if(mSizeSelectionBox.getSelectedItem().toString().equals(KILOBYTES))
			{
				showInvalidMessageBox(KILOBYTES);
			}
			else if(mSizeSelectionBox.getSelectedItem().toString().equals(MEGABYTES))
			{
				showInvalidMessageBox(MEGABYTES);
			}
			else if(mSizeSelectionBox.getSelectedItem().toString().equals(GIGABYTES))
			{
				showInvalidMessageBox(GIGABYTES);
			}
		}
	}
	
	private void showInvalidMessageBox(String pUnits)
	{
		long multiplier;
		long maxFileSizeInGigabytes = MAX_FILE_SIZE_IN_GB;
		multiplier = getMultiplier(pUnits);
		if(Long.parseLong(mTextField.getText()) > (multiplier * maxFileSizeInGigabytes))
		{
			messageBox("You cannot enter a number larger than " +
			           (multiplier * maxFileSizeInGigabytes) + " " + pUnits + " = 64GB");
			clearTextField();
		}
	}
	
	private long getMultiplier(String pUnits)
	{
		long multiplier;
		if(pUnits.equals(BYTES))
		{
			multiplier = 1000000000L;
		}
		else if(pUnits.equals(KILOBYTES))
		{
			multiplier = 1000000L;
		}
		else if(pUnits.equals(MEGABYTES))
		{
			multiplier = 1000L;
		}
		else if(pUnits.equals(GIGABYTES))
		{
			multiplier = 1L;
		}
		else
		{
			multiplier = 0L;
		}
		return multiplier;
	}
	
	private void detectEnableSaveButton()
	{
		if(!mTextField.getText().isEmpty())
		{
			mSaveButton.setEnabled(true);
		}
		else
		{
			mSaveButton.setEnabled(false);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent pEvent)
	{
		if(pEvent.getSource() == mExitButton)
		{
			System.exit(0);
		}
		if(pEvent.getSource() == mTextField)
		{
			if(!mTextField.getText().isEmpty())
			{
				mSaveButton.setEnabled(true);
			}
		}
		if(pEvent.getSource() == mSaveButton)
		{
			System.out.println("Save Button Clicked");
			saveFile();
		}
	}
	
	private void saveFile()
	{
		mFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int saveDialog = mFileChooser.showSaveDialog(null);
		File fileToBeSaved;
		PrintWriter fileWriter;
		if(saveDialog == JFileChooser.APPROVE_OPTION)
		{
			String fileSaveLocation = mFileChooser.getSelectedFile().getAbsolutePath();
			//System.out.println(fileSaveLocation);
			try
			{
				fileWriter = new PrintWriter(fileSaveLocation);
				
			}
			catch(FileNotFoundException e)
			{
				System.err.println("Cannot write to file. FileNotFoundException");
			}
		}
	}
	
	private String printToFile()
	{
		int maxIterations = Integer.parseInt(mTextField.getText());
		long multiplier = getMultiplier(mSizeSelectionBox.getSelectedItem().toString());
		String output = "";
		for(int i = 0; i < maxIterations; i++)
		{
			output += Double.toString(Math.random() * 10.0);
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
			System.err.println("Unsupported Look & Feel Exception");
		}
		catch (ClassNotFoundException e) {
			// handle exception
			System.err.println("Class not Found Exception");
		}
		catch (InstantiationException e) {
			System.err.println("Instantiation Exception");
		}
		catch (IllegalAccessException e) {
			// handle exception
			System.err.println("Illegal Access Exception");
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
	
	private void clearTextField()
	{
		mTextField.setText("");
	}
	
	public void messageBox(String pMessage)
	{
		JOptionPane.showMessageDialog(mMainFrame, pMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
