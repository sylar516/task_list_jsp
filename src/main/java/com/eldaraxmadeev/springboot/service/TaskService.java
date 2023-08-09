package com.eldaraxmadeev.springboot.service;

import com.eldaraxmadeev.springboot.dao.TaskDAO;
import com.eldaraxmadeev.springboot.domain.Status;
import com.eldaraxmadeev.springboot.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskDAO taskDAO;

    public long getTasksCount() {
        return taskDAO.getTasksCount();
    }

    public List<Task> getAllTasksOnPage(int pageNumber, int pageSize) {
        return taskDAO.getAllTasksOnPage(pageNumber, pageSize);
    }

    public Task createTask(String description, Status status) {
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        return taskDAO.saveOrUpdateTask(task);
    }
    public Task getTask(Integer id) {
        return taskDAO.getTask(id);
    }

    public Task saveOrUpdateTask(Integer id, String description, Status status) {
        Task task = taskDAO.getTask(id);
        task.setDescription(description);
        task.setStatus(status);

        return taskDAO.saveOrUpdateTask(task);
    }

    public void deleteTask(Integer id) {
        taskDAO.deleteTask(id);
    }
}
