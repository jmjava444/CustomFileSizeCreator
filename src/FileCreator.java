import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileCreator implements Runnable
{
	long mFileSizeInBytes;
	String mPath;
	long mCurrentProgress;
	
	public FileCreator(long pFileSizeInBytes, String pPath)
	{
		mFileSizeInBytes = pFileSizeInBytes;
		mPath = pPath;
		mCurrentProgress = 0L;
	}
	
	@Override
	public void run()
	{
		try
		{
			createFile();
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),
					"File could not be written to. Make sure " +
					"the output file is not checked \"Read-only\'",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Creates a text file comprised of random digits, and you can specify the size.
	 *
	 * @throws FileNotFoundException Make sure to resolve in case it cannot write to the file
	 */
	public void createFile() throws FileNotFoundException
	{
		mCurrentProgress = 0L;
		File file = new File(mPath);
		PrintWriter out = new PrintWriter(file);
		while(mCurrentProgress < mFileSizeInBytes)
		{
			writeChar(out);
			mCurrentProgress++;
		}
		out.close();
	}
	
	public int getCurrentProgress()
	{
		return (int) (mCurrentProgress / 1000);
	}
	
	private void writeChar(PrintWriter pw)
	{
		pw.print((int) (Math.random() * 10.0));
	}
}
