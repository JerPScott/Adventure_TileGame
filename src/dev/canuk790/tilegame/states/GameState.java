package dev.canuk790.tilegame.states;

import java.awt.Graphics;

import dev.canuk790.tilegame.Game;
import dev.canuk790.tilegame.entities.creatures.Player;
import dev.canuk790.tilegame.gfx.Assets;
import dev.canuk790.tilegame.gfx.MyFont;
import dev.canuk790.tilegame.zones.ZoneHandler;

public class GameState extends State{

	private Player player;
	private ZoneHandler zoneHandler;
	
	public GameState(){
		player = new Player(10*32, 7*32, Assets.playerFront);
		zoneHandler = new ZoneHandler();
	}
	
	@Override
	public void tick() {
		zoneHandler.getActiveZone().tick(player);
		player.tick();
		if (player.getHealth() <= 0){
			Game.manageState(3);
		}
	}

	@Override
	public void render(Graphics g) {
		zoneHandler.getActiveZone().render(g);
		player.render(g);
		
		// render stats
		MyFont.render("Health:" + Integer.toString(player.getHealth()), g, 0, 0);
	}

	@Override
	public void inputSpace() {
		if ((player.getX() == 0*32 || player.getX() == 19*32 || player.getY() == 0*32 || player.getY() == 14*32)){
			zoneHandler.switchZone(player);
		}
		player.interact(zoneHandler.getActiveZone());
	}

	@Override
	public void inputLeft() {
		player.moveLeft(zoneHandler.getActiveZone());
	}

	@Override
	public void inputRight() {
		player.moveRight(zoneHandler.getActiveZone());
	}

	@Override
	public void inputUp() {
		player.moveUp(zoneHandler.getActiveZone());
	}

	@Override
	public void inputDown() {
		player.moveDown(zoneHandler.getActiveZone());
	}

	@Override
	public void onOpen() {
	}

	@Override
	public void onClose() {
	}

}
