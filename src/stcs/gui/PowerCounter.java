package stcs.gui;

import java.awt.event.ActionListener;

public class PowerCounter extends Thread {
	ActionListener listener;
	int delay = 15;
	boolean active = true, counting = false;

	public PowerCounter(ActionListener parent) {
		super();
		listener = parent;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setNotification(boolean ntf) {
		counting = ntf;
	}

	public void finish() {
		counting = false;
		active = false;
	}

	public void run() {
		while (active) {
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (counting) {
				listener.actionPerformed(null);
			}
		}
	}
}
