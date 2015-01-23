package dev.canuk790.tilegame.states;

import java.awt.Graphics;

import dev.canuk790.tilegame.entities.creatures.Player;
import dev.canuk790.tilegame.gfx.Assets;

public class GameState extends State{

	private Player player;
	
	public GameState(){
		player = new Player(0, 32, Assets.playerFront);
	}
	
	@Override
	public void tick() {
		player.tick();
	}

	@Override
	public void render(Graphics g) {
		player.render(g);
	}

}