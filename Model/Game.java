package Model;

import Objects.GameObject;
import Objects.HealingConsumable;
import Objects.InventoryObject;
import View.Window;
import Character.Player;

import java.util.ArrayList;
import java.util.Random;

public class Game implements DeletableObserver {

    private ArrayList<GameObject> objects = new ArrayList<>();
    private Window window;
    private int size = 20;
    private int numberOfBreakableBlocks = 10;

    ////////////////////////////////////////////////////////////////////////////////////////

    public Game(Window window) {
        this.window = window;

        // Creating one Player at position (10,10)
        objects.add(new Player(10, 10, 3, 5, new ArrayList<InventoryObject>(), 5, 1, 1, 0));

        // Map building
        for (int i = 0; i < size; i++) {
            objects.add(new BlockUnbreakable(i, 0));  // Solid boundaries, cannot be crossed
            objects.add(new BlockUnbreakable(0, i));
            objects.add(new BlockUnbreakable(i, size - 1));
            objects.add(new BlockUnbreakable(size - 1, i));
        }
        Random rand = new Random();
        for (int i = 0; i < numberOfBreakableBlocks; i++) {
            int x = 1+i;
            int y = 1+i;
            int lifepoints = rand.nextInt(5) + 1;
            BlockBreakable block = new BlockBreakable(x, y, lifepoints);
            block.attachDeletable(this);
            objects.add(block);
        }

        // Creating one Healing Consumable at position (5,2)
        HealingConsumable potion = new HealingConsumable(5,2,2, 5, "potion", 3);
        potion.attachDeletable(this);
        objects.add(potion);

        window.setGameObjects(this.getGameObjects());
        notifyView();
        //System.out.println(objects);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void movePlayer(int x, int y, int playerNumber) {
        Player player = ((Player) objects.get(playerNumber));
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
			        aimedObject =  object;
			    }
			}
		}
		if(aimedObject != null){
		    if(aimedObject instanceof InventoryObject){
		        ArrayList<InventoryObject> inventory = player.getInventory();
		        int sizeMax = player.getSizeMaxInventory();
		        int number = player.getSizeInventory();
		        if(number < sizeMax){
		            player.setInventory((InventoryObject) aimedObject);
		            aimedObject.activate();
                    notifyView();
                }else{
                    System.out.println("Inventaire plein !");
                }
            }else {
                aimedObject.activate();
                notifyView();
            }
		}
        
    }

    private void notifyView() {
        window.update();
    }

    @Override
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

    ////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<GameObject> getGameObjects() {
        return this.objects;
    }

}