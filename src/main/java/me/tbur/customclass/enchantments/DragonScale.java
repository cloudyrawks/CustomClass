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

import java.util.Objects;

public class DragonScale extends Enchantment implements Listener {

    public DragonScale(String namespace) {
        super(new NamespacedKey(CustomClass.getPlugin(), namespace));
    }

    @EventHandler
    public void onEquip(ArmorEquipEvent e){

        Player player = e.getPlayer();
        ItemStack equip = e.getNewArmorPiece();
        ItemStack deEquip = e.getOldArmorPiece();
        Location location = player.getLocation();
        if(equip != null && equip.getType() != Material.AIR && equip.getEnchantments().containsKey(Enchantment.getByKey(CustomClass.dragonScale.getKey()))) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Putting on Dragon Scales");
            Objects.requireNonNull(location.getWorld()).playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100 , 0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 0));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0));
        }else if(deEquip != null && deEquip.getType() != Material.AIR && deEquip.getEnchantments().containsKey(Enchantment.getByKey(CustomClass.dragonScale.getKey()))){
            player.sendMessage(ChatColor.YELLOW + "" + ChatColor.BOLD + "Dragon Scale's removed");
            player.removePotionEffect(PotionEffectType.ABSORPTION);
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
            player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
        }

    }

    @Override
    public String getName() {
        return "dragonscale";
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
