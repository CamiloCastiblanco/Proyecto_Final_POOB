package dominio.Tetris;

import dominio.Blocks.*;
import dominio.Buffos.*;

import java.awt.*;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/*
 * Class that generates the area where we can play, the most important one. Extends JPanel
 * 
 * @author : Barreto - Castiblanco
 * 
 * @version 1.4 13/12/2021
 * 
 */
public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color color;
    private TetrisBlock block;
    private TetrisBlock[] blocks;
    private Color[][] background;
    private Color[][] backgroundBordes;
    private String[][] fichas;
    private boolean blockLock; 
    private int score, level;
    private Buffo buffo;
    //private Color[] colors = {Color.RED}; 
    private Color[] colors = {Color.BLACK, Color.BLACK, new Color(239,184,16), Color.BLACK, Color.RED, Color.BLACK };
    private Color colorspecial;
    
    /*
     * new Color(227,228,229)
     * Constructor method of GameArea
     * Application front, this method draws basically only the tetris game and there is the reason for its name
     
     */   
    public GameArea(int columns, int width, int height, Color color) {
        this.color = color;
        this.setBounds(0, 0, width, height);
        this.setBackground(color);
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];
        backgroundBordes = new Color[gridRows][gridColumns];
        blockLock = false; 
        fichas = new String[gridRows][gridColumns];
        //blocks = new TetrisBlock[]{new rainbowShape()};
        blocks = new TetrisBlock[]{new IShape() , new LShape() , new OShape() , new SShape() , new TShape()};
        setlevel(1);
        selectBuffo();
        score = 0; 
        level = 1; 
    }

    /*
     * This method is only for paint things. Inside it, we will explain more
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        /*
         * Here we draw only the gridsquare, columns and rows 
         */
        
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke grosor = new BasicStroke(2);
        g2d.setStroke(grosor);
        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= gridRows; i++) {
            g2d.drawLine(0, i* gridCellSize , gridColumns     * gridCellSize ,i* gridCellSize );
        }
        for (int x = 0; x <= gridColumns; x++) {
            g2d.drawLine(x *gridCellSize, 0 , x *gridCellSize , gridRows    * gridCellSize );
        }
        
        drawBuffo(g);
        drawBlock(g);
        drawBackground(g);


        
    }

    /*
     *  Method that allow us to generate random blocks
     */
    public void spawnBlock() {
        Random r = new Random();
        block = blocks[r.nextInt(blocks.length)];
        if(block.getLetra().equals("R")) {
        		block = new rainbowShape();
        }
        colorspecial = colors[r.nextInt(colors.length)];
        block.setColorBorde(colorspecial);
        block.spawn(gridColumns);        
    }

    /*
     * Method that tell us if the bunch of pieces is already on the top of the gamearea
     */
    
    public boolean isBlockOutOfBounds() {
    	
        if (block.getY() <= -1 ) {
        	block = null;
            return true;
        }
        return false;

    }

    /*
     * This method moves down a block if there is nothing below its and if is not already in the bottom or if the game is over
     */
    public Boolean moveBlockDown(){
        
        
        if (!checkBottom() || block == null ) {

            return false;
        }

        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        for(int row = 0; row < h; row++) {
            for( int col = 0; col < w; col++) {
                if(shape[row][col] == 1 ) {
                	if((block.getX() + col == buffo.getX()) && (block.getY() + row == buffo.getY())) {
                		if(buffo.getTipo().equals("buffoST")) {
                			try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                		}else if(buffo.getTipo().equals("buffoSD")) {
                			blockLock = true; 
                		} else if(buffo.getTipo().equals("buffoSlow")){
                			for(int i = 0 ; i < 3; i ++ ) {
                				try {
									Thread.sleep(300);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                			}
                			
                		} 
                		selectBuffo();
                	}
                }
            }
        }
        
        if(!blockLock) {
        	block.moveDown();
        }
        repaint();
        return true;
    }
    /*
     *  This method only tell us if a piece is already in the bottom 
     */
    public boolean checkBottom() {
        if (block.getBottomEdge() == gridRows) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int col = 0; col < w; col++) {
            for (int row = h - 1; row >= 0; row--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }
    /*
     *  This method allow us to move the piece until left limit. If this is not implemented could get out of the gamearea range 
     */
    public boolean checkLeft() {
        if (block.getLeftEdge() == 0 || block == null) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }

    /*
     * This method allow us to move the piece until right limit. If this is not implemented could get out of the gamearea range
     */
    
    public boolean checkRight() {
        if (block.getRightEdge() == gridColumns || block == null) {
            return false;
        }
        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for (int row = 0; row < h; row++) {
            for (int col = w - 1; col >= 0; col--) {
                if (shape[row][col] != 0) {
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if (y < 0) break;
                    if (background[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }

    /*
     * This method draws the pieces, we use a 0-1 matrix where a 1 means a drawed square
     */
    
    private void drawBlock(Graphics g) {
        Color color = block.getColor();
        for (int row = 0; row < block.getHeight(); row++) {
            for (int col = 0; col < block.getWidth(); col++) {
                if (block.getShape()[row][col] == 1) {
                    int x = (block.getX() + col) * gridCellSize;
                    int y = (block.getY() + row) * gridCellSize;
                    drawGridSquare(g, color , x, y, block.getColorBorde());
                }
            }
        }

    }

    /*
     * This method draws every piece in the background. Background means the bunch of the bottom pieces, they don't move but we must to draw them
     */
    
    private void drawBackground(Graphics g) {
        Color color;
        for (int r = 0; r < gridRows; r++) {
            for (int c = 0; c < gridColumns; c++) {
                color = background[r][c];
                if (color != null) {
                    int x = c * gridCellSize;
                    int y = r * gridCellSize;
                    drawGridSquare(g, color ,x, y, backgroundBordes[r][c]);

                }
            }
        }
    }
    /*
     *  This method allow us to paint the blocks gridsquare 
     */
    private void drawGridSquare(Graphics g, Color c,int x, int y, Color borde) {
        
        Image img = null;
        if (Color.CYAN.toString().equals(c.toString())){
            img = new ImageIcon("src/dominio/pictures/blockI.png").getImage();
        } else if (Color.YELLOW.toString().equals(c.toString())){
            img = new ImageIcon("src/dominio/pictures/blockO.png").getImage();
        } else if (Color.ORANGE.toString().equals(c.toString())){
            img = new ImageIcon("src/dominio/pictures/blockL.png").getImage();
        } else if (Color.GREEN.toString().equals(c.toString())){
            img = new ImageIcon("src/dominio/pictures/blockS.png").getImage();
        } else if (Color.MAGENTA.toString().equals(c.toString())){
            img = new ImageIcon("src/dominio/pictures/blockT.png").getImage();
        } else if(Color.RED.toString().equals(c.toString())) {
        	img = new ImageIcon("src/dominio/pictures/blockZ.png").getImage();
        }

        g.drawImage(img, x, y, gridCellSize, gridCellSize, null);
        
        g.setColor(borde);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    /*
     * This method allow us to paint the buffos inside the gamearea
     */

    public void drawBuffo(Graphics g){
        Image img = null;
        if(buffo.getTipo().equals("buffoST")){
            img = new ImageIcon("src/dominio/pictures/buffoST.png").getImage();
        } else if(buffo.getTipo().equals("buffoSD")){
            img = new ImageIcon("src/dominio/pictures/buffoSD.png").getImage();
        } else if(buffo.getTipo().equals("buffoSlow")){
            img = new ImageIcon("src/dominio/pictures/buffoSlow.png").getImage();
        } else if(buffo.getTipo().equals("buffox2")){
            img = new ImageIcon("src/dominio/pictures/buffox2.png").getImage();
        }

        g.drawImage(img, buffo.getX()*gridCellSize, buffo.getY()*gridCellSize, gridCellSize, gridCellSize, null);
    }

    /*
     *  This method locks a block to the background when it already reach the bottom and then continue with other block 
     *  There is not saved pieces, is saved a colors matrix
     */
    
    public void moveBlockToBackground() {
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                if (shape[r][c] == 1) {
                    background[r + yPos][c + xPos] = color;
                    backgroundBordes[r + yPos][c + xPos] = block.getColorBorde();
                    fichas[r + yPos][c + xPos] = Integer.toString(block.getCurrentRotation()) + block.getLetra();
                }
            }
        }

        if(block.getColorBorde().equals(Color.RED)){
        	score += 2; 
            bomb(block);
        }
   

    }

    /*
     * The bomb piece method
     */
    public void bomb(TetrisBlock bloque){
        int[][] shape = block.getShape();
        int h = bloque.getHeight();
        int w = bloque.getWidth();
        int xPos = bloque.getX();
        int yPos = bloque.getY();
        for (int r = 0; r < h; r++) {
            for (int c = 0; c < w; c++) {
                setNull(r + yPos, c + xPos  );  
            }
        }
        
        if(xPos - 1 >= 0 ) {
        	
        	for(int r = 0; r < h; r++) {
        		setNull(r + yPos, xPos -1  );
        	} 
        	 
        }
        if(yPos + h < gridRows ) {
        	for (int c = 0; c < w; c++) {
        		setNull(yPos + h , xPos + c); 
            }
        	
        }
        
        if(xPos + w < gridColumns ) {
        	for(int r = 0; r < h; r++) {
        		setNull(r + yPos, xPos + w );
        	} 
        }
        
        
        


    }
    public void setNull(int y , int x ) {
    	background[y][x] = null;
        backgroundBordes[y][x] = null;
        fichas[y][x] = null;
    } 

    /*
     * This method moves a block to the right
     */
    public void moveBlockRight() {
        if (!checkRight()) return;
        block.moveRigth();
        repaint();
    }
    /*
     * This method moves a block to the Left
     */

    public void moveBlockLeft() {
        if (!checkLeft()) return;
        block.moveLeft();
        repaint();
    }

    /**
     * Every time when a limit is reached we must to verify:
     * The piece cannot will be out of the bounds 
     * The piece is not on the top of the bunch (game over )
     * The next piece rotation should not above another piece
     * 
     */
    public void rotateBlock() {
        if (block != null) {
            block.rotate();
            repaint();
        }

        if(block.getLeftEdge() < 0 ) block.setX(0);
        if(block.getBottomEdge() < 0) block.setX(0);
        if(block.getRightEdge() >= gridColumns) block.setX(gridColumns - block.getWidth());
        if(block.getBottomEdge() >= gridRows) block.setY(gridRows - block.getHeight());

        checkValidRotation(); // Here we verify if a block is rotating above another one, if this is true the piece come back to the previous state
    }

    /*
     * This method checks the next piece rotation. 
     * If any of the piece part is touching the background it means a wrong rotation and the piece come back to the previous state
     */
    public void checkValidRotation(){
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        for(int row = 0; row < h; row++) {
            for( int col = 0; col < w; col++) {
                if(shape[row][col] != 0) {
                    int x = col + block.getX();
                    int y = row + block.getY();
                    if(y < 0)
                        break;
                    if(background[y][x] != null){
                        block.rotateBack();
                        repaint();
                        return;
                    }

                }
            }
        }
    }
    /*
     * This method moves a block to the bottom instantly
     */
    public void dropBlock() {
        while (checkBottom()) {
            block.moveDown();
        }
        repaint();
    }
    /*
     * This method clears a line when is already finished
     */

    public int clearLines() {
        boolean linefield;
        Color g = new Color(227,228,229); 
        int linescleared = 0;
        for (int r = gridRows - 1; r >= 0; r--) {
            linefield = true;
            for (int c = 0; c < gridColumns; c++) {
            	
                if (background[r][c] == null || backgroundBordes[r][c].equals(g)) {
                    linefield = false;
                    break;
                }

            }
            if (linefield) {
                clearLine(r); // Here we clear a specific line
                shiftDown(r); //This moves down the lines when another below it is cleared
                clearLine(0); //This update the new bottom line
                r++; //This allow us to clear various lines simultaneously                
                linescleared++; // This give a point when a line is cleared. 1 point for each line

                repaint();
            }
        }
        score += linescleared; 
        
        if( score > 3  ) {
        	level += 1; 
        	score = 0; 
        }

        
        return linescleared;
    }
    /*
     * This method clear a specific line
     */
    public void clearLine(int r) {
        for (int i = 0; i < gridColumns; i++) {
            background[r][i] = null;
            backgroundBordes[r][i] = null;
            fichas[r][i] = null;
        }
    }
    
    /*
     *  This method moves down the lines when another below it is cleared
     */
    
    public void shiftDown(int r) {
        for (int row = r; row > 0; row--) {
            for (int col = 0; col < gridColumns; col++) {
                background[row][col] = background[row - 1][col];
                backgroundBordes[row][col] = backgroundBordes[row - 1][col];
                fichas[row][col] = fichas[row - 1][col];
            }
        }
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setScore(int score) {

        this.score = score;
    }

    public void setlevel(int level) {
        this.level = level;
    }
    
    public TetrisBlock getBlock() {
        return block;
    }

    public Buffo getBuffo() {
        return buffo;
    }
    
    public String getTipoBuffo() {
        return buffo.getTipo();
    }
    /*
     * This method choose a type of buffo randomly and where it will spawn or be drawn
     */
    public void selectBuffo(){
        String[] tipos = {"buffoSD", "buffoSlow", "buffoST", "buffox2"};
        Random r = new Random();
        int x = r.nextInt(gridColumns);
        int y = r.nextInt(gridRows/2);
        while(background[x][y] !=null){
            x = r.nextInt(gridColumns);
            y = r.nextInt(gridRows/2);
        }
        buffo = new Buffo(tipos[r.nextInt(tipos.length)], x, y);
    }
    
    public void unlock() {
    	blockLock = false; 
    }
    
    public void changeColor() {
    	if(block.getLetra().equals("R")) {
    		((rainbowShape) block).changeColor();
    	}
    }

}

