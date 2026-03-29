package net.shieldcommunity.nullcordxplugin;

import net.md_5.bungee.api.plugin.Plugin;
import net.shieldcommunity.nullcordxplugin.managers.AttackManager;
import net.shieldcommunity.nullcordxplugin.managers.CustomCheckManager;

import java.util.logging.Level;

public final class NullCordXPlugin extends Plugin {

    private AttackManager attackManager;
    private CustomCheckManager customCheckManager;

    @Override
    public void onEnable() {
        this.attackManager = new AttackManager(this);
        this.attackManager.load();

        this.customCheckManager = new CustomCheckManager(this);
        this.customCheckManager.load();

        getLogger().log(Level.INFO, "Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        if (this.attackManager != null) {
            this.attackManager.unload();
            this.attackManager = null;
        }

        if (this.customCheckManager != null) {
            this.customCheckManager.unload();
            this.customCheckManager = null;
        }

        getLogger().log(Level.INFO, "Plugin successfully disabled!");
    }
}
