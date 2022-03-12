import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;

import static java.lang.Thread.sleep;

public class ProgressBarWindow extends JPanel implements ActionListener, PropertyChangeListener
{
	private ProgressMonitor progressMonitor;
	private Task task;
	private final View mView;
	private int minimum;
	private int maximum;
	
	class Task extends SwingWorker<Void, Void>
	{
		@Override
		public Void doInBackground()
		{
			int progress = 0;
			setProgress(0);
			while(progress < 100 && !isCancelled())
			{
				//Do the task here
			}
			return null;
		}
		
		@Override
		public void done()
		{
			Toolkit.getDefaultToolkit().beep();
			progressMonitor.setProgress(0);
		}
	}
	
	public ProgressBarWindow(View pView, int minimum, int maximum)
	{
		mView = pView;
		this.minimum = minimum;
		this.maximum = maximum;
	}
	
	/**
	 * Invoked when the user presses the start button.
	 */
	public void actionPerformed(ActionEvent evt)
	{
		progressMonitor = new ProgressMonitor(mView,
				"Creating File",
				"This may take a while...", minimum, maximum);
		progressMonitor.setProgress(0);
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	public void propertyChange(PropertyChangeEvent event)
	{
		if("progress".equals(event.getPropertyName()))
		{
			int progress = (Integer) event.getNewValue();
			progressMonitor.setProgress(progress);
			String message =
					String.format("Completed %d%%.\n", progress);
			progressMonitor.setNote(message);
			if(progressMonitor.isCanceled() || task.isDone())
			{
				Toolkit.getDefaultToolkit().beep();
				if(progressMonitor.isCanceled())
				{
					task.cancel(true);
				}
				else
				{
					//Do nothing
				}
			}
		}
	}
}

