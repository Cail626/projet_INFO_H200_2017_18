package Character;

import Model.Directable;
import Objects.GameObject;
import Objects.InventoryObject;

import java.util.ArrayList;

public abstract class Character extends GameObject implements Directable {

    int life;
    int direction;
    int force;
    ArrayList<InventoryObject> inventory;
    int sizeMaxInventory;
    int characterNumber;
    int color;

    ////////////////////////////////////////////////////////////////////////////////////////

    public Character(int X, int Y, int life, int force, ArrayList<InventoryObject> inventory, int sizeMaxInventory, int characterNumber, int color) {
        super(X, Y, color, characterNumber);
        this.life = life;
        this.force = force;
        this.inventory = inventory;
        this.sizeMaxInventory = sizeMaxInventory;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void move(int X, int Y) {
        this.posX = this.posX + X;
        this.posY = this.posY + Y;
    }

    public void rotate(int x, int y) {
        if(x == 0 && y == -1)
            direction = NORTH;
        else if(x == 0 && y == 1)
            direction = SOUTH;
        else if(x == 1 && y == 0)
            direction = EAST;
        else if(x == -1 && y == 0)
            direction = WEST;
    }


    ////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean isObstacle() {
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void setLife(int change){
        this.life = life + change;
    }

    public void setInventory(InventoryObject object){
        inventory.add(object);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int getDirection() {
    return direction;
    }

    public int getFrontX() {
        int delta = 0;
        if (direction % 2 == 0){
            delta += 1 - direction;
        }
        return this.posX + delta;
    }

    public int getFrontY() {
        int delta = 0;
        if (direction % 2 != 0){
            delta += direction - 2;
        }
        return this.posY + delta;
    }

    public int getLife(){
        return life;
    }

    public int getForce(){
        return force;
    }

    public int getSizeInventory(){
        return inventory.size();
    }

    public int getSizeMaxInventory(){
        return sizeMaxInventory;
    }

    public ArrayList<InventoryObject> getInventory(){
        return inventory;
    }
}
