package View;

import Model.Directable;
import Objects.GameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Map extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<GameObject> objects = null;
    private boolean inventoryState;
    private int posIc[]= {1,2};
    
    private final int numInvY = 2;
    private final int numInvX = 5;
    private final int invWidth = 1000;
    private final int invHeight = 255;
    //taille de l'icone
    private final int side = 60;
    
    public Map() {
        this.setFocusable(true);
        this.requestFocusInWindow();
        posIc[0] = numInvX/2 + 1;
        posIc[1] = numInvY/2 + 1;
    }

    public void paint(Graphics g) {
    	
    	 g.setColor(Color.WHITE);
    	 g.fillRect(0, 0, getWidth(), getHeight() );
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
        	//on charge l'image du fond de l'intentaire
            Image image = getToolkit().getImage("inventory.jpg");
        	g.drawImage(image, 0, 3*invHeight, invWidth, invHeight, this);
        	image = getToolkit().getImage("icone.jpg");
        	//on dessine le nombre d'emplacements d'inventaire suivant x et y
        	for(int i=0; i<(numInvX*numInvY); i++){
        		int xic = i%numInvX + 1;
        		int yic = i/numInvX + 1;
        		g.drawImage(image, (xic)*invWidth/(numInvX+1)-side/2, 3*invHeight + (yic)*invHeight/(numInvY+1) - side/2, side, side, this);
        	}
        	int xic = posIc[0];
        	int yic = posIc[1];
        	//on dessine l'icone sélectionnée
        	image = getToolkit().getImage("icone_select.jpg");
        	g.drawImage(image, (xic)*invWidth/(numInvX+1)-side/2, 3*invHeight + (yic)*invHeight/(numInvY+1) - side/2, side, side, this);
        }
        
    }
    //on déplace l'icone sélectionnée
    public void moveIc(int direction){
    	switch(direction){
    	//Right
    	case 0:
    		if(posIc[0] < numInvX){
    			posIc[0] += 1;
    		}
    		break;
    	//Up
    	case 1:
    		if(posIc[1] > 1){
    			posIc[1] -= 1;
    		}
    		break;
    	//Left
    	case 2:
    		if(posIc[0] > 1){
    			posIc[0] -= 1;
    		}
    		break;
    	//Down
    	case 3:
    		if(posIc[1] < numInvY){
    			posIc[1] += 1;
    		}
    		break;
    	}
    }
    
    public void setObjects(ArrayList<GameObject> objects) {
        this.objects = objects;
    }

    //On change l'affichage de l'inventaire
    public boolean switchInventoryState(){
    	if(inventoryState == true){
    		inventoryState = false;
    	}
    	else{
    		inventoryState = true;
    	}
    	return inventoryState;
    }
    
    public boolean getInventoryState(){
    	return inventoryState;
    }
    
    public void redraw() {
        this.repaint();
    }
    
}
