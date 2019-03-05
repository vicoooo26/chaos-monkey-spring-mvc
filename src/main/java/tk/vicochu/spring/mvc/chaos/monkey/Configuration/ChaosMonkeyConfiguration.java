package tk.vicochu.spring.mvc.chaos.monkey.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import tk.vicochu.spring.mvc.chaos.monkey.Aspects.*;
import tk.vicochu.spring.mvc.chaos.monkey.Assaults.ChaosMonkeyAssault;
import tk.vicochu.spring.mvc.chaos.monkey.Assaults.ExceptionAssault;
import tk.vicochu.spring.mvc.chaos.monkey.Assaults.KillAppAssault;
import tk.vicochu.spring.mvc.chaos.monkey.Assaults.LatencyAssault;
import tk.vicochu.spring.mvc.chaos.monkey.Component.ChaosMonkey;
import tk.vicochu.spring.mvc.chaos.monkey.Condition.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
@Profile("chaos-monkey")
public class ChaosMonkeyConfiguration implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChaosMonkey.class);
    private ChaosMonkeyProperties chaosMonkeyProperties;
    private WatcherProperties watcherProperties;
    private AssaultProperties assaultProperties;
    private ApplicationContext applicationContext;

    public ChaosMonkeyConfiguration() {
        try {
            String chaosLogo = StreamUtils.copyToString(new ClassPathResource("chaos-logo.txt").getInputStream(), Charset.defaultCharset());
            LOGGER.info(chaosLogo);
        } catch (IOException e) {
            LOGGER.info("Chaos Monkey - ready to do evil");
        }

    }

    @PostConstruct
    public void initChaosMonkeyConfiguration() {
        this.chaosMonkeyProperties = applicationContext.getBean(ChaosMonkeyProperties.class);
        this.watcherProperties = applicationContext.getBean(WatcherProperties.class);
        this.assaultProperties = applicationContext.getBean(AssaultProperties.class);
    }

    @Bean
    public ChaosMonkeySettings settings() {
        return new ChaosMonkeySettings(this.chaosMonkeyProperties, this.assaultProperties, this.watcherProperties);
    }

    @Bean
    public LatencyAssault latencyAssault() {
        return new LatencyAssault(settings());
    }

    @Bean
    public ExceptionAssault exceptionAssault() {
        return new ExceptionAssault(settings());
    }

    @Bean
    public KillAppAssault killAppAssault() {
        return new KillAppAssault(settings());
    }

    @Bean
    public ChaosMonkey chaosMonkey(List<ChaosMonkeyAssault> chaosMonkeyAssaults) {
        return new ChaosMonkey(settings(), chaosMonkeyAssaults);
    }

    @Bean
    @Conditional(AttackControllerCondition.class)
    public SpringControllerAspect controllerAspect(ChaosMonkey chaosMonkey) {
        return new SpringControllerAspect(chaosMonkey);
    }

    @Bean
    @Conditional(AttackRestControllerCondition.class)
    public SpringRestControllerAspect restControllerAspect(ChaosMonkey chaosMonkey) {
        return new SpringRestControllerAspect(chaosMonkey);
    }

    @Bean
    @Conditional(AttackServiceCondition.class)
    public SpringServiceAspect serviceAspect(ChaosMonkey chaosMonkey) {
        return new SpringServiceAspect(chaosMonkey);
    }

    @Bean
    @Conditional(AttackComponentCondition.class)
    public SpringComponentAspect componentAspect(ChaosMonkey chaosMonkey) {
        return new SpringComponentAspect(chaosMonkey);
    }

    @Bean
    @Conditional(AttackRepositoryCondition.class)
    public SpringRepositoryAspect repositoryAspect(ChaosMonkey chaosMonkey) {
        return new SpringRepositoryAspect(chaosMonkey);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}