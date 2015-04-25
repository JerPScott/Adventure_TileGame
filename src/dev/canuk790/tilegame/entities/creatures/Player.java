package dev.canuk790.tilegame.entities.creatures;

import java.awt.image.BufferedImage;

import dev.canuk790.tilegame.audio.AudioAssets;
import dev.canuk790.tilegame.zones.Zone;

public class Player extends Creature{

	public Player(int x, int y, BufferedImage texture) {
		super(x, y, texture);
		soundEffect = AudioAssets.playerHit;
	}
	
	public void interact(Zone activeZone){
		
	}

}
