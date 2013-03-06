package stcs;
import java.awt.EventQueue;


import stcs.gui.CTgui;


public class SimpleTomographSimulator {


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CTgui window;
				window=new CTgui();
			}
		});
	}
}
