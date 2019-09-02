/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

/**
 *
 * @author villa
 */
public class Piece {
    private int x;
    private int y;
    
    public Piece(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public boolean runsInto(Piece piece){
        if(this.x == piece.getX() && this.y == piece.getY()){
            return true;
        } else {
            return false;
        }
    }
    
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        
        if(this.getClass() != object.getClass()){
            return false;
        }
        
        Piece compare = (Piece) object;
        
        if(this.x == compare.getX() && this.y == compare.getY()){
            return true;
        } else {
            return false;
        }
    }
    
}
