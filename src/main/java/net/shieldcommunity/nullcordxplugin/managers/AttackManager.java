package net.shieldcommunity.nullcordxplugin.managers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.shieldcommunity.nullcordx.api.events.*;

import java.util.logging.Level;

@RequiredArgsConstructor
public class AttackManager implements LoadableManager, Listener {

    private final Plugin plugin;

    @Override
    public void load() {
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
    }

    @Override
    public void unload() {
        this.plugin.getProxy().getPluginManager().unregisterListener(this);
    }

    @EventHandler
    public void onBotAttackDetectedEvent(BotAttackDetectedEvent event) {
        onAttackDetected(event, "bot");
    }

    @EventHandler
    public void onBotAttackEndedEvent(BotAttackEndedEvent event) {
        onAttackEnded(event, "bot");
    }

    @EventHandler
    public void onPingAttackDetectedEvent(PingAttackDetectedEvent event) {
        onAttackDetected(event, "ping");
    }

    @EventHandler
    public void onPingAttackEndedEvent(PingAttackEndedEvent event) {
        onAttackEnded(event, "ping");
    }

    @EventHandler
    public void onSpamAttackDetectedEvent(SpamAttackDetectedEvent event) {
        onAttackDetected(event, "spam");
    }

    @EventHandler
    public void onSpamAttackEndedEvent(SpamAttackEndedEvent event) {
        onAttackEnded(event, "spam");
    }

    @EventHandler
    public void onNullCordXReloadEvent(NullCordXReloadEvent event) {
        this.plugin.getLogger().log(Level.WARNING, "NullCordX reloaded!");
    }

    @EventHandler
    public void onUserCheckedSuccessfullyEvent(UserCheckedSuccessfullyEvent event) {
        this.plugin.getLogger().log(Level.INFO, "User " + event.getUser().getName() + " checked successfully!");
    }

    @EventHandler
    public void onUserFailedCheckEvent(UserFailedCheckEvent event) {
        this.plugin.getLogger().log(Level.WARNING, "User " + event.getUser().getName() + " failed check! Kick type: " + event.getKickType() + ". Message: " + event.getUser());
    }

    @EventHandler
    public void onUserConnectedEvent(UserConnectedEvent event) {
        this.plugin.getLogger().log(Level.INFO, "User " + event.getUser().getName() + " connected!");
    }

    private void onAttackDetected(@NonNull AbstractAttackEvent<?> attackEvent, @NonNull String type) {
        this.plugin.getLogger().log(Level.WARNING, type + " attack detected! Trigger data: " + attackEvent.getTriggerData() + ". Time: " + attackEvent.getTime());
    }

    private void onAttackEnded(@NonNull AbstractAttackEvent<?> attackEvent, @NonNull String type) {
        this.plugin.getLogger().log(Level.WARNING, type + " attack ended! Trigger data: " + attackEvent.getTriggerData() + ". Time: " + attackEvent.getTime());
    }
}
