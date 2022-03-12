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

public class FileSizeCalculator implements Runnable
{
	private View mView;
	private String mFileSize;
	private long mUnits;
	private int i;
	ProgressBarWindow progressBarWindow;
	
	private static final long BYTES = 1L;
	private static final long KILOBYTES = 1024L;
	private static final long MEGABYTES = 1048576L;
	private static final long GIGABYTES = 1073741824L;
	
	public FileSizeCalculator(View pView, String pFileSize, long pUnits)
	{
		mView = pView;
		mFileSize = pFileSize;
		mUnits = pUnits;
	}
	
	public FileSizeCalculator(View pView)
	{
		mView = pView;
		mFileSize = "0";
		mUnits = 0L;
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
	
	/**
	 * Creates a text file comprised of random digits, and you can specify the size.
	 *
	 * @param pSizeInBytes File size in bytes
	 * @param pPath Pathname of the specified file
	 * @throws FileNotFoundException Make sure to resolve in case it cannot write to the file
	 */
	public void createFile(long pSizeInBytes, String pPath) throws FileNotFoundException
	{
		i = 0;
		File file = new File(pPath);
		PrintWriter out = new PrintWriter(file);
		progressBarWindow = new ProgressBarWindow(mView, 0, Integer.parseInt(mFileSize));
		while(i < pSizeInBytes)
		{
			writeChar(out);
			i++;
		}
		out.close();
	}
	
	protected int getFileSize()
	{
		return Integer.parseInt(mFileSize);
	}
	
	private void setFileSize(String pFileSize)
	{
		mFileSize = pFileSize;
	}
	
	private void setUnits(long pUnits)
	{
		mUnits = pUnits;
	}
	
	private void writeChar(PrintWriter pw)
	{
		pw.print((int) (Math.random() * 10.0));
	}
	
	public int getCurrentProgress()
	{
		return i;
	}
	
	@Override
	public void run(long pSizeInBytes, String pPath)
	{
		createFile(pSizeInBytes, pPath);
	}
}
