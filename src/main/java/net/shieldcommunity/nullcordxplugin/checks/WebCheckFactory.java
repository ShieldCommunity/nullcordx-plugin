package net.shieldcommunity.nullcordxplugin.checks;

import lombok.NonNull;
import net.shieldcommunity.nullcordx.api.NullCordX;
import net.shieldcommunity.nullcordx.api.checking.AbstractChecking;
import net.shieldcommunity.nullcordx.api.checking.CheckingUser;
import net.shieldcommunity.nullcordx.api.checking.ConditionData;
import net.shieldcommunity.nullcordx.api.checking.LazyLoadedCheckingFactory;
import net.shieldcommunity.nullcordxplugin.managers.CustomCheckManager;

import java.util.logging.Logger;

public class WebCheckFactory extends LazyLoadedCheckingFactory {

    private final Logger logger;
    private final CustomCheckManager customCheckManager;

    public WebCheckFactory(@NonNull ConditionData conditionData,
                           @NonNull Logger logger,
                           @NonNull CustomCheckManager customCheckManager) {
        super("web", conditionData);
        this.logger = logger;
        this.customCheckManager = customCheckManager;
    }

    @NonNull
    @Override
    public AbstractChecking createChecking(@NonNull CheckingUser connector, @NonNull NullCordX nullCordX) {
        return new WebCheck(this, connector, nullCordX, this.logger, this.customCheckManager);
    }

    @NonNull
    @Override
    public Class<? extends AbstractChecking> getType() {
        return WebCheck.class;
    }
}
