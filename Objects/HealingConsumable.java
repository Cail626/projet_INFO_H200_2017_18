package Objects;

import Model.DeletableObserver;

import java.util.ArrayList;

public class HealingConsumable extends Consumable{

    private ArrayList<DeletableObserver> observers = new ArrayList<DeletableObserver>();
    private int healingPower = 0;

    public HealingConsumable(int X, int Y, int color, int id, String description, int healingPower) {
        super(X, Y, color, id, description);
        this.healingPower = healingPower;
    }

    public void consume(){

    }

    public void setHealingPower(int healingPower){
        this.healingPower = healingPower;
    }

    public int getHealingPower(){
        return healingPower;
    }

    @Override
    public void attachDeletable(DeletableObserver po) {
        observers.add(po);
    }

    @Override
    public void notifyDeletableObserver() {
        int i = 0;
        for (DeletableObserver o : observers) {
            i++;
            o.delete(this, null);
        }
    }

    @Override
    public boolean isObstacle() {
        return false;
    }
}
