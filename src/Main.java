import javax.swing.*;

/****************************************************************************
 * Class: Main (Main.java)
 *
 * DESCRIPTION
 * This program is designed to output a custom file size of the user's
 * choice, and it will output to a .txt file.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

public class Main
{
	private View mView;
	
	public static void main(String[] pArgs)
	{
		Main main = new Main();
		main.run();
	}
	
	private void run()
	{
		View view = new View(this);
		setView(view);
	}
	
	private View getView()
	{
		return mView;
	}
	
	private void setView(View pView)
	{
		mView = pView;
	}
	
	public void exit()
	{
		System.exit(0);
	}
}
