package com.comze_instancelabs.chamaeleon;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;
import pgDev.bukkit.DisguiseCraft.api.DisguiseCraftAPI;
import pgDev.bukkit.DisguiseCraft.disguise.Disguise;
import pgDev.bukkit.DisguiseCraft.disguise.DisguiseType;
 
public final class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		getLogger().info("onEnable has been invoked!");
		//getServer().getPluginManager().registerEvents(this, this);
		//getServer().getPluginManager().registerEvents(new com.comze_instancelabs.chamaeleon.Listener(this), this);
		setupDisguiseCraft();
    }
 
    @Override
    public void onDisable() {
    	getLogger().info("onDisable has been invoked!");
    }
    
    
    DisguiseCraftAPI dcAPI;
    public void setupDisguiseCraft() {
    	dcAPI = DisguiseCraft.getAPI();
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
    	if(cmd.getName().equalsIgnoreCase("cham")){
    		if(args.length < 1){
    			sender.sendMessage("You are a chamaeleon now.");
    			getServer().getPluginManager().registerEvents(new com.comze_instancelabs.chamaeleon.Listener(this, (Player) sender), this);
    		}else{
    			/*dcAPI.getDisguise((Player)sender);
    			Disguise d = new Disguise(dcAPI.newEntityID(), "blockID:35", DisguiseType.FallingBlock);
    			d.addSingleData("blocklock");
    			dcAPI.disguisePlayer((Player)sender, d);*/
    		}
    		return true;
    	}else if(cmd.getName().equalsIgnoreCase("dplayer")){
    		if(args.length < 2){
    			sender.sendMessage("Too few arguments.");
    		}else{
    			//disguise someone else
    			Disguise d;
		        DisguiseType f = DisguiseType.fromString(args[1]);
				d = new Disguise(dcAPI.newEntityID(), f);
    			Player target = (Bukkit.getServer().getPlayer(args[0]));
    			dcAPI.disguisePlayer(target, d);
    		}
    		return true;
    	}else if(cmd.getName().equalsIgnoreCase("undplayer")){
    		if(args.length < 1){
    			sender.sendMessage("Too few arguments.");
    		}else{
    			//disguise someone else
    			Player target = (Bukkit.getServer().getPlayer(args[0]));
    			dcAPI.undisguisePlayer(target);
    		}
    		return true;
    	}else if(cmd.getName().equalsIgnoreCase("unchamall")){
    		HandlerList.unregisterAll(this);
    		dcAPI.undisguisePlayer((Player) sender);
    		return true;
    	}
    	return false;
    }
}