package me.notenoughskill.easyrep.easyrep;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class EasyRep extends JavaPlugin{
    private ReputationSystem reputationSystem;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        this.reputationSystem = new ReputationSystem();
        this.config = YamlConfiguration.loadConfiguration(new File("plugins/EasyRep/reputation.yml"));
        loadReputations();

        this.getCommand("checkrep").setExecutor(new ReputationCommands(this.reputationSystem));
        this.getCommand("rep").setExecutor(new ReputationCommands(this.reputationSystem));
        this.getCommand("negrep").setExecutor(new ReputationCommands(this.reputationSystem));
    }

    private void loadReputations() {
        if (!this.config.contains("reputations")) {
            return;
        }
        for (String playerName : this.config.getConfigurationSection("reputations").getKeys(false)) {
            int reputation = this.config.getInt("reputations." + playerName);
            reputationSystem.playerReputations.put(playerName, reputation);
        }
    }

    private void saveReputations() {
        for (String playerName : reputationSystem.playerReputations.keySet()) {
            int reputation = reputationSystem.playerReputations.get(playerName);
            this.config.set("reputations." + playerName, reputation);
        }
        try {
            this.config.save(new File("plugins/EasyRep/reputations.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}