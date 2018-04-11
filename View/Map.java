package View;

import Model.Directable;
import Model.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout; 
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Map extends JPanel {
    private ArrayList<GameObject> objects = null;
    private boolean inventoryState;
    //Nom du fichier contenant l'image
    private final String impath = "inventory.jpg";
    //L'image
    private Image inventory;
    
    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        //On charge l'image dès le lancement du constructeur
        inventory = getToolkit().getImage(impath);
    }

    public void paint(Graphics g) {
        for (int i = 0; i < 20; i++) { // Virer la valeur 20 et parametrer ca
            for (int j = 0; j < 20; j++) {
                int x = i;
                int y = j;
                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(x * 50, y * 50, 48, 48); //dessine l'interieur( commnence en haut à droite )
                g.setColor(Color.BLACK);
                g.drawRect(x * 50, y * 50, 48, 48); //dessine le contour( commnence en haut à droite )
            }
        }

        for (GameObject object : this.objects) {
            int x = object.getPosX();
            int y = object.getPosY();
            int color = object.getColor();

            if (color == 0) {
                g.setColor(Color.DARK_GRAY);
            } else if (color == 1) {
                g.setColor(Color.GRAY);
            } else if (color == 2) {
                g.setColor(Color.BLUE);
            } else if (color == 3) {
                g.setColor(Color.GREEN);
            } else if (color == 4) {
                g.setColor(Color.RED);
            } else if (color == 5) {
                g.setColor(Color.ORANGE);
            }

            g.fillRect(x * 50, y * 50, 48, 48);
            g.setColor(Color.BLACK);
            g.drawRect(x * 50, y * 50, 48, 48);
            
            // Decouper en fontions
            if(object instanceof Directable) {
                int direction = ((Directable) object).getDirection();
                
                int deltaX = 0;
                int deltaY = 0;
                
                switch (direction) {
                case Directable.EAST:
                    deltaX = +24;
                    break;
                case Directable.NORTH:
                    deltaY = -24;
                    break;
                case Directable.WEST:
                    deltaX = -24;
                    break;
                case Directable.SOUTH:
                    deltaY = 24;
                    break;
                }

                int xCenter = x * 50 + 24;
                int yCenter = y * 50 + 24;
                g.drawLine(xCenter, yCenter, xCenter + deltaX, yCenter + deltaY);
            }
        }
        if( getInventoryState() == true){
        	int width = getWidth();
        	int height = getHeight();
        	g.drawImage(inventory, 0, 3*height/4, width, height/4, this);
        }
        
    }

    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    public void switchInventoryState(){
    	if(inventoryState == true){
    		inventoryState = false;
    	}
    	else{
    		inventoryState = true;
    	}
    }
    
    public boolean getInventoryState(){
    	return inventoryState;
    }
    
    //On change l'affichage de l'inventaire
    public void showInventory(){
    	switchInventoryState();
    	redraw();
    }
    
    public void redraw() {
        this.repaint();
    }
    
}
