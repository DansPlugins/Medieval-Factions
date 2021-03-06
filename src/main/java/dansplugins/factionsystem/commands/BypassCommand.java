package dansplugins.factionsystem.commands;

import dansplugins.factionsystem.LocaleManager;
import dansplugins.factionsystem.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BypassCommand {

    public boolean toggleBypass(CommandSender sender) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(LocaleManager.getInstance().getText("OnlyPlayersCanUseCommand"));
            return false;
        }

        Player player = (Player) sender;

        if (!(player.hasPermission("mf.bypass") || player.hasPermission("mf.admin"))) {
            sender.sendMessage(ChatColor.RED + String.format(LocaleManager.getInstance().getText("PermissionNeeded"), "mf.bypass"));
            return false;
        }

        if (!EphemeralData.getInstance().getAdminsBypassingProtections().contains(player.getUniqueId())) {
            EphemeralData.getInstance().getAdminsBypassingProtections().add(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + LocaleManager.getInstance().getText("NowBypassingProtections"));
        }
        else {
            EphemeralData.getInstance().getAdminsBypassingProtections().remove(player.getUniqueId());
            player.sendMessage(ChatColor.GREEN + LocaleManager.getInstance().getText("NoLongerBypassingProtections"));
        }

        return true;

    }
}
