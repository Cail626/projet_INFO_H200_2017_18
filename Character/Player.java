package Character;

import Objects.InventoryObject;

import java.util.ArrayList;

public class Player extends Character {

    private int exp;

    public Player(int X, int Y, int life, int force, ArrayList<InventoryObject> inventory, int sizeMaxInventory, int characterNumber, int color, int exp) {
        super(X, Y, life, force, inventory, sizeMaxInventory, characterNumber, color);
        this.exp = exp;
    }

    @Override
    public void activate() {

    }

    public void setForce(int force){
        this.force = force;
    }

    public void setExp(int exp){
        this.exp = exp;
    }

    public int getExp(){
        return exp;
    }
}
