/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private int x;
    private int y;
    private boolean growOrNot;
    private Direction direction;
    private List<Piece> worm;
    
    public Worm(int originalX, int originalY, Direction originalDirection){
        this.x = originalX;
        this.y = originalY;
        this.direction = originalDirection;
        this.worm = new ArrayList<Piece>();
        this.worm.add(new Piece(originalX, originalY));
        this.growOrNot = true;
    }
    
    public Direction getDirection(){
        return this.direction;
    }
    
    public void setDirection(Direction dir){
        this.direction = dir;
    }
    
    public int getLength(){
        return this.worm.size();
    }
    
    public List<Piece> getPieces(){
        return this.worm;
    }
    
    public void grow(){
        this.growOrNot = true;
    }
    
    public void move(){
        Piece head = this.worm.get(this.worm.size() - 1);
        
        if(this.direction == Direction.UP){
            Piece piece = new Piece(head.getX(), head.getY() - 1);
            this.worm.add(piece);
        } else if (this.direction == Direction.DOWN){
            Piece piece = new Piece(head.getX(), head.getY() + 1);
            this.worm.add(piece);
        } else if (this.direction == Direction.RIGHT){
            Piece piece = new Piece(head.getX() + 1, head.getY());
            this.worm.add(piece);
        } else if (this.direction == Direction.LEFT){
            Piece piece = new Piece(head.getX() - 1, head.getY());
            this.worm.add(piece);
        }
        
        if(this.worm.size() >= 3 && !growOrNot){
            this.worm.remove(0);
        }
        
        if(this.worm.size() >= 3){
            growOrNot = false;
        }
    }
    
    public int getX(){
        return this.worm.get(this.worm.size() - 1).getX();
    }
    
    public int getY(){
        return this.worm.get(this.worm.size() - 1).getY();
    }
    
    public boolean runsInto(Piece piece){
        for(Piece section : this.worm){
            if(section.runsInto(piece)){
                return true;
            }
        }
        return false;
    }
    
    public boolean runsIntoItself(){
        for(Piece section : this.worm){
            for(Piece piece : this.worm){
                if(section == piece){
                    continue;
                }
                
                if(section.getX() == piece.getX() && section.getY() == piece.getY()){
                    return true;
                }
            }
         
        }
        
        return false;
    }
}
