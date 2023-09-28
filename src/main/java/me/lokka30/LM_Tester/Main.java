package me.lokka30.LM_Tester;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        //Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        final PluginCommand cmd = getCommand("lm_tester");

        if (cmd == null){
            getLogger().warning("Unable to register command lm_tester");
        }
        else {
            cmd.setExecutor(new Commands(this));
        }

        Bukkit.getLogger().info("LM_Tester has started");
    }

    @Override
    public void onDisable() {

    }
}
