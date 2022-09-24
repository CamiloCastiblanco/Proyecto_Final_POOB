package dominio.Tetris;

import java.awt.*;
import java.util.Random;

/**
 * This class represents all the required pieces for the project. The pieces are I,L,T,O,S, and the rainbow new one
 * 
 *@author : Barreto - Castiblanco 
 *
 * @version 1.4 13/12/2021
 * 
*/


public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int[][][] shapes;
    private int currentrotation;
    private int x , y;
    private String letra;
    private Color[] availablecolors = {Color.CYAN , Color.YELLOW, Color.ORANGE, Color.GREEN, Color.MAGENTA};
    private Random r;
    private Color colorBorde;
    public TetrisBlock(int[][] shape ) {
    	/*
         * Constructor method of GameArea
         *
         */

        this.shape = shape;
        initShapes();
        r = new Random();
    }
    /*
     * This method initialize a piece
     *
     */
    private void initShapes(){
    	
        shapes = new int [4][][];// This array represents the possible rotations of each piece

        for(int i =  0 ; i < 4 ; i++ ){
            int r = shape[0].length;
            int c = shape.length;

            shapes[i] = new int[r][c];
            for (int y = 0 ; y < r ; y ++){
                for(int x = 0 ; x < c ; x ++ ){
                    shapes[i][y][x] = shape[c -x - 1 ][y];
                }
            }
            shape = shapes[i];
        }
    }
    public Color getColor() {
        return color;
    }

    public int[][] getShape() {
        return shape;
    }
    public int getHeight(){
        return shape.length;
    }
    public int getWidth(){
        return shape[0].length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y ){
        this.y = y;
    }

    public Color getColorBorde(){
        return colorBorde;
    }
    public void setColorBorde(Color borde){
        colorBorde = borde;
    }

    public int getCurrentRotation(){
        return currentrotation;
    }

    public void setLetra(String letra){
        this.letra = letra;
    }
    public String getLetra(){
        return letra;
    }

    /*
     * These methods are for the piece movement Right one, left one and down one, respectively
     */

    public void moveLeft(){
        x--;
    }
    public void moveRigth(){
        x++;
    }
    public void moveDown(){
        y++;
    }
    public void rotate(){ // The rotation is given by the saved states in shapes

        currentrotation++;
        if(currentrotation> 3){
            currentrotation = 0 ;
        }
        shape = shapes[currentrotation];
    }
    public void rotateBack(){
        currentrotation--;
        if (currentrotation < 0) {
            currentrotation = 3;
        }
        shape = shapes[currentrotation];
    }

    /*
     * This method allow us to control the spawn place of the piece. By default them spawn centered and with no rotation or initial rotation
     */
    
    public void spawn(int gridWidth){ //
        currentrotation = 0;
        shape = shapes[currentrotation];
        y = -getHeight();
        x = (gridWidth - getWidth()) / 2;

    }

    public int getBottomEdge(){
        return  y + getHeight();
    }
    public int getLeftEdge(){
        return x;
    }
    public int getRightEdge(){
        return x + getWidth();
    }

    public void setColor(Color color){
        this.color = color;
    }



}
