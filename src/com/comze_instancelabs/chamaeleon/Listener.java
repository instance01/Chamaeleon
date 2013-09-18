package com.comze_instancelabs.chamaeleon;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;

public class Listener implements org.bukkit.event.Listener{
	
	private Player p;
	
	public Listener(Plugin plugin, Player pl){
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		p = pl;
		setupDisguiseCraft();
	}
	
	DisguiseCraftAPI dcAPI;
    public void setupDisguiseCraft() {
    	dcAPI = DisguiseCraft.getAPI();
    }
	
    int before_id = 0;
    
    @EventHandler
    public void onmove(PlayerMoveEvent event){
	        int x = event.getFrom().getBlockX();
	        int fromy = event.getFrom().getBlockY();
	        int y = fromy - 1;
	        int z = event.getFrom().getBlockZ();
	        Location loc = new Location(event.getFrom().getWorld(), x, y, z);
	        
	        //if (!loc.getBlock().isEmpty() && !loc.getBlock().isLiquid()){
	        	//before_id = loc.getBlock().getTypeId();
	        int id = 0;
	        if(event.getPlayer().getName().equalsIgnoreCase(p.getName())){
	        	id = loc.getBlock().getTypeId();
		        if(before_id != id && !loc.getBlock().isEmpty() && !loc.getBlock().isLiquid()){
		        	String st = "blockID:" + id;
			        //event.getPlayer().sendMessage(st);
			        Disguise d;
			        DisguiseType f = DisguiseType.FallingBlock;
					d = new Disguise(dcAPI.newEntityID(), st, f);
					d.addSingleData("blocklock");
					//dcAPI.disguisePlayer(event.getPlayer(), d);
					if(dcAPI.isDisguised(event.getPlayer())){
						dcAPI.changePlayerDisguise(event.getPlayer(), d);
					}else{
						dcAPI.disguisePlayer(event.getPlayer(), d);
					}
		        }
		        
		        before_id = id;
	        }
	        
	        
	        //}

			
    }
}
