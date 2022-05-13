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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class View extends JFrame implements ActionListener
{
	public static final String FRAME_TITLE = "Custom File Size Creator";
	public static final String BYTES = "Bytes";
	public static final String KILOBYTES = "Kilobytes";
	public static final String MEGABYTES = "Megabytes";
	public static final String GIGABYTES = "Gigabytes";
	public static final long MAX_FILE_SIZE_IN_GB = 64L;
	
	private Main mMain;
	
	private final JFrame mMainFrame;
	private final JPanel mMainPanel;
	private final JPanel mButtonPanel;
	private final JPanel mTextPanel;
	private final JPanel mProgressBarPanel;
	private final JLabel mFileSizeLabel;
	private final JLabel mUnitsLabel;
	private final JTextField mTextField;
	private final JButton mSaveButton;
	private final JButton mExitButton;
	private final JComboBox<String> mUnitsComboBox;
	private final JFileChooser mFileChooser;
	
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
		mUnitsComboBox = new JComboBox<>();
		mExitButton = new JButton("Exit",
				createImageIcon("/img/exit-icon.png"));
		mSaveButton = new JButton("Save...",
				createImageIcon("/img/save-icon.png"));
		mFileChooser = new JFileChooser();
		mProgressBarPanel = new JPanel(new FlowLayout());
		
		// Modify some button properties
		mSaveButton.addActionListener(this);
		mExitButton.addActionListener(this);
		mSaveButton.setEnabled(false);
		
		// Add a keystroke listener to the text field
		addKeystrokeListener();
		
		addComponentsToGUI();
		
	}
	
	private void addComponentsToGUI()
	{
		// Set window properties
		setLookAndFeel();
		setWindowProperties();
		mMainPanel.setLayout(new BoxLayout(mMainPanel, BoxLayout.Y_AXIS));
		mButtonPanel.setLayout(new FlowLayout());
		mTextPanel.setLayout(new FlowLayout());
		
		// Add options to the JComboBox
		mUnitsComboBox.addItem(BYTES);
		mUnitsComboBox.addItem(KILOBYTES);
		mUnitsComboBox.addItem(MEGABYTES);
		mUnitsComboBox.addItem(GIGABYTES);
		mUnitsComboBox.addActionListener(this);
		
		// Add components to text panel
		mTextPanel.add(mFileSizeLabel);
		mTextPanel.add(mTextField);
		mTextPanel.add(mUnitsLabel);
		mTextPanel.add(mUnitsComboBox);
		
		// Add components to button panel
		mButtonPanel.add(mSaveButton);
		mButtonPanel.add(new JPanel());
		mButtonPanel.add(mExitButton);
		
		// Add components to main panel
		mMainPanel.add(mTextPanel);
		mMainPanel.add(mProgressBarPanel);
		mMainPanel.add(mButtonPanel);
		
		// Add components to main frame
		mMainFrame.add(mMainPanel);
		
		// Last operation: pack and set visible
		mMainFrame.pack();
		mMainFrame.setVisible(true);
	}
	
	private void addKeystrokeListener()
	{
		mTextField.setFocusTraversalKeysEnabled(true);
		KeyListener keyListener = new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) { }
			
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
				toggleSaveButton();
			}
		};
		mTextField.addKeyListener(keyListener);
	}
	
	private void handleTextFieldInput() throws NumberFormatException
	{
		if(!mTextField.getText().isEmpty())
		{
			switch(getCurrentUnit())
			{
				case BYTES -> invalidNumberMessageBox(BYTES);
				case KILOBYTES -> invalidNumberMessageBox(KILOBYTES);
				case MEGABYTES -> invalidNumberMessageBox(MEGABYTES);
				case GIGABYTES -> invalidNumberMessageBox(GIGABYTES);
			}
		}
	}
	
	private void invalidNumberMessageBox(String pUnits)
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
		return switch(pUnits)
				{
					case BYTES -> 1073741824L;
					case KILOBYTES -> 1048576L;
					case MEGABYTES -> 1024L;
					case GIGABYTES -> 1L;
					default -> 0L;
				};
	}
	
	private void toggleSaveButton()
	{
		mSaveButton.setEnabled(!mTextField.getText().isEmpty());
	}
	
	@Override
	public void actionPerformed(ActionEvent pEvent)
	{
		if(pEvent.getSource() == mExitButton)
		{
			mMain.exit();
		}
		isSourceTextField(pEvent);
		isSourceSaveButton(pEvent);
	}
	// TODO: Make this method of type boolean. Why is it not?
	private void isSourceTextField(ActionEvent pEvent)
	{
		if(pEvent.getSource() == mTextField)
		{
			if(!mTextField.getText().isEmpty())
			{
				mSaveButton.setEnabled(true);
			}
		}
	}
	// TODO: Make this method of type boolean. Why is it not?
	private void isSourceSaveButton(ActionEvent pEvent)
	{
		if(pEvent.getSource() == mSaveButton)
		{
			FileSizeCalculator mFileSizeCalculator = new FileSizeCalculator(this);
			long fileSizeInBytes = mFileSizeCalculator.calculate();
			mFileChooser.setAcceptAllFileFilterUsed(false);
			mFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Test File",
					".test"));
			int returnValue = mFileChooser.showSaveDialog(this);
			if(returnValue == JFileChooser.APPROVE_OPTION)
			{
				FileCreator filecreator = new FileCreator(fileSizeInBytes,
						mFileChooser.getSelectedFile() + ".test");
				Thread fileCreatorThread = new Thread(filecreator);
				
				MyProgressBar myProgressBar = new MyProgressBar(0, ((int) (fileSizeInBytes / 1000)),
						this, fileCreatorThread, filecreator);
				myProgressBar.setEnabled(true);
				myProgressBar.setMinimumSize(new Dimension(500, 50));
				myProgressBar.setAlignmentY(getRootPane().getAlignmentY());
				mProgressBarPanel.add(myProgressBar);
				Thread myProgressBarThread = new Thread(myProgressBar);
				
				fileCreatorThread.start();
				myProgressBarThread.start();
				mMainFrame.pack();
				mMainFrame.setVisible(true);
			}
		}
	}
	
	private ImageIcon createImageIcon(String path)
	{
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
	
	private void setMain(Main pMain)
	{
		mMain = pMain;
	}
	
	private void clearTextField()
	{
		mTextField.setText("");
	}
	
	public void messageBox(String pMessage)
	{
		JOptionPane.showMessageDialog(mMainFrame, pMessage,
				"Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public String getCurrentUnit()
	{
		return Objects.requireNonNull(mUnitsComboBox.getSelectedItem()).toString();
	}
	
	public long getTextFieldLong()
	{
		try
		{
			return Long.parseLong(mTextField.getText());
		}
		catch(NumberFormatException e)
		{
			System.err.println("There was an error parsing long");
			e.printStackTrace();
			return 0L;
		}
	}
}
