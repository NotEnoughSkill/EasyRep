package me.notenoughskill.easyrep.easyrep;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReputationCommands implements CommandExecutor {
    private ReputationSystem reputationSystem;

    public ReputationCommands(ReputationSystem reputationSystem) {
        this.reputationSystem = reputationSystem;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player!");
            return true;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("checkrep")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /checkrep <player>");
                return false;
            }

            String playerName = args[0];
            int reputation = this.reputationSystem.getReputation(playerName);

            player.sendMessage(String.format("%s's reputation: %d", playerName, reputation));
            return true;
        } else if (cmd.getName().equalsIgnoreCase("rep")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /checkrep <player>");
                return false;
            }
            String playerName = args[0];
            Player target = player.getServer().getPlayer(playerName);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online!");
                return true;
            }
            int currentReputation = this.reputationSystem.getReputation(player.getName());
            this.reputationSystem.setReputation(player.getName(), currentReputation + 1);
            sender.sendMessage(ChatColor.GREEN + "You gave +rep to " + target.getName());
            target.sendMessage(ChatColor.GREEN + "You received +rep from " + sender.getName());
            return true;
        } else if (cmd.getName().equalsIgnoreCase("negrep")) {
            if (args.length != 1) {
                player.sendMessage(ChatColor.RED + "Usage: /negrep <player>");
                return false;
            }
            String playerName = args[0];
            Player target = player.getServer().getPlayer(playerName);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "That player is not online!");
                return true;
            }
            int currentReputation = this.reputationSystem.getReputation(player.getName());
            this.reputationSystem.setReputation(player.getName(), currentReputation - 1);
            sender.sendMessage(ChatColor.RED + "You gave -rep to " + target.getName());
            target.sendMessage(ChatColor.RED + "You received -rep from " + sender.getName());
            return true;
        }

        return true;
    }
}
