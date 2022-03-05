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
	private String mFileSize;
	private int mUnits;
	private static final int BYTES = 1;
	private static final int KILOBYTES = 1000;
	private static final int MEGABYTES = 1000000;
	private static final int GIGABYTES = 1000000000;
	
	public FileSizeCalculator(String pFileSize, int pUnits)
	{
		mFileSize = pFileSize;
		mUnits = pUnits;
	}
	
	//TODO make a method to calculate total final size
	public void calculate()
	{
	
	}
}
