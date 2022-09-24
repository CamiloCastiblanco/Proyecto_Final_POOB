package dominio.Blocks;

import dominio.Tetris.TetrisBlock;

import java.awt.*;


/*
 * Class that generates the I shape and extends TetrisBlock class
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 *
 */
public class IShape extends TetrisBlock {
	/*
	 * Constructor of the class IShape
	 */
    public IShape(){
        super(new int[][] {{1,1,1,1}});
        setColor(Color.CYAN);
        setLetra("I");
    }
  /*
   * This piece is the only one that has a different rotation and that's why there is a code change
   */
    
    @Override
    public void rotate() {
        super.rotate();
        if(this.getWidth() == 1 ){
            this.setX(this.getX() + 1 );
            this.setY(this.getY() - 1 );
        }
        else{
            this.setX(this.getX() - 1 );
            this.setY(this.getY() + 1 );
        }
    }
}
