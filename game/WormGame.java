package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.domain.Direction;
import wormgame.gui.Updatable;
import wormgame.domain.Worm;
import wormgame.domain.Apple;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;
    private Random randomizer;
    private int count;
    
    public WormGame(int width, int height) {
        super(1000, null);
        
        this.count = 0;
        
        this.randomizer = new Random();

        this.width = width;
        this.height = height;
        this.continues = true;
        
        int appleStartX = this.randomizer.nextInt(width);
        int appleStartY = this.randomizer.nextInt(height);
        
        while (appleStartX == width/2 && appleStartY == height/2){
            appleStartX = this.randomizer.nextInt(width);
            appleStartY = this.randomizer.nextInt(height);
        }
        
        this.worm = new Worm(width/2, height/2, Direction.DOWN);
        this.apple = new Apple(appleStartX, appleStartY);

        addActionListener(this);
        setInitialDelay(2000);
    }
    
    public Worm getWorm(){
        return this.worm;
    }
    
    public void setWorm(Worm worm){
        this.worm = worm;
    }
    
    public Apple getApple(){
        return this.apple;
    }
    
    public void setApple(Apple apple){
        this.apple = apple;
    }
    
    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        
        this.worm.move();
        
        if(this.worm.getX() >= this.width || this.worm.getX() < 0){
            continues = false;
        }
        
        if(this.worm.getY() >= this.height || this.worm.getY() < 0){
            continues = false;
        }
        
        if(this.worm.runsInto(this.apple)){
            int appleStartX = this.randomizer.nextInt(width);
            int appleStartY = this.randomizer.nextInt(height);
        
            while (appleStartX == width/2 && appleStartY == height/2){
                appleStartX = this.randomizer.nextInt(width);
                appleStartY = this.randomizer.nextInt(height);
            }   
        
            this.apple = new Apple(appleStartX, appleStartY);
            
            this.worm.grow();
            
            this.count++;
        }
        
        if(this.worm.runsIntoItself()){
            continues = false;
        }
                
        this.updatable.update();
        
        setDelay(1000/this.worm.getLength());
    }
    
    public String getCount(){
        if(!continues){
            return "YOU LOSE!";
        }
        
        return "Score: " + this.count;
    }

}
