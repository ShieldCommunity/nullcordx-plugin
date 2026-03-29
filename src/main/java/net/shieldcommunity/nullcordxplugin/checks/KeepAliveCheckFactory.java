package net.shieldcommunity.nullcordxplugin.checks;

import lombok.NonNull;
import net.shieldcommunity.nullcordx.api.NullCordX;
import net.shieldcommunity.nullcordx.api.checking.*;

import java.util.logging.Logger;

public class KeepAliveCheckFactory extends LazyLoadedCheckingFactory {

    private final Logger logger;

    public KeepAliveCheckFactory(@NonNull ConditionData conditionData,
                                 @NonNull Logger logger) {
        super("wait", conditionData);
        this.logger = logger;
    }

    @NonNull
    @Override
    public AbstractChecking createChecking(@NonNull CheckingUser connector, @NonNull NullCordX nullCordX) {
        return new KeepAliveCheck(this, connector, nullCordX, this.logger);
    }

    @NonNull
    @Override
    public Class<? extends AbstractChecking> getType() {
        return KeepAliveCheck.class;
    }
}
