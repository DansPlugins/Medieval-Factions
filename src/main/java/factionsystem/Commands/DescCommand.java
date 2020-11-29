package factionsystem.Commands;

import factionsystem.MedievalFactions;
import factionsystem.Objects.Faction;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DescCommand extends Command {

    public DescCommand(MedievalFactions plugin) {
        super(plugin);
    }

    public boolean setDescription(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            boolean owner = false;
            for (Faction faction : main.factions) {
                if (faction.isOwner(player.getUniqueId())) {
                    owner = true;
                    if (args.length > 1) {

                        // set arg[1] - args[args.length-1] to be the description with spaces put in between
                        String newDesc = "";
                        for (int i = 1; i < args.length; i++) {
                            newDesc = newDesc + args[i];
                            if (!(i == args.length - 1)) {
                                newDesc = newDesc + " ";
                            }
                        }

                        faction.setDescription(newDesc);
                        player.sendMessage(ChatColor.AQUA + "Description set!");
                        return true;
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "Usage: /mf desc (description)");
                        return false;
                    }
                }
            }
            if (!owner) {
                player.sendMessage(ChatColor.RED + "You need to be the owner of a faction to use this command.");
                return false;
            }
        }
        return false;
    }

}
