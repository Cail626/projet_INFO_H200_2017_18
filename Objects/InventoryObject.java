package Objects;

import Model.Deletable;

public abstract class InventoryObject extends GameObject implements Deletable {

    private String description;

    public InventoryObject(int X, int Y, int color, int id, String description) {
        super(X, Y, color, id);
        this.description = description;
    }

    public void throwAway(){

    }

    @Override
    public void activate(){ // action de ramasser l'objet
        notifyDeletableObserver();
    }

    public String getDescription(){
        return description;
    }
}
