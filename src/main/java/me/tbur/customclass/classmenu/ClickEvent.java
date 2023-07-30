package me.tbur.customclass.classmenu;

import me.tbur.customclass.CustomClass;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class ClickEvent implements Listener {

    CustomClass plugin;

    public ClickEvent(CustomClass plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        final String CLASS_MENU = ChatColor.AQUA + "Classes";

        if (!(e.getView().getTitle().equalsIgnoreCase(CLASS_MENU))){
            e.setCancelled(false);
        }
        else if (e.getView().getTitle().equalsIgnoreCase(CLASS_MENU)) {
            Location location = player.getLocation();

            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;

            else switch (e.getCurrentItem().getType()) {
                case DRAGON_EGG:
                    Objects.requireNonNull(location.getWorld()).playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                    ItemStack dragonItem = new ItemStack(Material.LEATHER_CHESTPLATE);
                    dragonItem.addUnsafeEnchantment(CustomClass.dragonScale, 1);
                    ItemMeta dragonMeta = dragonItem.getItemMeta();
                    assert dragonMeta != null;
                    dragonMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "DRACONIC CHESTPLATE");
                    ArrayList<String> dragonLore = new ArrayList<>();
                    dragonLore.add(ChatColor.GRAY + "Equip this item to gain some of the powers of the Dragons.");
                    dragonLore.add(ChatColor.GRAY + "Dragon Scale I");
                    dragonMeta.setLore(dragonLore);
                    dragonItem.setItemMeta(dragonMeta);
                    player.getInventory().addItem(dragonItem);

                    player.closeInventory();
                    e.setCancelled(false);
                    break;
                case PIGLIN_HEAD:
                    Objects.requireNonNull(location.getWorld()).playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                    ItemStack piglinItem = new ItemStack(Material.LEATHER_CHESTPLATE);
                    piglinItem.addUnsafeEnchantment(CustomClass.piglinArms, 1);
                    ItemMeta piglinMeta = piglinItem.getItemMeta();
                    assert piglinMeta != null;
                    piglinMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "PIGLIN CHESTPIECE");
                    ArrayList<String> piglinLore = new ArrayList<>();
                    piglinLore.add(ChatColor.GRAY + "Equip this item to gain some of the powers of the Piglins.");
                    piglinLore.add(ChatColor.GRAY + "Piglin Arms I");
                    piglinMeta.setLore(piglinLore);
                    piglinItem.setItemMeta(piglinMeta);
                    player.getInventory().addItem(piglinItem);

                    player.closeInventory();
                    e.setCancelled(false);
                    break;
                case IRON_SWORD:
                    Objects.requireNonNull(location.getWorld()).playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 0);
                    ItemStack elfItem = new ItemStack(Material.LEATHER_CHESTPLATE);
                    elfItem.addUnsafeEnchantment(CustomClass.elvenLegs, 1);
                    ItemMeta elfMeta = elfItem.getItemMeta();
                    elfMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "ELF CHESTPLATE" );
                    ArrayList <String> elfLore = new ArrayList<>();
                    elfLore.add(ChatColor.GRAY + "Equip this item to gain some powers from the Elves.");
                    elfLore.add(ChatColor.GRAY + "Elf Booster I");
                    elfMeta.setLore(elfLore);
                    elfItem.setItemMeta(elfMeta);
                    player.getInventory().addItem(elfItem);

                    player.closeInventory();
                    e.setCancelled(false);
                    break;
            }

        }
    }
}
