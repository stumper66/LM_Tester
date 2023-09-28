package me.lokka30.LM_Tester;

import me.lokka30.levelledmobs.LevelledMobs;
import me.lokka30.levelledmobs.customdrops.CustomDropInstance;
import me.lokka30.levelledmobs.customdrops.CustomDropItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {
    public Commands(final @NotNull Main main){
        this.main = main;
    }

    private final Main main;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        main.getLogger().info("LM_Tester, running test");

        //testCustomDropGroups();

        return true;
    }

    private void testCustomDropGroups(){
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Cool Netherite Sword");
        meta.setLore(List.of("Created via API"));
        itemStack.setItemMeta(meta);

        LevelledMobs lm = LevelledMobs.getInstance();
        final CustomDropItem customDropItem = new CustomDropItem(lm); // must pass instance to LevelledMobs main class
        customDropItem.setItemStack(itemStack);
        customDropItem.chance = 1.0F;
        customDropItem.equippedSpawnChance = 1.0F;

        final CustomDropInstance customDropInstance = new CustomDropInstance(EntityType.ZOMBIE);
        customDropInstance.customItems.add(customDropItem);

        lm.customDropsHandler.externalCustomDrops.addCustomDrop(customDropInstance);

        main.getLogger().info("Added a new drop for zombie");
    }

    private void testCustomDrops(){
        ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
        ItemMeta meta = itemStack.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Cool Netherite Sword");
        meta.setLore(List.of("Created via API"));
        itemStack.setItemMeta(meta);

        LevelledMobs lm = LevelledMobs.getInstance();
        final CustomDropItem customDropItem = new CustomDropItem(lm); // must pass instance to LevelledMobs main class
        customDropItem.setItemStack(itemStack);
        customDropItem.chance = 1.0F;
        customDropItem.equippedSpawnChance = 1.0F;

        final CustomDropInstance customDropInstance = new CustomDropInstance(EntityType.ZOMBIE);
        customDropInstance.customItems.add(customDropItem);

        lm.customDropsHandler.externalCustomDrops.addCustomDrop(customDropInstance);

        main.getLogger().info("Added a new drop for zombie");
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return Collections.emptyList();
    }
}
