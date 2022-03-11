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
	private String mFileSize;
	private long mUnits;
	private static final long BYTES = 1L;
	private static final long KILOBYTES = 1000L;
	private static final long MEGABYTES = 1000000L;
	private static final long GIGABYTES = 1000000000L;
	
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
	 * @return long
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
	
	private void setFileSize(String pFileSize)
	{
		mFileSize = pFileSize;
	}
	
	private void setUnits(long pUnits)
	{
		mUnits = pUnits;
	}
}
