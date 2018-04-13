package View;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Objects.GameObject;


public class Window {
    private Map map = new Map();

    public Window() {
        JFrame window = new JFrame("Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 1000, 1020);
        window.getContentPane().setBackground(Color.gray);
        window.getContentPane().add(this.map);
        window.setVisible(true);
    }

    public void setGameObjects(ArrayList<GameObject> objects) {
        this.map.setObjects(objects);
        this.map.redraw();
    }

    public void update() {
        this.map.redraw();
    }
    
    public void moveIc(int direction){
    	map.moveIc(direction);
    	this.map.redraw();
    }
    
    public boolean switchInventory(){
    	boolean inventoryState = map.switchInventoryState();
    	this.map.redraw();
    	return inventoryState;
    }
    
    public void setKeyListener(KeyListener keyboard) {
    	this.map.addKeyListener(keyboard);
    }
}
