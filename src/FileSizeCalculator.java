import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/****************************************************************************
 * Class: FileSizeCalculator (FileSizeCalculator.java)
 *
 * DESCRIPTION
 * Stores the status of the JComboBox from View.java, and it stores the
 * JTextField imput. It has methods to perform calculations to make the
 * file.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

public class FileSizeCalculator
{
	private View mView;
	private long mFileSize;
	
	private static final long BYTES = 1L;
	private static final long KILOBYTES = 1024L;
	private static final long MEGABYTES = 1048576L;
	private static final long GIGABYTES = 1073741824L;
	
	public FileSizeCalculator(View pView)
	{
		mView = pView;
		mFileSize = 0L;
	}
	
	/**
	 * Returns the raw byte value based on which combo box option is selected.
	 * @return The size of the file in bytes.
	 */
	public long calculate()
	{
		if(mView.getCurrentUnit().equals(View.BYTES))
		{
			return mView.getTextFieldLong() * BYTES;
		}
		else if(mView.getCurrentUnit().equals(View.KILOBYTES))
		{
			return mView.getTextFieldLong() * KILOBYTES;
		}
		else if(mView.getCurrentUnit().equals(View.MEGABYTES))
		{
			return mView.getTextFieldLong() * MEGABYTES;
		}
		else if(mView.getCurrentUnit().equals(View.GIGABYTES))
		{
			return mView.getTextFieldLong() * GIGABYTES;
		}
		else
		{
			System.err.println("FileSizeCalculator.calculate() failed... Returned 0");
			return 0L;
		}
	}
	
	protected int getFileSize()
	{
		return (int) mFileSize;
	}
	
	private void setFileSize(long pFileSize)
	{
		mFileSize = pFileSize;
	}
}
