package dominio.Blocks;

import java.awt.Color;
import java.awt.*;

import dominio.Tetris.TetrisBlock;


/**
 * Esta clase se encarga de crear el nuevo tetrómino llamado rainbow, 
 * que cada vez que el usuario lo mueve este cambia de color, los colores del arcoiris.
 * @author Yeison Barreto
 *
 */
public class rainbowShape extends TetrisBlock {
	
	private int numRainbow = 0 ; 
	private Color[] colorsRainbow = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.MAGENTA};
	
    public rainbowShape(){
        super(new int[][] {{0  ,1 , 1  ,0 }, { 1, 0, 0 , 1}});
        numRainbow = 0;
        setColor(Color.RED);
        setLetra("R");
    }
    
    public void changeColor() {
    	numRainbow += 1;
    	if(numRainbow <= 5 ) {
    		setColor(colorsRainbow[numRainbow]);
    	} else {
    		numRainbow = 0;
    		setColor(colorsRainbow[numRainbow]);
    	}
    	
    }
}
