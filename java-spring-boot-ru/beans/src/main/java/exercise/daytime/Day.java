package exercise.daytime;
import jakarta.annotation.PostConstruct;

public class Day implements Daytime {
    private String name = "day";

//    public String getName() {
//        return name;
//    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Day bean has been created!");
    }

    @Override
    public String getName() {
        return "day";
    }

    // END
}
