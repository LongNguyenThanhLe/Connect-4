package project5plus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;


public class PaintProgram extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
    private JMenu colorMenu, shapeMenu, fontMenu;
    private JMenuItem foregroundMenu, backgroundMenu, clearMenu;
    private JMenuItem styleItem, sizeItem;
    private DrawArea drawArea ;
    private TextField textField ;
    private static String shapes[] = {"Straight Line", "Rectangle", "Oval", "Round Rectangle", "Filled Rectangle", "Filled Oval"};
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    HashMap<String, Color> colorMap = new HashMap<>(Map.of(
    		"Black", Color.BLACK,
    		"Blue", Color.BLUE,
    		"Cyan", Color.CYAN,
    		"Green", Color.GREEN,
    		"Magenta", Color.MAGENTA,
    		"Red", Color.RED
    		));
    
    HashMap<String, Integer> sizeMap = new HashMap<>(Map.of(
    		"Size 10", 10,
    		"Size 20", 20, 
    		"Size 72", 72	
    		));
    HashMap<String, Integer> styleMap = new HashMap<>(Map.of(
    		"BOLD", Font.BOLD,
    		"ITALICS", Font.ITALIC,
    		"PLAIN", Font.PLAIN
    		));
    

public PaintProgram() {
	
	this.setTitle("Simple Paint Program");
 	this.setSize(WIDTH, HEIGHT);
 	this.setLocationRelativeTo(null);
 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 	menuBar();
 	
    drawArea = new DrawArea();
    this.add(drawArea , BorderLayout.CENTER);
    
    textField = new TextField("TextField", 300);
    this.add( textField , BorderLayout.SOUTH);
    this.setVisible(true);


}
	
	 private void menuBar() {
    	 // Create the menu bar
        menuBar = new JMenuBar();  
        setJMenuBar(menuBar);
        
        //Create the shape menu
        shapeMenu = new JMenu("SHAPE");
        menuBar.add(shapeMenu);
       
        JMenuItem item; 
        
        for (String shape : shapes) {
            item = new JMenuItem(shape);
        	item.addActionListener(this);
            shapeMenu.add(item);
        }
        
      //COLOR 
        // Create the color menu 
        colorMenu = new JMenu("COLOR");
        menuBar.add(colorMenu);
        
        foregroundMenu = new JMenu("Foreground");
        backgroundMenu = new JMenu("Background");
        colorMenu.add(foregroundMenu);
        colorMenu.add(backgroundMenu);
        colorMenu.addSeparator();
        
        for (String key : colorMap.keySet()) {
        	item = new JMenuItem(key);
         	item.addActionListener(this);
            foregroundMenu.add(item);
        }
        
        for (String key : colorMap.keySet()) {
        	item = new JMenuItem(key);
         	item.addActionListener(this);
            backgroundMenu.add(item);
        }
        
        //Clear
        clearMenu = new JMenuItem("Clear");
        colorMenu.add(clearMenu);
        clearMenu.addActionListener(this);

        //FONT 
        fontMenu = new JMenu("FONT");
        menuBar.add(fontMenu);
        
        styleItem = new JMenu("STYLE");
        sizeItem = new JMenu("SIZE");
        fontMenu.add(styleItem);
        fontMenu.add(sizeItem);
        
        for (String key : styleMap.keySet()) {
        	item = new JMenuItem(key);
         	item.addActionListener(this);
         	styleItem.add(item);
        }
        
        for (String key : sizeMap.keySet()) {
        	item = new JMenuItem(key);
         	item.addActionListener(this);
         	sizeItem.add(item);
                
        }    	
    }
	 
    public static void main(String[] args) {
    	try {
    		PaintProgram paintProgram = new PaintProgram();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}	
   
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		String option = item.getText();
		Font font = textField.getFont();
	
		if (Arrays.asList(shapes).contains(option)) {
			drawArea.setShape(option);
			textField.setText(option);
		} else if(colorMap.containsKey(option)) {
			JPopupMenu popupMenu = (JPopupMenu)item.getParent();
			Component parent = popupMenu.getInvoker();
			String menu =((JMenu)parent).getText();
			if(menu.equals("Foreground")) {
				drawArea.setForeground(colorMap.get(option));
			} else {
				drawArea.setBackground(colorMap.get(option));
			}
		} else if(sizeMap.containsKey(option)) { 
			font = new Font(font.getName(), font.getStyle(), sizeMap.get(option));
			textField.setFont(font);
		}else if(styleMap.containsKey(option)) {
			font = new Font(font.getName(), styleMap.get(option), font.getSize());
			textField.setFont(font);
		}else if(option.equals("Clear")) {
			drawArea.clearShape();
			repaint();
		}
	}   
}
