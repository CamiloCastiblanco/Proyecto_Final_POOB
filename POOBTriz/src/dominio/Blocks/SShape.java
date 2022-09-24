package dominio.Blocks;

import java.awt.Color;

import dominio.Tetris.TetrisBlock;

/*
 * Class that generates the S shape and extends TetrisBlock class
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 * 
 */
public class SShape extends TetrisBlock {
	/*
	 * Constructor of the class SShape
	 */

    public SShape(){
        super(new int[][] {{0  ,1 ,1 }, { 1, 1, 0}});
        setColor(Color.GREEN);
        setLetra("S");
    }

}
