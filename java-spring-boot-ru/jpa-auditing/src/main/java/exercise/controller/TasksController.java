package exercise.controller;

import exercise.exception.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Task;
import exercise.repository.TaskRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(path = "")
    public List<Task> index() {
        return taskRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Task show(@PathVariable long id) {

        var task =  taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));

        return task;
    }

    // BEGIN
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create (@RequestBody Task task) {
//        Task existingTask = taskRepository.findByTitle(task.getTitle())
//            .orElseThrow(
//                () -> new ResourceAlreadyExistsException("Task with title " + task.getTitle() + " already exists" )
//            );
        return taskRepository.save(task);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable long id, @RequestBody Task task) {
        Task existingTask = taskRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("There no task with id: " + id));
        existingTask.setDescription(task.getDescription());
        existingTask.setTitle(task.getTitle());
        return taskRepository.save(existingTask);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
