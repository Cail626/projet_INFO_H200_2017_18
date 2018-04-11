package Objects;

public abstract class Consumable extends InventoryObject{

    public Consumable(int X, int Y, int color, int id, String description) {
        super(X, Y, color, id, description);
    }

    public void consume(){}
}
