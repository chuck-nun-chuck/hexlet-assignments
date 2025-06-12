package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts") // Список постов
    public List<Post> index(
            @RequestParam(defaultValue = "5") Integer limit,
            @RequestParam(defaultValue = "1") Integer page) {
        return posts.subList(limit*page - limit, limit*page).stream().toList();
//                .limit(limit).toList();
    }

    @PostMapping("/posts") // Создание поста
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @GetMapping("/posts/{id}") // Вывод страницы
    public Optional<Post> show(@PathVariable String id) {
        var post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return post;
    }

    @PutMapping("/posts/{id}") // Обновление страницы
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var searchPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (searchPost.isPresent()) {
            var page = searchPost.get();
            page.setId(data.getId());
            page.setTitle(data.getTitle());
            page.setBody(data.getBody());
        }
        return data;
    }

    @DeleteMapping("/posts/{id}") // Удаление страницы
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END
}
