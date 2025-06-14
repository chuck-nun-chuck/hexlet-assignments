package exercise.controller.users;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;



// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> showUserPosts (@PathVariable Integer id) {
        return posts.stream()
                .filter(p -> p.getUserId() == id).toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{id}/posts")
    public Post showUserPosts (
            @PathVariable Integer id,
            @RequestBody Post data
    ) {
        data.setUserId(id);
        posts.add(data);
        return data;
    }
}
// END
