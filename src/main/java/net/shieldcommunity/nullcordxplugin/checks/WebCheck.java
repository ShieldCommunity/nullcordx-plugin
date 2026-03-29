package net.shieldcommunity.nullcordxplugin.checks;

import lombok.NonNull;
import net.shieldcommunity.nullcordx.api.KickType;
import net.shieldcommunity.nullcordx.api.NullCordX;
import net.shieldcommunity.nullcordx.api.checking.AbstractVirtualChecking;
import net.shieldcommunity.nullcordx.api.checking.CheckingFactory;
import net.shieldcommunity.nullcordx.api.checking.CheckingUser;
import net.shieldcommunity.nullcordxplugin.managers.CustomCheckManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class WebCheck extends AbstractVirtualChecking {

    private final Logger logger;
    private final CustomCheckManager customCheckManager;

    public WebCheck(@NonNull CheckingFactory checkingFactory,
                    @NonNull CheckingUser connector,
                    @NonNull NullCordX nullCordX,
                    @NonNull Logger logger,
                    @NonNull CustomCheckManager customCheckManager) {
        super(checkingFactory, connector, nullCordX);
        this.logger = logger;
        this.customCheckManager = customCheckManager;
    }

    @NonNull
    @Override
    public String getName() {
        return "Web";
    }

    @Override
    public void onAdded() {
        this.customCheckManager.checkInWeb(this.connector.getName()).whenComplete((result, throwable) -> {
            if (throwable != null) {
                this.logger.log(Level.SEVERE, "Failed to check Web", throwable);
                complete(KickType.CHECKING_ERROR, "Error on Web check");
                return;
            }

            if (result) {
                complete();
                return;
            }

            complete(KickType.FAILED_CHECK, "Web failed");
        });
    }

    @Override
    public void onRemoved() {

    }
}
