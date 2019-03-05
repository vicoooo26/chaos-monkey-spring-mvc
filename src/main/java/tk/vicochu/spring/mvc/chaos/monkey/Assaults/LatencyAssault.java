package tk.vicochu.spring.mvc.chaos.monkey.Assaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.vicochu.spring.mvc.chaos.monkey.Configuration.ChaosMonkeySettings;

import java.util.concurrent.ThreadLocalRandom;

public class LatencyAssault implements ChaosMonkeyAssault {

    private static final Logger LOGGER = LoggerFactory.getLogger(LatencyAssault.class);
    private final ChaosMonkeySettings settings;

    public LatencyAssault(ChaosMonkeySettings settings) {
        this.settings = settings;
    }

    @Override
    public boolean isActive() {
        return settings.getAssaultProperties().isLatencyActive();
    }

    @Override
    public void attack() {
        LOGGER.debug("Chaos Monkey - timeout");
        int timeout = ThreadLocalRandom.current().nextInt(settings.getAssaultProperties().getLatencyRangeStart(),
                settings.getAssaultProperties().getLatencyRangeEnd());
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
