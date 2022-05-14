/****************************************************************************
 * Class: Main (Main.java)
 *
 * DESCRIPTION
 * This program is designed to output a custom file size of the user's
 * choice (containing random chars), and it will output to a .test file.
 *
 * Copyright 2022, Joshua McKenzie, All rights reserved.
 ***************************************************************************/

public class Main
{
	
	public static void main(String[] pArgs)
	{
		Main main = new Main();
		new View(main);
	}
	
	public void exit()
	{
		System.exit(0);
	}
}
