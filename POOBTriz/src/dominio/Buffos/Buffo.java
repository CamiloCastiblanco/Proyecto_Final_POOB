package dominio.Buffos;

public class Buffo{
	/*
	 * Class that generates all kind of power ups that must be in the project 
	 * 
	 * @author : Barreto - Castiblanco
	 * 
	 * @version 1.4 13/12/2021
	 * 
	 */

    private String tipoBuffo;
    private int xPos, yPos;

    /*
	 * Constructor of the class SShape
	 */
    public Buffo(String tipo, int x, int y){
        this.tipoBuffo = tipo;
        this.xPos = x;
        this.yPos =y;

    }

    public String getTipo(){
        return tipoBuffo;
    }
    public void setBuffo(String tipo){
        this.tipoBuffo = tipo;
    }

    public int getX(){
        return xPos;
    }
    public void setX(int x){
        this.xPos = x;
    }

    public int getY(){
        return yPos;
    }
    public void setY(int y){
        this.yPos = y;
    }

}