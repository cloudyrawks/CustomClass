package me.tbur.customclass.classmenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ClassMenu {

    public static void openClassMenu(Player player){

        Inventory classes = Bukkit.createInventory(player, 9, ChatColor.AQUA + "Classes");

        //dragon class
        ItemStack dragonClass = new ItemStack(Material.DRAGON_EGG);
        ItemMeta dragonMeta = dragonClass.getItemMeta();
        assert dragonMeta != null;
        dragonMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "DRAGON CLASS");
        ArrayList<String> dragonLore = new ArrayList<>();
        dragonLore.add(ChatColor.GRAY + "Click this to receive a draconian blessed item");
        dragonMeta.setLore(dragonLore);
        dragonClass.setItemMeta(dragonMeta);

        //orc class
        ItemStack piglinClass = new ItemStack(Material.PIGLIN_HEAD);
        ItemMeta piglinMeta = piglinClass.getItemMeta();
        assert  piglinMeta != null;
        piglinMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "PIGLIN CLASS");
        ArrayList<String> piglinLore = new ArrayList<>();
        piglinLore.add(ChatColor.GRAY + "Click this to receive an item blessed by the piglins");
        piglinMeta.setLore(piglinLore);
        piglinClass.setItemMeta(piglinMeta);

        //elf class
        ItemStack elfClass = new ItemStack(Material.IRON_SWORD);
        ItemMeta elfMeta = elfClass.getItemMeta();
        assert elfMeta != null;
        elfMeta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ELF CLASS");
        ArrayList<String> elfLore = new ArrayList<>();
        elfLore.add(ChatColor.GRAY + "Click this to receive a blessing item from the elves.");
        elfMeta.setLore(elfLore);
        elfClass.setItemMeta(elfMeta);

        classes.setItem(2, dragonClass);
        classes.setItem(4, piglinClass);
        classes.setItem(6, elfClass);
        player.openInventory(classes);

    }

}
