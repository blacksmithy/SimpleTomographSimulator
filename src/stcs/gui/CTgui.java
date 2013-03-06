package stcs.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import stcs.logic.RectangleMatrixCalculator;

public class CTgui extends JFrame implements ActionListener, ChangeListener, WindowListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271534813006797395L;


	CTpanel srcPanel;
	CTpanel outPanel;
	JSpinner rowsSelector;
	JSpinner colsSelector;
	JButton btnReset;
	JButton btnCt;

	public CTgui() {
		super("CTgui");
		getContentPane().setLayout(null);
		
		srcPanel = new CTpanel(5,5);
		srcPanel.setBounds(10, 11, 200, 200);
		getContentPane().add(srcPanel);
		
		colsSelector = new JSpinner();
		colsSelector.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		colsSelector.setBounds(101, 220, 39, 20);
		getContentPane().add(colsSelector);
		colsSelector.addChangeListener(this);
		
		rowsSelector = new JSpinner();
		rowsSelector.setModel(new SpinnerNumberModel(5, 1, 20, 1));
		rowsSelector.setBounds(101, 245, 39, 20);
		getContentPane().add(rowsSelector);
		rowsSelector.addChangeListener(this);
		
		JLabel lblIloKolumn = new JLabel("Ilo\u015B\u0107 kolumn");
		lblIloKolumn.setBounds(10, 222, 91, 14);
		getContentPane().add(lblIloKolumn);
		
		JLabel lblIloWierszy = new JLabel("Ilo\u015B\u0107 wierszy");
		lblIloWierszy.setBounds(10, 247, 91, 14);
		getContentPane().add(lblIloWierszy);
		
		btnReset = new JButton("reset");
		btnReset.setBounds(152, 217, 57, 25);
		getContentPane().add(btnReset);
		btnReset.addActionListener(this);
		
		btnCt = new JButton("CT");
		btnCt.setBounds(152, 242, 57, 25);
		getContentPane().add(btnCt);
		btnCt.addActionListener(this);
		
		outPanel = new CTpanel(srcPanel);
		outPanel.setBounds(10, 274, 200, 200);
		getContentPane().add(outPanel);
		
		setVisible(true);
		setEnabled(true);
		setSize(240, 525);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(this);
	}
	
	
	
	
	public static void main(String[] args)
	{
		new CTgui();
	}




	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnCt)
		{
			outPanel.loadTab(RectangleMatrixCalculator.getOriginalMatrixApproximation(srcPanel.rowsSums, srcPanel.colsSums, 0.1));
			//srcPanel.randomize();
		}
		if(e.getSource()==btnReset)
		{
			srcPanel.clear();
		}
	}




	@Override
	public void stateChanged(ChangeEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource()==colsSelector)
		{
			srcPanel.setColCount((int) colsSelector.getValue());
			srcPanel.repaint();
			outPanel.reinit(srcPanel);
		}
		if(e.getSource()==rowsSelector)
		{
			srcPanel.setRowCount((int) rowsSelector.getValue());
			srcPanel.repaint();
			outPanel.reinit(srcPanel);
		}
	}

	public void windowClosing(WindowEvent arg0)
	{
		srcPanel.finish();
	}

	public void windowActivated(WindowEvent arg0){}
	public void windowClosed(WindowEvent arg0){}
	public void windowDeactivated(WindowEvent arg0){}
	public void windowDeiconified(WindowEvent arg0){}
	public void windowIconified(WindowEvent arg0){}
	public void windowOpened(WindowEvent arg0){}
	
}
