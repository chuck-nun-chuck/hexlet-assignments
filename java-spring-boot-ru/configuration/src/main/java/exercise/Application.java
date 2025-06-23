package exercise;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;
import exercise.component.UserProperties;


@SpringBootApplication
@RestController
public class Application {
    private Logger logger = Logger.getLogger(Application.class.getName());

    // Все пользователи
    private final List<User> users = Data.getUsers();

    //Admins emails
    @Autowired
    private UserProperties userProperties;

    // BEGIN
    @GetMapping("/admins")
    public List<String> showAdmins() {
        logger.info("emails: ${emails}");
        return users
                .stream()
                .filter(
                x -> userProperties.getAdmins().contains(x.getEmail())
                ).map(user -> user.getName()).toList();
    }
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> Objects.equals(u.getId(), id))
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
