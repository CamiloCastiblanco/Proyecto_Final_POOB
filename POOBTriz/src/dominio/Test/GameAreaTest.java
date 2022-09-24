package dominio.Test;

import dominio.Buffos.Buffo;
import dominio.Tetris.GameArea;
import dominio.Tetris.TetrisBlock;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * The test class GameArea.
 *
 * @author  Barreto-Castiblanco
 * @version 1.0 10/12/2021
 */

class GameAreaTest {
    //Variables para hacer las pruebas:
    private GameArea ga;
    private TetrisBlock block;
    private Buffo buffo;
    private int width = 205 , height = 400;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */

    @Before
    void setUp(){
        // este metodo se invoca antes de ejecutar cada método de prueba (anotados con @Test)
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    }



    @Test
    void deberiaCrearGameArea() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        assertNotEquals(ga, null);
    }

    
    @Test
    void deberiaCrearBloque() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.spawnBlock();
        assertNotEquals(ga.getBlock(), null);
        
    }

    @Test
    void deberiaMoverDerecha() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.spawnBlock();
    	int block = ga.getBlock().getX();
    	ga.getBlock().moveRigth();
        assertNotEquals(ga.getBlock().getX(), block);
    }

    @Test
    void deberiaMoverIzquierda() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.spawnBlock();
    	int block = ga.getBlock().getX();
    	ga.getBlock().moveLeft();
        assertNotEquals(ga.getBlock().getX(), block);
    }

    @Test
    void deberiaMoverBajar() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.spawnBlock();
    	int block = ga.getBlock().getY();
    	ga.getBlock().moveDown();
        assertNotEquals(ga.getBlock().getY(), block);
    }

    @Test
    void deberiaCrearBuffoAleatorio() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.selectBuffo();
        assertNotEquals(ga.getBuffo(), null);
    }

    @Test
    void deberiaCrearBuffoAleatorioValido() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.selectBuffo();
    	String[] tipos = {"buffoSD", "buffoSlow", "buffoST", "buffox2"};
    	boolean buffoValido = Arrays.asList(tipos).contains(ga.getTipoBuffo());
        assertTrue(buffoValido);
    }
    
    
    @Test
    void deberiaCrearTetronimosValido() {
    	ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
    	ga.spawnBlock();
    	Color[] colors = {Color.BLACK, new Color(227,228,229), new Color(239,184,16), Color.RED};
    	boolean tetronimoVal = Arrays.asList(colors).contains(ga.getBlock().getColorBorde());
        assertTrue(tetronimoVal);
    }
    
    @Test
    void noDeberiaSalirseUnBloqueDelAreaPorIzquierda() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.moveBlockLeft();
        ga.moveBlockLeft();
        ga.moveBlockLeft();
        ga.moveBlockLeft(); 
        assertFalse(ga.checkLeft());
    }

    @Test
    void noDeberiaSalirseUnBloqueDelAreaPorDerecha() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.moveBlockRight();
        ga.moveBlockRight();
        ga.moveBlockRight();
        ga.moveBlockRight(); 
        assertFalse(ga.checkRight());
    }

    @Test
    void noDeberiaSalirseUnBloqueDelAreaPorAbajo() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.dropBlock();
        assertFalse(ga.checkBottom());
    }

    @Test
    void noDeberiaBorrarLineasQueNoExisten() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.clearLine(0);
        int x = ga.clearLines();
        assertEquals(x, 0);
    }

    @Test
    void noDeberiaMoverUnBloqueSiYaTieneOtroJustoDebajo() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.dropBlock();
        ga.spawnBlock();
        ga.dropBlock();
        assertFalse(ga.moveBlockDown());
    }
    
    @Test
    void noDeberiaSerNulaUnRotacionValida() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.rotateBlock();
        assertNotEquals(ga.getBlock(), null);
    }

    @Test
    void deberiaAñadirPiezasAlMonton() {
        ga = new dominio.Tetris.GameArea(10, width, height, Color.BLACK);
        ga.spawnBlock();
        ga.dropBlock();
        ga.moveBlockToBackground();
        assertNotEquals(ga.getBlock(), null);
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    void tearDown(){
    }

}

