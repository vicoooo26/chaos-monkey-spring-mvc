package tk.vicochu.spring.mvc.chaos.monkey.Aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import tk.vicochu.spring.mvc.chaos.monkey.Component.ChaosMonkey;

@Aspect
public class SpringRepositoryAspect extends ChaosMonkeyBaseAspect {
    private final ChaosMonkey chaosMonkey;

    public SpringRepositoryAspect(ChaosMonkey chaosMonkey) {
        this.chaosMonkey = chaosMonkey;
    }

    @Pointcut("this(org.springframework.data.repository.CrudRepository) || within(@org.springframework.data.repository.RepositoryDefinition *)")
    public void implementsCrudRepository() {

    }

    @Around("implementsCrudRepository() && allPublicMethodPointcut() && !classInChaosMonkeyPackage()")
    public Object intercept(ProceedingJoinPoint pjp) throws Throwable {


        MethodSignature signature = (MethodSignature) pjp.getSignature();

        chaosMonkey.callChaosMonkey(createSignature(signature));

        return pjp.proceed();
    }
}
