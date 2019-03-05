package tk.vicochu.spring.mvc.chaos.monkey.Assaults;

public interface ChaosMonkeyAssault {

    boolean isActive();

    void attack();

}
