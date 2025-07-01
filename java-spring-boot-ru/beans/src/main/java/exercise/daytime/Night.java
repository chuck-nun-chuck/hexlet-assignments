package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Night implements Daytime {
    private String name = "night";

//    public String getName() {
//        return name;
//    }

    // BEGIN

    @PostConstruct
    public void init() {
        System.out.println("Night bean has been created!");
    }

    @Override
    public String getName() {
        return "night";
        }

    // END
}
