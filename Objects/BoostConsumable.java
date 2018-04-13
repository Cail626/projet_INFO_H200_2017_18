package Objects;

import Moving.Player;

public class BoostConsumable extends Consumable{

    private String boostType;
    private int boostLength;

    ////////////////////////////////////////////////////////////////////////////////////////

    public BoostConsumable(int X, int Y, int color, int id, String description, String boostType, int boostLength, Player p) {
        super(X, Y, color, id, description, p);
        this.boostType = boostType;
        this.boostLength = boostLength;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void consume(){
        if(boostType == "force"){
            p.setForce(p.getForce() + 1);
        }else if(boostType == "life"){
            p.setLife(1);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public void setBoostType(String boostType){
        this.boostType = boostType;
    }

    public void setBoostLength(int boostLength){
        this.boostLength = boostLength;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public String getBoostType(){
        return boostType;
    }

    public int getBoostLength() {
        return boostLength;
    }
}
