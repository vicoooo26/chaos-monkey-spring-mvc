package tk.vicochu.spring.mvc.chaos.monkey.Assaults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import tk.vicochu.spring.mvc.chaos.monkey.Configuration.ChaosMonkeySettings;

public class KillAppAssault implements ChaosMonkeyAssault, ApplicationContextAware {

    private ApplicationContext applicationContext;


    private static final Logger LOGGER = LoggerFactory.getLogger(KillAppAssault.class);
    private final ChaosMonkeySettings settings;

    public KillAppAssault(ChaosMonkeySettings settings) {
        this.settings = settings;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean isActive() {
        return settings.getAssaultProperties().isKillApplicationActive();
    }

    @Override
    public void attack() {
        LOGGER.info("Chaos Monkey - I am killing your Application!");
        try {
            ((ConfigurableApplicationContext) applicationContext).close();
            Thread.sleep(5000); // wait befor kill to deliver some metrics
            System.exit(0);
        } catch (Exception e) {
            LOGGER.info("Chaos Monkey - Unable to kill the App, I am not the BOSS!");
        }
    }
}
