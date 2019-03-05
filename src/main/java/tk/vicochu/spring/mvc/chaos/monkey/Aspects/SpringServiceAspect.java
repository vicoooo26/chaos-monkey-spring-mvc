package tk.vicochu.spring.mvc.chaos.monkey.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.vicochu.spring.mvc.chaos.monkey.Component.ChaosMonkey;

@Aspect
public class SpringServiceAspect extends ChaosMonkeyBaseAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringServiceAspect.class);

    private final ChaosMonkey chaosMonkey;

    public SpringServiceAspect(ChaosMonkey chaosMonkey) {
        this.chaosMonkey = chaosMonkey;
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void classAnnotatedWithControllerPointcut() {
    }

    @Around("classAnnotatedWithControllerPointcut() && allPublicMethodPointcut() && !classInChaosMonkeyPackage()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.debug(LOGGER.isDebugEnabled() ? "Service class and public method detected: " + pjp.getSignature() : null);

        MethodSignature signature = (MethodSignature) pjp.getSignature();

        chaosMonkey.callChaosMonkey(createSignature(signature));

        return pjp.proceed();
    }
}
