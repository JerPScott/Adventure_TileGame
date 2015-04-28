package dev.canuk790.tilegame.entities.creatures.enemies;

import java.awt.image.BufferedImage;

import dev.canuk790.tilegame.entities.creatures.Player;

public class Snake extends Enemy{

	public Snake(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		damage = 1;
	}
	
	public void scout(Player player){
		
		attackTimer++;
		
		//this is where the different enamies get their unique AI
		
		xDiff = (x - player.getX())/32;
		yDiff = (y - player.getY())/32;
		
		if ((Math.abs(xDiff) <= 1 && Math.abs(yDiff) <= 1)){
			//if snake is in range of player and has waited long enough since last attack, attack
			if (attackTimer > 60){
				attackTimer = 0;
				attack(player, damage);
			}
		}else if ((Math.abs(xDiff) < 3) && (Math.abs(yDiff) < 3)){
			// otherwise check if the snake will move towards the player
			if (Math.abs(xDiff) >= Math.abs(yDiff)){
				deltaX = -Math.signum(xDiff)*32;
			}else{
				deltaY = -Math.signum(yDiff)*32;
			}
		}
	}

}
