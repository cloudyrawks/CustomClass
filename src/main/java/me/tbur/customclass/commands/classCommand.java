package me.tbur.customclass.commands;

import me.tbur.customclass.classmenu.ClassMenu;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class classCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player))
            sender.sendMessage(ChatColor.RED + "Please perform that command as a player");
        else {
            if (!(player.hasPermission("customclass.classmenu"))) {
                player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Hey! " + ChatColor.GRAY + "You don't have permission to do that!");
                return true;
            }
            else player.sendMessage(ChatColor.GREEN + "Opening Menu...");
            ClassMenu.openClassMenu(player);
            return true;
        }
        return true;
    }
}
