package tk.vicochu.spring.mvc.chaos.monkey.Configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class ChaosMonkeyProperties {

    @Value("${chaos.monkey.enabled:false}")
    private boolean enabled;

}
