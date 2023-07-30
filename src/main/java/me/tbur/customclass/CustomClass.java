package me.tbur.customclass;

import me.tbur.customclass.armorevents.ArmorEquipEvent;
import me.tbur.customclass.armorevents.ArmorListener;
import me.tbur.customclass.armorevents.DispenserArmorListener;
import me.tbur.customclass.classmenu.ClickEvent;
import me.tbur.customclass.commands.classCommand;
import me.tbur.customclass.enchantments.DragonScale;
import me.tbur.customclass.enchantments.ElvenLegs;
import me.tbur.customclass.enchantments.PiglinArms;
import me.tbur.customclass.listeners.JoinListener;
import me.tbur.customclass.listeners.QuitListener;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public final class CustomClass extends JavaPlugin implements Listener {


    public static CustomClass getPlugin(){
        return plugin;
    }
    private static CustomClass plugin;
    public static ArrayList<Enchantment> customEnchants = new ArrayList<>();
    public static PiglinArms piglinArms;
    public static ElvenLegs elvenLegs;
    public static DragonScale dragonScale;

    @Override
    public void onEnable() {
        plugin = this;
        //registering the enchantment name, adding to the array and registering the enchantment itself.
        piglinArms = new PiglinArms("piglinarms");
        elvenLegs = new ElvenLegs("elvenlegs");
        dragonScale = new DragonScale("dragonscale");

        customEnchants.add(piglinArms);
        customEnchants.add(elvenLegs);
        customEnchants.add(dragonScale);

        registerEnchantment(elvenLegs);
        registerEnchantment(piglinArms);
        registerEnchantment(dragonScale);


        this.getServer().getPluginManager().registerEvents(piglinArms, this);
        this.getServer().getPluginManager().registerEvents(elvenLegs, this);
        this.getServer().getPluginManager().registerEvents(dragonScale, this);
        getServer().getPluginManager().registerEvents(new ClickEvent(this), this);
        Objects.requireNonNull(getCommand("class")).setExecutor(new classCommand());
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);


        saveDefaultConfig();


        getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);
        try{
            //Better way to check for this? Only in 1.13.1+?
            Class.forName("org.bukkit.event.block.BlockDispenseArmorEvent");
            getServer().getPluginManager().registerEvents(new DispenserArmorListener(), this);
        }catch(Exception ignored){}
        //example();
    }

    /**
     * Used to test locally and debug information about what is happening with the ArmorEquipEvent.
     */
    public void example(){
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void equip(ArmorEquipEvent event){
        System.out.println("ArmorEquipEvent - " + event.getMethod());
        System.out.println("Type: " + event.getType());
        System.out.println("New: " + (event.getNewArmorPiece() != null ? event.getNewArmorPiece().getType() : "null"));
        System.out.println("Old: " + (event.getOldArmorPiece() != null ? event.getOldArmorPiece().getType() : "null"));
        boolean test = true;
        if(test){
            // Does a test where if you start in survival without a helmet on you SHOULD end up in survival without the helmet on, or adventure mode if helmet is on.
            // Used to make sure spam clicking doesn't mess up, no clue if a better test exists.
            if(event.getOldArmorPiece() != null && event.getOldArmorPiece().getType().equals(Material.DIAMOND_HELMET)){
                event.getPlayer().setGameMode(event.getPlayer().getGameMode().equals(GameMode.ADVENTURE) ? GameMode.SURVIVAL : GameMode.ADVENTURE);
            }
            if(event.getNewArmorPiece() != null && event.getNewArmorPiece().getType().equals(Material.DIAMOND_HELMET)){
                event.getPlayer().setGameMode(event.getPlayer().getGameMode().equals(GameMode.ADVENTURE) ? GameMode.SURVIVAL : GameMode.ADVENTURE);
            }
            System.out.println("New Gamemode: " + event.getPlayer().getGameMode());
        }
    }

    public static void registerEnchantment(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            // It's been registered!
        }
    }

    @Override
    public void onDisable() {
        try {
            Field keyField = Enchantment.class.getDeclaredField("byKey");

            keyField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment>) keyField.get(null);

            for(Enchantment enchantment : customEnchants) {
                if (byKey.containsKey(enchantment.getKey())) {
                    byKey.remove(enchantment.getKey());
                }
            }
            Field nameField = Enchantment.class.getDeclaredField("byName");

            nameField.setAccessible(true);
            @SuppressWarnings("unchecked")
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) nameField.get(null);

            for(Enchantment enchantment : customEnchants) {
                if (byName.containsKey(enchantment.getName())) {
                    byName.remove(enchantment.getName());
                }
            }
        } catch (Exception ignored) { }
    }
}
