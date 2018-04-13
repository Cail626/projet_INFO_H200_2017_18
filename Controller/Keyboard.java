package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Game;
import Model.Directable;

public class Keyboard implements KeyListener {
    private Game game;
    private boolean inventoryState;
    
    private static final int player = 0;

    public Keyboard(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        
        if(inventoryState == false){
	        switch (key) {
	        case KeyEvent.VK_RIGHT:
	            game.moveCaracter(1, 0, player);
	            break;
	        case KeyEvent.VK_LEFT:
	            game.moveCaracter(-1, 0, player);
	            break;
	        case KeyEvent.VK_DOWN:
	            game.moveCaracter(0, 1, player);
	            break;
	        case KeyEvent.VK_UP:
	            game.moveCaracter(0, -1, player);
	             break;
	         case KeyEvent.VK_SPACE:
	             game.action(player);
	             break;
	        case KeyEvent.VK_P:
	             game.playerPos(player);
	             break;
	        }
        }
        else{
        	switch (key) {
	        case KeyEvent.VK_RIGHT:
	            game.moveIc(Directable.EAST);
	            break;
	        case KeyEvent.VK_LEFT:
	        	game.moveIc(Directable.WEST);
	            break;
	        case KeyEvent.VK_DOWN:
	        	game.moveIc(Directable.SOUTH);
	            break;
	        case KeyEvent.VK_UP:
	        	game.moveIc(Directable.NORTH);
	             break;
	         case KeyEvent.VK_SPACE:
	             game.action(player);
	             break;
	        }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent event) {
    	int key = event.getKeyCode();
    	switch (key) {
	    case KeyEvent.VK_I:
	    	inventoryState = game.switchInventory();
	    	break;
    	}
    }
}
