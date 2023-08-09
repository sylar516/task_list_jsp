package com.eldaraxmadeev.springboot.controller;

import com.eldaraxmadeev.springboot.domain.Task;
import com.eldaraxmadeev.springboot.domain.TaskDTO;
import com.eldaraxmadeev.springboot.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping()
    public List<TaskDTO> getAllTasks(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        List<Task> allTasksOnPage = taskService.getAllTasksOnPage(pageNumber, pageSize);
        return allTasksOnPage.stream().map(this::toTaskDTO).collect(Collectors.toList());
    }

    @GetMapping("/count")
    public long getTasksCount() {
        return taskService.getTasksCount();
    }

    @GetMapping("/{ID}")
    public Task getTask(@PathVariable("ID") Integer id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskService.createTask(taskDTO.description, taskDTO.status);
        return toTaskDTO(task);
    }

    @PostMapping("/{ID}")
    public TaskDTO saveTask(@PathVariable("ID") Integer id, @RequestBody TaskDTO taskDTO) {
        Task task = taskService.saveOrUpdateTask(id, taskDTO.description, taskDTO.status);
        return toTaskDTO(task);
    }

    @DeleteMapping("/{ID}")
    public void deleteTask(@PathVariable("ID") Integer id) {
        taskService.deleteTask(id);
    }

    private TaskDTO toTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.id = task.getId();
        taskDTO.description = task.getDescription();
        taskDTO.status = task.getStatus();
        return taskDTO;
    }
}
