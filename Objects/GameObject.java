package Objects;

import Model.Activable;

public abstract class GameObject implements Activable {
    protected int posX;
    protected int posY;
    protected int color;
    protected int id;

    public GameObject(int X, int Y, int color, int id) {
        this.posX = X;
        this.posY = Y;
        this.color = color;
        this.id = id;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public int getColor() {
        return this.color;
    }

    public int getId(){
        return this.id;
    }

    public boolean isAtPosition(int x, int y) {
        return this.posX == x && this.posY == y;
    }

    public abstract boolean isObstacle();

    public abstract void activate();
}
