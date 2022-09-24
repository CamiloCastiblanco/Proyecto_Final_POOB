package dominio.Blocks;

import java.awt.Color;

import dominio.Tetris.TetrisBlock;

/*
 * Class that generates the T shape and extends TetrisBlock class
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 * 
 */
public class TShape extends TetrisBlock {
	/*
	 * Constructor of the class TShape
	 */
    public TShape(){
        super(new int[][] {{1,1 ,1 } , {0,1,0 }});
        setColor(Color.MAGENTA);
        setLetra("T");
    }
    }

