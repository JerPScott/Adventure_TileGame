package dev.canuk790.tilegame.zones;

import dev.canuk790.tilegame.entities.creatures.Player;

public class ZoneHandler {
	
	private Zone[][] zones = new Zone[3][3]; //must be square (well doesn't have to be but then other code needs to be changed... keep it square)
	private Zone activeZone;
	
	private int zoneTypes[][] = new int[3][3];
	private int zoneDifficulties[][] = new int[3][3];
	private int zoneIndexX, zoneIndexY, playerX, playerY;
	
	int[][] tileCodes = new int[20][15];
	
	public ZoneHandler(){
		
		// assign zone types
		zoneTypes[0][0] = 1;
		zoneTypes[0][1] = 1;
		zoneTypes[0][2] = 1;
		zoneTypes[1][0] = 1;
		zoneTypes[1][1] = 3;
		zoneTypes[1][2] = 1;
		zoneTypes[2][0] = 3;
		zoneTypes[2][1] = 3;
		zoneTypes[2][2] = 5;
		
		// assign zone difficulties
		for (int[] v: zoneDifficulties){
			for (int j = 0; j < v.length; j++) {
				v[j] = 1;
			}
		}
		
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				zones[i][j] = new Zone(tileCodes, zoneTypes[i][j], zoneDifficulties[i][j]);
			}
		}
		
		zoneIndexX = 0;
		zoneIndexY = 2;
		activeZone = zones[zoneIndexX][zoneIndexY];
	}
	
	public void switchZone(Player player){
		
		playerX = player.getX();
		playerY = player.getY();
		
		// activeZone = new activeZone based on where player is
		assert playerX != playerY;
		if ((playerX == 0) && (zoneIndexX > 0)){
			zoneIndexX--;
			player.setX(18*32);
		}else if ((playerX == 19*32) && (zoneIndexX < 2)){
			zoneIndexX++;
			player.setX(1*32);
		}else if ((playerY == 0) && (zoneIndexY > 0)){
			zoneIndexY--;
			player.setY(13*32);
		}else if ((playerY == 14*32) && (zoneIndexY < 2)){
			zoneIndexY++;
			player.setY(1*32);
		}
		
		activeZone = zones[zoneIndexX][zoneIndexY];
	
	}
	
	public Zone getActiveZone(){
		return activeZone;
	}
	
}
