package tk.vicochu.spring.mvc.chaos.monkey.Configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@Data
@NoArgsConstructor
@Validated
@Component
public class AssaultProperties {
    @Value("${chaos.monkey.assaults.level:5}")
    @Min(value = 1)
    @Max(value = 10000)
    private int level;

    @Value("${chaos.monkey.assaults.latencyRangeStart:1000}")
    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    private int latencyRangeStart;

    @Value("${chaos.monkey.assaults.latencyRangeEnd:3000}")
    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    private int latencyRangeEnd;

    @Value("${chaos.monkey.assaults.latencyActive:true}")
    private boolean latencyActive;

    @Value("${chaos.monkey.assaults.exceptionsActive:false}")
    private boolean exceptionsActive;

//    @AssaultExceptionConstraint
//    private AssaultException exception;

    @Value("${chaos.monkey.assaults.killApplicationActive:false}")
    private boolean killApplicationActive;

    @Value("${chaos.monkey.assaults.watchedCustomServices:#{null}}")
    private List<String> watchedCustomServices;

//    public AssaultException getException() {
//        return exception == null ? new AssaultException() : exception;
//    }

//    public void setException(AssaultException exception) {
//        this.exception = exception;
//    }

    @JsonIgnore
    public int getTroubleRandom() {
        return ThreadLocalRandom.current().nextInt(1, getLevel() + 1);
    }

    @JsonIgnore
    public int chooseAssault(int amount) {
        return ThreadLocalRandom.current().nextInt(0, amount);
    }


}
