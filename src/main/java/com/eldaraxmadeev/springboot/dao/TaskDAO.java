package com.eldaraxmadeev.springboot.dao;

import com.eldaraxmadeev.springboot.domain.Task;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAO {
    @Autowired
    private EntityManager entityManager;

    public Task getTask(Integer id) {
        return getSession().get(Task.class, id);
    }

    public long getTasksCount() {
        Query<Long> query = getSession().createQuery("select count(t) from Task t", Long.class);
        return query.getSingleResult();
    }

    public List<Task> getAllTasksOnPage(int pageNumber, int pageSize) {
        Query<Task> query = getSession().createQuery("from Task", Task.class);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public Task saveOrUpdateTask(Task task) {
        return getSession().merge(task);
    }

    public void deleteTask(Integer id) {
        Task task = getTask(id);
        getSession().remove(task);
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }
}
