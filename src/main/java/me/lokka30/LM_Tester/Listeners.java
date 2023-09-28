package me.lokka30.LM_Tester;

import me.lokka30.levelledmobs.LevelInterface;
import me.lokka30.levelledmobs.LevelledMobs;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Listeners implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onTest(final EntityTargetEvent event){

        if (!(event.getEntity() instanceof LivingEntity)) return;
        // Access LevelledMobs' main class, grab the LevelInterface class. LI is used to interact with LevelledMobs.
        final LevelInterface levelInterface = LevelledMobs.getInstance().levelInterface;

        LivingEntity livingEntity = (LivingEntity) event.getEntity();

        // Make sure the mob is levelled.
        if(!(levelInterface.isLevelled(livingEntity))) return;

        // Get the level of the mob.
        int level = levelInterface.getLevelOfMob(livingEntity);

        Bukkit.getLogger().info("LM tester: " + livingEntity.getType().name() + ", level: " + level);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    private void onEntityDeathEvent(@NotNull final EntityDeathEvent event){
        if (event.getEntity() instanceof Player) return;
        final Plugin lmPlugin = Bukkit.getPluginManager().getPlugin("LevelledMobs");
        if (lmPlugin == null){
            // LM is not installed
            return;
        }

        final NamespacedKey levelKey = new NamespacedKey(lmPlugin, "level");
        int level = 0;
        final LivingEntity le = event.getEntity();
        if (le.getPersistentDataContainer().has(levelKey, PersistentDataType.INTEGER))
            level = Objects.requireNonNull(le.getPersistentDataContainer().get(levelKey, PersistentDataType.INTEGER));

        if (level > 0)
            Bukkit.getLogger().info(le.getType().name() + " died, level: " + level);
        else
            Bukkit.getLogger().info(le.getType().name() + " died, was not levelled");
    }
}
