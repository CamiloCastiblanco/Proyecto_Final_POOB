package dominio.Blocks;

import java.awt.Color;

import dominio.Tetris.TetrisBlock;

/*
 * Class that generates the O shape and extends TetrisBlock class
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 * 
 */
public class OShape extends TetrisBlock {
	/*
	 * Constructor of the class OShape
	 */
    public OShape(){
        super(new int[][] {{1 , 1}, { 1, 1 }});
        setColor(Color.YELLOW);
        setLetra("O");
    }

}
