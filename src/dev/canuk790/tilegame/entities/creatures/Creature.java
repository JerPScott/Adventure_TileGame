package dev.canuk790.tilegame.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import dev.canuk790.tilegame.audio.AudioPlayer;
import dev.canuk790.tilegame.entities.Entity;
import dev.canuk790.tilegame.zones.Zone;

public abstract class Creature extends Entity{
	
	protected int health, speed;
	protected File soundEffect;
	protected boolean takingDamage;
	protected float deltaX, deltaY; // for movement

	public Creature(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		deltaX = 0;
		deltaY = 0;
		health = 10;
		speed = 4; // speed must be a factor of 32
		takingDamage = false;
		soundEffect = null;
	}
	
	@Override
	public void tick() {
		// update the texture depending on movement once key listener has implemented
		// Note: Very happy with how this movement looks and feels
		if (!(deltaX==0) || !(deltaY==0)){
			x += Math.signum(deltaX)*speed;
			y += Math.signum(deltaY)*speed;
			
			deltaX -= Math.signum(deltaX)*speed;
			deltaY -= Math.signum(deltaY)*speed;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, x, y, null);
		if (takingDamage){
			g.setColor(Color.RED);
			g.fillRect(x, y, 28, 28);
			takingDamage = false;
		}
		// x and y values from entity class (creature extends entity)
	}
	
	// methods for setting pending movement of the creature
	// also checks if there is anything in the way and ensures that the creature is in a tile before enabling movement
	public void moveLeft(Zone activeZone){
		if (x%32==0 && y%32==0 && x>0*32 && !(activeZone.isBlocked[x/32 - 1][y/32])) // if creature is in a tile, move creature one tile 
			deltaX = -32;
	}
	
	public void moveRight(Zone activeZone){
		if (x%32==0 && y%32==0 && x<19*32 && !(activeZone.isBlocked[x/32 + 1][y/32])) // if creature is in a tile, move creature one tile 
			deltaX = 32;
	}

	public void moveUp(Zone activeZone){
		if (x%32==0 && y%32==0 && y>0*32 && !(activeZone.isBlocked[x/32][y/32 - 1])) // if creature is in a tile, move creature one tile 
			deltaY = -32;
	}

	public void moveDown(Zone activeZone){
		if (x%32==0 && y%32==0 && y<14*32 && !(activeZone.isBlocked[x/32][y/32 + 1])) // if creature is in a tile, move creature one tile 
			deltaY = 32;
	}
	
	public void attack(Creature target, int damage){
		target.takeDamage(damage);
	}
	
	public void takeDamage(int damage){
		//decrease the creatures health
		this.health = this.health - damage;
		this.takingDamage = true;
		//could add in sound effect
		AudioPlayer.playSound(soundEffect);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int H){
		health = H;
	}
	
	public void setX(int playerX){
		x = playerX;
	}
	
	public void setY(int playerY){
		y = playerY;
	}

}
