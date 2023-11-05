package project5plus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class DrawArea  extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private static final int arcWidth = 20;
	private static final int arcHeight = 20;
	
	private int startX, startY, endX, endY;
	private String shape = "Oval";
	
	public DrawArea() {
		setBackground(Color.cyan);
		addMouseListener(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
	
		if (shape.equals("Straight Line")){
			g.drawLine(startX, startY, endX, endY);
		} else if(shape.equals("Rectangle")) {
			g.drawRect(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
		} else if(shape.equals("Oval")) {
			g.drawOval(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
		} else if(shape.equals("Round Rectangle")) {
			g.drawRoundRect(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY), arcWidth, arcHeight);
		}else if(shape.equals("Filled Rectangle")) {
			g.fillRect(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
		}else if(shape.equals("Filled Oval")) {
			g.fillOval(Math.min(startX, startY), Math.min(startY, endY), Math.abs(endX - startX), Math.abs(endY - startY));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		startX = e.getX();
		startY= e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		repaint();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	public void setShape(String shape) {
		this.shape = shape;
		
	}

	public void clearShape() {
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
		repaint();
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		endX = e.getX();
		endY = e.getY();
		repaint();
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}
}