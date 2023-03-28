package me.notenoughskill.easyrep.easyrep;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ReputationSystem {
    public HashMap<String, Integer> playerReputations;

    public ReputationSystem() {
        this.playerReputations = new HashMap<String, Integer>();
    }

    public int getReputation(String playerName) {
        if (!this.playerReputations.containsKey(playerName)) {
            this.playerReputations.put(playerName, 0);
        }

        return this.playerReputations.get(playerName);
    }

    public void setReputation(String playerName, int reputation) {
        this.playerReputations.put(playerName, reputation);
    }

    public void setReputation(Player player, int reputation) {
        String playerName = player.getName();
        setReputation(playerName, reputation);
    }

}
