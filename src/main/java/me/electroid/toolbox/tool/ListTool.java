package me.electroid.toolbox.tool;

import com.sk89q.minecraft.util.commands.*;
import me.electroid.toolbox.Tool;
import me.electroid.toolbox.filter.PlayerFilter;
import org.bukkit.*;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ElectroidFilms on 7/21/15.
 */
public class ListTool extends Tool {

    public ListTool() {
        super(true, false);
    }

    @Command(
            aliases = {"list", "players"},
            desc = "Lists players that are on the server.",
            max = -1,
            min = 0,
            usage = "[filter]"
    )
    @CommandPermissions("toolbox.list")
    @Console
    public static void list(final CommandContext args, final CommandSender sender) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        if (args.argsLength() >= 1) {
            players = new PlayerFilter().evaluate((Collection<Player>) players, sender, args.getRemainingString(0));
        }
        if (players.size() == 0) {
            sender.sendMessage(ChatColor.RED + "No players online match the given filter!");
        } else {
            List<String> names = new ArrayList<String>();
            for (Player player : players) {
                names.add(player.getDisplayName(sender));
            }
            sender.sendMessage("(" + names.size() + "/" + Bukkit.getMaxPlayers() + "): " + names.toString());
        }
    }

}
