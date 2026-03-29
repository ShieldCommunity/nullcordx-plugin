package net.shieldcommunity.nullcordxplugin.checks;

import lombok.NonNull;
import net.md_5.bungee.protocol.packet.KeepAlive;
import net.shieldcommunity.nullcordx.api.NullCordX;
import net.shieldcommunity.nullcordx.api.checking.AbstractVirtualChecking;
import net.shieldcommunity.nullcordx.api.checking.CheckingFactory;
import net.shieldcommunity.nullcordx.api.checking.CheckingUser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class KeepAliveCheck extends AbstractVirtualChecking {

    private final Logger logger;
    private int time = 0;

    public KeepAliveCheck(@NonNull CheckingFactory checkingFactory,
                          @NonNull CheckingUser connector,
                          @NonNull NullCordX nullCordX,
                          @NonNull Logger logger) {
        super(checkingFactory, connector, nullCordX);
        this.logger = logger;
    }

    @NonNull
    @Override
    public String getName() {
        return "KeepAlive";
    }

    @Override
    public void onAdded() {
        this.time = 0;
    }

    @Override
    public void onRemoved() {

    }

    @Override
    public void handle(KeepAlive keepAlive) throws Exception {
        if (this.time > 5) {
            this.logger.log(Level.WARNING, "Keep alive check completed for " + this.connector.getName() + "!");
            complete();
            return;
        }

        this.time++;
    }
}
