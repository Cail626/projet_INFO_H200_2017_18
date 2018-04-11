package Model;

public class BlockUnbreakable extends Block {

    public BlockUnbreakable(int X, int Y) {
        super(X, Y, 0, 11);
    }

    @Override
    public boolean isObstacle() {
        return true;
    }

    @Override
    public void activate() {

    }
}
