package dominio.Blocks;

import java.awt.Color;

import dominio.Tetris.TetrisBlock;

/*
 * Class that generates the L shape and extends TetrisBlock class
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 * 
 */
public class LShape extends TetrisBlock {
	/*
	 * Constructor of the class LShape
	 */
    public LShape(){
        super(new int[][]{{1, 0}, {1, 0}, {1, 1}});
        setColor(Color.ORANGE);
        setLetra("L");
    }

}
