package tk.vicochu.spring.mvc.chaos.monkey.Assaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.vicochu.spring.mvc.chaos.monkey.Configuration.ChaosMonkeySettings;

public class ExceptionAssault implements ChaosMonkeyAssault {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAssault.class);
    private final ChaosMonkeySettings settings;

    public ExceptionAssault(ChaosMonkeySettings settings) {
        this.settings = settings;
    }

    @Override
    public boolean isActive() {
        return settings.getAssaultProperties().isExceptionsActive();
    }

    @Override
    public void attack() {
        LOGGER.info("Chaos Monkey - exception");
        throw new RuntimeException("Chaos Monkey - RuntimeException");
    }
}

