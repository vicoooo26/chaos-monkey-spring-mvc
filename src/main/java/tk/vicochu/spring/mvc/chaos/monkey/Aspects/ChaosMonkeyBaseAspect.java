package tk.vicochu.spring.mvc.chaos.monkey.Aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

public abstract class ChaosMonkeyBaseAspect {

    //TODO need to configure poiontcut scope, profile and package into a jar
    @Pointcut("within(tk.vicochu.spring.mvc.chaos.monkey..*)")
    public void classInChaosMonkeyPackage() {
    }

    @Pointcut("execution(* *.*(..))")
    public void allPublicMethodPointcut() {
    }

    String createSignature(MethodSignature signature) {

        return signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
    }
}
