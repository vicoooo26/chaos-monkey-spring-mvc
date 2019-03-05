package tk.vicochu.spring.mvc.chaos.monkey.Component;

import tk.vicochu.spring.mvc.chaos.monkey.Assaults.ChaosMonkeyAssault;
import tk.vicochu.spring.mvc.chaos.monkey.Configuration.ChaosMonkeySettings;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

public class ChaosMonkey {
    private final ChaosMonkeySettings chaosMonkeySettings;
    private final List<ChaosMonkeyAssault> assaults;

    public ChaosMonkey(ChaosMonkeySettings chaosMonkeySettings, List<ChaosMonkeyAssault> assaults) {
        this.chaosMonkeySettings = chaosMonkeySettings;
        this.assaults = assaults;
    }


    public void callChaosMonkey(String simpleName) {
        if (isTrouble() && isEnabled()) {
            //TODO need to implement: attack specific service
            chooseAndRunAttack();
        }
    }

    private void chooseAndRunAttack() {
        List<ChaosMonkeyAssault> activeAssaults = assaults.stream()
                .filter(ChaosMonkeyAssault::isActive)
                .collect(Collectors.toList());
        if (isEmpty(activeAssaults)) {
            return;
        }
        getRandomFrom(activeAssaults).attack();

    }

    private ChaosMonkeyAssault getRandomFrom(List<ChaosMonkeyAssault> activeAssaults) {
        int exceptionRand = chaosMonkeySettings.getAssaultProperties().chooseAssault(activeAssaults.size());
        return activeAssaults.get(exceptionRand);
    }

    private boolean isTrouble() {
        return chaosMonkeySettings.getAssaultProperties().getTroubleRandom() >= chaosMonkeySettings.getAssaultProperties().getLevel();
    }

    private boolean isEnabled() {
        //TODO stackoverflow here
        return this.chaosMonkeySettings.getChaosMonkeyProperties().isEnabled();
    }
}
