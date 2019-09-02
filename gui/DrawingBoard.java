package wormgame.gui;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import wormgame.game.WormGame;
import wormgame.domain.Apple;
import wormgame.domain.Piece;
import java.awt.Font;

public class DrawingBoard extends JPanel implements Updatable{
    private WormGame game;
    private int pieceLength;
    
    public DrawingBoard(WormGame game, int pieceLength){
        this.game = game;
        this.pieceLength = pieceLength;
    }
    
    @Override
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        
        graphics.setColor(Color.BLACK);
        
        for(Piece piece : this.game.getWorm().getPieces()){
            graphics.fill3DRect(piece.getX() * pieceLength, piece.getY() * pieceLength, pieceLength, pieceLength, true);
        }
        
        Font font = new Font("Calibri", Font.BOLD, 15);
        graphics.setFont(font);
        
        graphics.drawString("" + this.game.getCount(), this.game.getWidth()/2, 20);
        
        graphics.setColor(Color.RED);
        graphics.fillOval(this.game.getApple().getX() * pieceLength, this.game.getApple().getY() * pieceLength, pieceLength, pieceLength);
    }
    
    @Override
    public void update(){
        super.repaint();
    }
}
