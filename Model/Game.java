package Model;

import Objects.*;
import View.Window;
import Character.Player;

import java.util.ArrayList;
import java.util.Random;

import java.awt.event.KeyEvent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Character.Player;
import Objects.GameObject;

public class Game implements DeletableObserver {
    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    private Window window;
    private int size = 20;
    // private int bombTimer = 3000;
    //private int numberOfBreakableBlocks = 120;

    public Game(Window window) {
        this.window = window;
        // Creating one Player at position (1,1)
        
        Player player = new Player(10, 10, 3, 5, new ArrayList<InventoryObject>(), 5, 1, 1, 0);
        objects.add(player);
        // Map building
        buildMap("map0.text");
    }
    
    private void buildMap(String file){
    	
    	String text = "";
    	try{
    		BufferedReader in = new BufferedReader(new FileReader(file));
    	
    		text = in.readLine();
    				
    		while(text != null){
    			text = text.trim();
    			
    			if ("size".compareTo(text) == 0){
    	        	size = Integer.valueOf(in.readLine());
    			}
    			else if("option".compareTo(text) == 0){
    				mapOption(in);
    			}
    			else if("gameObject(id,x,y)".compareTo(text) == 0){
    				mapObject(in);
    			}
	    	text = in.readLine();
    		}
    		
    		/*Random rand = new Random();
	        for (int i = 0; i < numberOfBreakableBlocks; i++) {
	            int x = rand.nextInt(16) + 2;
	            int y = rand.nextInt(16) + 2;
	            int lifepoints = rand.nextInt(5) + 1;
	            BlockBreakable block = new BlockBreakable(x, y, lifepoints);
	            block.attachDeletable(this);
	            objects.add(block);
	        }*/
    		
    		window.setGameObjects(this.getGameObjects());
    		in.close();
	        
	    } catch(FileNotFoundException e){
			System.out.println(e);
		} catch( IOException e){
			System.out.println(e);
		}
	    notifyView();
    }
    
    private void mapObject(BufferedReader in) throws IOException{
    	String[] text;
    	int id;
    	int x;
    	int y;
    	int numObject = Integer.valueOf(in.readLine());
    	for(int i=0; i<numObject; i++){
    		text = in.readLine().split(" ");
    		id = Integer.valueOf(text[0]);
    		x = Integer.valueOf(text[1]);
    		y = Integer.valueOf(text[2]);
    		switch(id){
    			case 1:
    				BlockBreakable block = new BlockBreakable(x, y, 4);
    				block.attachDeletable(this);
    	            objects.add(block);
    				break;
    		}
    	}
    }
    
    private void mapOption(BufferedReader in) throws IOException{
    	String text;
    	text = in.readLine().trim();
    	if ("wall".compareTo(text) == 0) {
	    	for (int i = 0; i < size; i++) {
	            objects.add(new BlockUnbreakable(i, 0));
	            objects.add(new BlockUnbreakable(0, i));
	            objects.add(new BlockUnbreakable(i, size - 1));
	            objects.add(new BlockUnbreakable(size - 1, i));
	        }
    	}
    }

    public void moveCaracter(int x, int y, int caracterNumber) {
        Player player = ((Player) objects.get(caracterNumber));
        int nextX = player.getPosX() + x;
        int nextY = player.getPosY() + y;

        boolean obstacle = false;
        for (GameObject object : objects) {
            if (object.isAtPosition(nextX, nextY)) {
                obstacle = object.isObstacle();
            }
            if (obstacle == true) {
                break;
            }
        }
        player.rotate(x, y);
        if (obstacle == false) {
            player.move(x, y);
        }
        notifyView();
    }

    public void action(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        Activable aimedObject = null;
  		for(GameObject object : objects){
  			if(object.isAtPosition(player.getFrontX(),player.getFrontY())){
  			    if(object instanceof Activable){
  			        aimedObject = (Activable) object;
  			    }
  			}
  		}
  		if(aimedObject != null){
  		    aimedObject.activate();
              notifyView();
  		}
          
    }
    
    public void showInventory(){
    	window.showInventory();
    }

    private void notifyView() {
        window.update();
    }

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

    @Override
    // ps est un objet qui sera supprimé(il doit donc être deletable)
    // .remove -> fct d'une ArrayList
    synchronized public void delete(Deletable ps, ArrayList<GameObject> loot) {
        objects.remove(ps);
        if (loot != null) {
            objects.addAll(loot);
        }
        notifyView();
    }


    public void playerPos(int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
        System.out.println(player.getPosX() + ":" + player.getPosY());
        
    }

}
