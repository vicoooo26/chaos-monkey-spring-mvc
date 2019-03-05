package tk.vicochu.spring.mvc.chaos.monkey.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ChaosMonkeySettings {

    @NotNull
    private ChaosMonkeyProperties chaosMonkeyProperties;
    @NotNull
    private AssaultProperties assaultProperties;
    @NotNull
    private WatcherProperties watcherProperties;

}
