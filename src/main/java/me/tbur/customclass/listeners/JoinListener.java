package me.tbur.customclass.listeners;

import me.tbur.customclass.CustomClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (player.getInventory().getChestplate() == null) {
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.WEAKNESS);
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.ABSORPTION);
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
            player.removePotionEffect(PotionEffectType.SLOW);
            player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        }
        else {
            if (player.getInventory().getChestplate().getEnchantments().containsKey(CustomClass.elvenLegs)) return;
            if (player.getInventory().getChestplate().getEnchantments().containsKey(CustomClass.dragonScale)) return;
            if (player.getInventory().getChestplate().getEnchantments().containsKey(CustomClass.piglinArms)) return;
        }
    }

}
