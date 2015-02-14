package dev.canuk790.tilegame.zones;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import dev.canuk790.tilegame.gfx.Assets;
import dev.canuk790.tilegame.tiles.Tile;
import dev.canuk790.tilegame.tiles.Tiles;

public class Zone {
	/* 
	 * Zones will hold the information for drawing each screen or zone that the player can explore.
	 * They will also hole information for collision and items/enemies/etc
	 * 
	 * Think about making different types of zones have different game mechanics. 
	 * (mountains caves dessert swap plains forest)
	 * 
	*/
	
	// try an implementation with Tiles that combines the blocked variables and the 
	public Tile[][] tiles = new Tile[20][15];
	private Tile defaultTile1, defaultTile2, blockedTile1, blockedTile2;
	
	private int difficulty, x, y;
	
	public Zone(int[][] tileCodes, int type, int d){
		
		difficulty = d;
		
		// set the default tile
		//
		// 1 == beach
		// 2 == forest
		// 3 == grassland
		// 4 == mountain
		// 5 == town
		// 6 == cave
		//
		switch (type){
		case 1: //beach
			defaultTile1 = Tiles.dirtLight1Tile;
			defaultTile2 = Tiles.dirtLight2Tile;
			blockedTile1 = Tiles.water1Tile;
			blockedTile2 = Tiles.water2Tile;
			break;
		case 2: //forest
			defaultTile1 = Tiles.grassDark1Tile;
			defaultTile2 = Tiles.grassDark2Tile;
			blockedTile1 = Tiles.treeDarkLeftTile;
			blockedTile2 = Tiles.treeDarkRightTile;
			break;
		case 3: //grassland
			defaultTile1 = Tiles.grassLight1Tile;
			defaultTile2 = Tiles.grassLight2Tile;
			blockedTile1 = Tiles.treeLightLeftTile;
			blockedTile2 = Tiles.treeLightRightTile;
			break;
		case 4: //mountain
			defaultTile1 = Tiles.rockLight1Tile;
			defaultTile2 = Tiles.rockLight2Tile;
			blockedTile1 = Tiles.treeDarkLeftTile;
			blockedTile2 = Tiles.treeDarkRightTile;
			break;
		case 5: //town
			defaultTile1 = Tiles.dirtLight1Tile;
			defaultTile2 = Tiles.dirtLight2Tile;
			blockedTile1 = Tiles.wall1Tile;
			blockedTile2 = Tiles.wall2Tile;
			break;
		case 6: //cave
			defaultTile1 = Tiles.rockDark1Tile;
			defaultTile2 = Tiles.rockDark2Tile;
			blockedTile1 = Tiles.water1Tile;
			blockedTile2 = Tiles.water2Tile;
			break;
		}
		
		for (int i=0; i<20; i++){
			for(int k=0; k<15; k++){
				
				// build the array of images to be drawn upon entering the zone
				//
				// 1 == light grass
				// 2 == dark grass
				// 3 == light rock
				// 4 == dark rock
				// 5 == light dirt/sand
				//
				switch (tileCodes[i][k]){
					case 0:
						if (Math.random() < 0.8){
							tiles[i][k] = defaultTile1;
						}else{
							tiles[i][k] = defaultTile2;
						}
						break;
					case 1:
						if (Math.random() < 0.8){
							tiles[i][k] = Tiles.grassLight1Tile;
						}else{
							tiles[i][k] = Tiles.grassLight2Tile;
						}
						break;
					case 2:
						if (Math.random() < 0.8){
							tiles[i][k] = Tiles.grassDark1Tile;
						}else{
							tiles[i][k] = Tiles.grassDark2Tile;
						}
						break;
					case 3:
						if (Math.random() < 0.8){
							tiles[i][k] = Tiles.rockLight1Tile;
						}else{
							tiles[i][k] = Tiles.rockLight2Tile;
						}
						break;
					case 4:
						if (Math.random() < 0.8){
							tiles[i][k] = Tiles.rockDark1Tile;
						}else{
							tiles[i][k] = Tiles.rockDark2Tile;
						}
						break;
					case 5:
						if (Math.random() < 0.8){
							tiles[i][k] = Tiles.dirtLight1Tile;
						}else{
							tiles[i][k] = Tiles.dirtLight2Tile;
						}
						break;
				}
				
				//block all outer tiles
				if ((i==0)||(i==19)||(k==0)||(k==14)){
					if (Math.random() < 0.8){
						tiles[i][k] = blockedTile1;
					}else{
						tiles[i][k] = blockedTile2;
					}
				}				
			}
		}
		
		tiles[0][7]= defaultTile1;
		tiles[19][7]= defaultTile1;
		tiles[10][0]= defaultTile1;
		tiles[10][14]= defaultTile1;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		x = 0;
		for (Tile[] v : tiles){
			y = 0;
			for (Tile I : v){
				g.drawImage(I.texture, x*32, y*32, null);
				y++;
			}
			x++;
		}
	}
	
}
