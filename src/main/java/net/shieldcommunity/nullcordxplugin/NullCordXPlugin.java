package net.shieldcommunity.nullcordxplugin;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;

public final class NullCordXPlugin extends Plugin {

    @Override
    public void onEnable() {
        getLogger().log(Level.INFO, "Plugin successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "Plugin successfully disabled!");
    }
}
