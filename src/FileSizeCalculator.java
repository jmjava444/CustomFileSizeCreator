/****************************************************************************
 * Class: FileSizeCalculator (FileSizeCalculator.java)
 *
 * DESCRIPTION
 * This class has methods to perform calculations to make the
 * file.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

public class FileSizeCalculator
{
	private View mView;
	
	private static final long BYTES = 1L;
	private static final long KILOBYTES = 1024L;
	private static final long MEGABYTES = 1048576L;
	private static final long GIGABYTES = 1073741824L;
	
	public FileSizeCalculator(View pView)
	{
		mView = pView;
	}
	
	/**
	 * Returns the raw byte value based on which combo box option is selected.
	 * @return The size of the file in bytes.
	 */
	public long calculate()
	{
		switch(mView.getCurrentUnit())
		{
			case View.BYTES:
				return mView.getTextFieldLong() * BYTES;
			case View.KILOBYTES:
				return mView.getTextFieldLong() * KILOBYTES;
			case View.MEGABYTES:
				return mView.getTextFieldLong() * MEGABYTES;
			case View.GIGABYTES:
				return mView.getTextFieldLong() * GIGABYTES;
			default:
				System.err.println("FileSizeCalculator.calculate() failed... Returned 0");
				return 0L;
		}
	}
}
