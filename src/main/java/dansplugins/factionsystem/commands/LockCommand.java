package dansplugins.factionsystem.commands;

import dansplugins.factionsystem.LocaleManager;
import dansplugins.factionsystem.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockCommand { ;

    public void lockBlock(CommandSender sender, String[] args) {
        // check if player
        if (sender instanceof Player) {

            Player player = (Player) sender;

            // check if has permission
            if (player.hasPermission("mf.lock")) {

                // check if argument exists
                if (args.length > 1) {

                    // cancel lock status if first argument is "cancel"
                    if (args[1].equalsIgnoreCase("cancel")) {
                        if (EphemeralData.getInstance().getLockingPlayers().contains(player.getUniqueId())) {
                            EphemeralData.getInstance().getLockingPlayers().remove(player.getUniqueId());
                            player.sendMessage(ChatColor.RED + LocaleManager.getInstance().getText("LockingCancelled"));
                            return;
                        }
                    }
                }

                // check that player has not already invoked this command without locking something
                if (!EphemeralData.getInstance().getLockingPlayers().contains(player.getUniqueId())) {
                    // add player to playersAboutToLockSomething list
                    EphemeralData.getInstance().getLockingPlayers().add(player.getUniqueId());

                    EphemeralData.getInstance().getUnlockingPlayers().remove(player.getUniqueId());

                    // inform them they need to right click the block that they want to lock or type /mf lock cancel to cancel it
                    player.sendMessage(ChatColor.GREEN + LocaleManager.getInstance().getText("RightClickLock"));
                }



            }
            else {
                sender.sendMessage(ChatColor.RED + String.format(LocaleManager.getInstance().getText("PermissionNeeded"), "mf.lock"));
            }

        }
    }

}
