package net.shieldcommunity.nullcordxplugin.managers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.shieldcommunity.nullcordx.api.NullCordX;
import net.shieldcommunity.nullcordx.api.NullCordXApi;
import net.shieldcommunity.nullcordx.api.checking.CheckingListFactory;
import net.shieldcommunity.nullcordx.api.checking.ConditionData;
import net.shieldcommunity.nullcordx.api.checking.ConditionEvent;
import net.shieldcommunity.nullcordx.api.checking.ConditionType;
import net.shieldcommunity.nullcordx.api.events.NullCordXReloadEvent;
import net.shieldcommunity.nullcordxplugin.checks.KeepAliveCheckFactory;
import net.shieldcommunity.nullcordxplugin.checks.WebCheckFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;

@RequiredArgsConstructor
public class CustomCheckManager implements LoadableManager, Listener {

    private final Plugin plugin;

    @Override
    public void load() {
        this.plugin.getProxy().getPluginManager().registerListener(this.plugin, this);
        initCustomCheck(NullCordXApi.getInstance());
    }

    @Override
    public void unload() {
        this.plugin.getProxy().getPluginManager().unregisterListener(this);
    }

    @EventHandler
    public void onNullCordXReloadEvent(NullCordXReloadEvent event) {
        initCustomCheck(event.getNullCordX());
    }

    private void initCustomCheck(@NonNull NullCordX nullCordX) {
        CheckingListFactory checkingListFactory = createCheckingListFactory();
        nullCordX.getCheckingFactories().setNormalCheckingFactoryForJava(checkingListFactory);

        this.plugin.getLogger().log(Level.INFO, "Custom checks successfully set!");
    }

    @NonNull
    private CheckingListFactory createCheckingListFactory() {
        KeepAliveCheckFactory keepAliveCheckFactory = new KeepAliveCheckFactory(
                createDefaultConditionData(),
                this.plugin.getLogger()
        );
        WebCheckFactory webCheckFactory = new WebCheckFactory(
                createDefaultConditionData(),
                this.plugin.getLogger(),
                this
        );

        return () -> List.of(
                keepAliveCheckFactory,
                webCheckFactory
        );
    }

    @NonNull
    public CompletableFuture<Boolean> checkInWeb(@NonNull String playerName) {
        return CompletableFuture.supplyAsync(() -> {
            this.plugin.getLogger().log(Level.INFO, "Checking in web player " + playerName + "...");
            // TODO Some web result
            return true;
        });
    }

    @NonNull
    private static ConditionData createDefaultConditionData() {
        return new ConditionData(
                new ConditionEvent(ConditionType.NEXT, null),
                new ConditionEvent(ConditionType.KICK, null),
                new ConditionEvent(ConditionType.KICK, null),
                true,
                true
        );
    }
}
