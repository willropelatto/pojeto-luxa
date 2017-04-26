package com.luxes;

import com.ctrl.game.PlayerController;

public class App 
{
    public static void main( String[] args )
    {      
        
		PlayerController pc = new PlayerController();
		pc.UpdateStoredPlayers();
		System.out.println("terminei");
		
    }
}
