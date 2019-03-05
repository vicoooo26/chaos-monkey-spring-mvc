package tk.vicochu.spring.mvc.chaos.monkey.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import tk.vicochu.spring.mvc.chaos.monkey.Component.ChaosMonkey;

@Aspect
public class SpringRestControllerAspect extends ChaosMonkeyBaseAspect {
    private final ChaosMonkey chaosMonkey;

    public SpringRestControllerAspect(ChaosMonkey chaosMonkey) {
        this.chaosMonkey = chaosMonkey;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void classAnnotatedWithControllerPointcut() {
    }

    @Around("classAnnotatedWithControllerPointcut() && allPublicMethodPointcut() && !classInChaosMonkeyPackage()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        chaosMonkey.callChaosMonkey(createSignature(signature));
        return pjp.proceed();
    }
}
