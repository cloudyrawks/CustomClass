package me.tbur.customclass.enchantments;

import me.tbur.customclass.CustomClass;
import me.tbur.customclass.armorevents.ArmorEquipEvent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ElvenLegs extends Enchantment implements Listener {

    public ElvenLegs(String namespace) {
        super(new NamespacedKey(CustomClass.getPlugin(), namespace));
    }

    @EventHandler
    public void onEquip (ArmorEquipEvent e) {
        Player player = e.getPlayer();
        ItemStack equip = e.getNewArmorPiece();
        ItemStack deEquip = e.getOldArmorPiece();
        Location location = player.getLocation();
        if(equip != null && equip.getType() != Material.AIR && equip.getEnchantments().containsKey(Enchantment.getByKey(CustomClass.elvenLegs.getKey()))) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Adding the elven boosters");
            location.getWorld().playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
        }else if(deEquip != null && deEquip.getType() != Material.AIR && deEquip.getEnchantments().containsKey(Enchantment.getByKey(CustomClass.elvenLegs.getKey()))) {
            player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD +  "Removing the elven boosters");
            player.removePotionEffect(PotionEffectType.SPEED);
            player.removePotionEffect(PotionEffectType.WEAKNESS);
        }
    }

    @Override
    public String getName() {
        return "elvenlegs";
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_TORSO;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    /**
     * @deprecated
     */
    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment enchantment) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack itemStack) {
        return true;
    }
}
