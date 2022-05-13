/****************************************************************************
 * Class: MyProgressBar (MyProgressBar.java)
 *
 * DESCRIPTION
 * This class defines an extension of JProgressBar that contains some more
 * information pertaining to the program.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

import javax.swing.*;

public class MyProgressBar extends JProgressBar implements Runnable
{
	Thread mThreadBeingMonitored;
	FileCreator mObjectBeingMonitored;
	View mCurrentView;
	
	public MyProgressBar(int pMinimum, int pMaximum, View pCurrentView,
	                     Thread pThreadBeingMonitored, FileCreator pObjectBeingMonitored)
	{
		super(pMinimum, pMaximum);
		this.mThreadBeingMonitored = pThreadBeingMonitored;
		mObjectBeingMonitored = pObjectBeingMonitored;
		mCurrentView = pCurrentView;
	}
	
	@Override
	public void run()
	{
		updateProgressBar();
	}
	
	public boolean isThreadAlive()
	{
		while(mThreadBeingMonitored.isAlive())
		{
			return true;
		}
		return false;
	}
	
	public void updateProgressBar()
	{
		while(isThreadAlive())
		{
			super.setEnabled(true);
			super.setVisible(true);
			super.setValue(mObjectBeingMonitored.getCurrentProgress());
		}
		JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(mCurrentView),
				"The file saved successfully.",
				"File Written", JOptionPane.INFORMATION_MESSAGE);
		super.setEnabled(false);
		super.setVisible(false);
	}
}
