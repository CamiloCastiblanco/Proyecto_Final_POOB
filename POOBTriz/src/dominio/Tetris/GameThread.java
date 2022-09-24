package dominio.Tetris;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dominio.Tetris.GameArea;
import presentacion.*;

public class GameThread extends Thread{
    private GameArea ga;
    private GameOne gf ;
    private GameTwo gff;
    private int pause = 500;
    private int speedupPerLevel = 50;
    private boolean accelerated; 
    private int lastLevel = 0 ; 
    private boolean bandera; 
    
    /*
     * New Typography Elements
     */
    private Font font;

    /*
     * Files elements
     */
    private File file;
    private String path;

    /*
     *  The thread needs the gamearea to interact with the elements and the game form to manage the SCORE and LEVEL
     */
    public GameThread(GameArea ga, GameOne gf, boolean accelerated){
        this.ga = ga;
        this.gf = gf ;
        this.accelerated = accelerated; 
        bandera = true; 
    }

    public GameThread(GameArea ga, GameTwo gff, boolean accelerated ){
        this.ga = ga;
        this.gff = gff ;
        bandera = true; 
    }
    /*
     * Thread that allow us run the MoveDown method and obtain score and level of each game
     * this happens every time pause, when the level is going up, the MoveDown time is going down    
     */
    @Override
    public void run(){
        while(bandera) {
            ga.spawnBlock();
            while(ga.moveBlockDown()) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // If the game is over, the thread finish
            if(ga.isBlockOutOfBounds()){
            	gameOver();
                break;
            }
            ga.moveBlockToBackground();
            ga.clearLines();
	            if(gf != null){
	                gf.updateScore();
	                gf.updateLevel();
	            } else{
	                gff.updateScore1();
	                gff.updateLevel1();
	                gff.updateScore2();
	            }
	        }
        	if(ga.getLevel() != lastLevel) {
	            if(accelerated && !(pause-speedupPerLevel < 500 ) ) {
	            	lastLevel = ga.getLevel(); 
	                pause -= speedupPerLevel;
	            }
        	}
        	bandera = getBandera() ; 
        	
     }
    
    
    public void setPause(int pause) {
    	this.pause = pause; 
    } 
    
    public int getpause() {
    	return pause; 
    	
    }
    
    public void gameOver() {
    	
    	file = new File("");
        path = file.getAbsolutePath();

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\src\\presentacion\\fontType\\PixelMplus12-Regular.ttf")));
        } catch (IOException | FontFormatException e) {
            //Doesn't catch anything
        }
    	
    	ImageIcon icon = new ImageIcon("src/presentacion/pictures/gameOver.png");
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(380, 180, 50);
        icon = new ImageIcon(newimg);

        JLabel title = new JLabel();
        font = font.deriveFont(18f);
        title.setFont(font);
        final JComponent[] inputs = new JComponent[] {title};

        int reply = JOptionPane.showConfirmDialog(null, inputs, "Game Over!",
                JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
        
        if (reply == JOptionPane.YES_OPTION) {
        	gf.setDefaultCloseOperation(gf.DO_NOTHING_ON_CLOSE);
        } else if(reply == JOptionPane.NO_OPTION) {
        	System.exit(0);
        }

    }
    
    public void pause() {
    	bandera = false; 
    	
    }
    public void resumen() {
    	bandera = true; 
    }
    public boolean getBandera() {
    	return bandera; 
    } 
    
    
}
