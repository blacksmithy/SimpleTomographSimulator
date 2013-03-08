package stcs.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JPanel;

public class CTpanel extends JPanel implements MouseListener, ActionListener, MouseMotionListener
{
	private static final long serialVersionUID = 4225294197220654404L;

	double tab[][];
	public double colsSums[];
	public double rowsSums[];
	int rows,cols;
	double min,max;
	Color beg=Color.BLACK,end=Color.CYAN;
	Dimension gridSize;
	PowerCounter counter;
	
	public CTpanel(int rowCount,int colCount)
	{
		super();
		rows=rowCount;
		cols=colCount;
		min=0;
		max=1;
		tab=new double[rows][cols];
		colsSums= new double[cols];
		rowsSums= new double[rows];
		gridSize=new Dimension(150, 150);
		counter=new PowerCounter(this);
		counter.start();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public CTpanel(CTpanel in)
	{
		super();
		reinit(in);
	}
	
	public void reinit(CTpanel in)
	{
		min=in.min;
		max=in.max;
		cols=in.cols;
		rows=in.rows;
		gridSize=in.gridSize;
		colsSums= new double[cols];
		rowsSums= new double[rows];
		tab=new double[rows][cols];
		repaint();
	}
	
	public void loadTab(double inTab[][])
	{
		min=max=inTab[0][0];
		for(int r=0;r<rows;r++)
		{
		   for(int c=0;c<cols;c++)
		   {
			   if(inTab[r][c]<min){min=inTab[r][c];}
			   if(inTab[r][c]>max){max=inTab[r][c];}
		   }
		}
		tab=inTab;
		recalcSums();
		repaint();
	}
	
	public void randomize()
	{
		Random rand=new Random();
		for(int r=0;r<rows;r++)
		{
		   for(int c=0;c<cols;c++)
		   {
			   tab[r][c]=rand.nextDouble()%1;
		   }
	   }
		recalcSums();
		repaint();
	}
	
	public void clear()
	{
		for(int r=0;r<rows;r++)
		{
		   for(int c=0;c<cols;c++)
		   {
			   tab[r][c]=0;
		   }
	   }
		recalcSums();
		repaint();
	}
	
	public void setStep(int step)
	{
		if(counter!=null)
		{
			counter.setDelay(step);
		}
	}
	
	Color getGradColor(double val)
	{
		double gr=(val-min)/(max-min);
		int r=0,g=0,b=0;
		r=beg.getRed()+(int)((double)(end.getRed()-beg.getRed())*gr);
		g=beg.getGreen()+(int)((double)(end.getGreen()-beg.getGreen())*gr);
		b=beg.getBlue()+(int)((double)(end.getBlue()-beg.getBlue())*gr);
		Color c=new Color(r,g,b);
		return c;
	}
	
	Color getGradSumColor(double val)
	{
		double gr=(val-min)/(max-min);
		int rgb=255 - (int) (gr*225);
		Color c=new Color(rgb,rgb,rgb);
		return c;
	}
	
	protected void paintComponent(Graphics gg) {
	   super.paintComponent(gg);       
	   Graphics2D g = (Graphics2D) gg;
	   g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	   int fieldWidth=gridSize.width/cols;
	   int fieldHeight=gridSize.height/rows;
	   for(int r=0;r<rows;r++)
	   {
		   for(int c=0;c<cols;c++)
		   {
			   g.setColor(getGradColor(tab[r][c]));
			   g.fillRect(fieldWidth*c, fieldHeight*r, fieldWidth, fieldHeight);
		   }
	   }
	   
	   recalcSums();
	   for(int r=0;r<rows;r++)
		{
			g.setColor(getGradSumColor(rowsSums[r]/cols));
			g.fillRect(fieldWidth*cols+10, fieldHeight*r, 10, fieldHeight);
		}
		for(int c=0;c<cols;c++)
		{
			g.setColor(getGradSumColor(colsSums[c]/rows));
			g.fillRect(fieldWidth*c, fieldHeight*rows+10, fieldWidth, 10);
		}
		
		g.setColor(Color.BLACK);
		g.drawRect(fieldWidth*cols+10, 0, 10, fieldHeight*rows);
		g.drawRect(0, fieldHeight*rows+10, fieldWidth*cols, 10);
	}
	
	public void setColCount(int colCount)
	{
		double nTab[][]=new double[rows][colCount];
		double nCols[]=new double[colCount];
		if(cols<=colCount)
		{
			for(int r=0;r<rows;r++)
			{
				for(int c=0;c<cols;c++)
				{
					nTab[r][c]=tab[r][c];
				}
			}
			for(int i=0;i<cols;i++)
			{
				nCols[i]=colsSums[i];
			}
		}
		else
		{
			for(int r=0;r<rows;r++)
			{
				for(int c=0;c<colCount;c++)
				{
					nTab[r][c]=tab[r][c];
				}
			}
			for(int i=0;i<colCount;i++)
			{
				nCols[i]=colsSums[i];
			}
		}
		cols=colCount;
		tab=nTab;
		colsSums=nCols;
	}
	
	public void setRowCount(int rowCount)
	{
		double nTab[][]=new double[rowCount][cols];
		double nRows[]=new double[rowCount];
		if(rows<=rowCount)
		{
			for(int r=0;r<rows;r++)
			{
				for(int c=0;c<cols;c++)
				{
					nTab[r][c]=tab[r][c];
				}
				nRows[r]=rowsSums[r];
			}
		}
		else
		{
			for(int r=0;r<rowCount;r++)
			{
				for(int c=0;c<cols;c++)
				{
					nTab[r][c]=tab[r][c];
				}
				nRows[r]=rowsSums[r];
			}
		}
		rows=rowCount;
		tab=nTab;
		rowsSums=nRows;
	}
	
	void recalcSums()
	{
		for(int r=0;r<rows;r++)
		{
			rowsSums[r]=0;
			for(int c=0;c<cols;c++)
			{
				rowsSums[r]+=tab[r][c];
			}
		}
		for(int c=0;c<cols;c++)
		{
			colsSums[c]=0;
			for(int r=0;r<rows;r++)
			{
				colsSums[c]+=tab[r][c];
			}
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	int currR=0,currC=0;
	int dir=0;
	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getButton()==MouseEvent.BUTTON1) {dir=1;}
		if(e.getButton()==MouseEvent.BUTTON3) {dir=-1;}
		counter.setNotification(true);
		currC=e.getX()/(gridSize.width/cols);
		currR=e.getY()/(gridSize.height/rows);
	}



	@Override
	public void mouseReleased(MouseEvent e)
	{
		dir=0;
		counter.setNotification(false);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			tab[currR][currC]+=0.01*dir;
			if(tab[currR][currC]<min) {tab[currR][currC]=min;}
			else if(tab[currR][currC]>max) {tab[currR][currC]=max;}
			repaint();
		}
		catch(ArrayIndexOutOfBoundsException e1) {}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		currC=e.getX()/(gridSize.width/cols);
		currR=e.getY()/(gridSize.height/rows);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		currC=e.getX()/(gridSize.width/cols);
		currR=e.getY()/(gridSize.height/rows);
	}

	public void finish()
	{
		counter.finish();
	}

}
