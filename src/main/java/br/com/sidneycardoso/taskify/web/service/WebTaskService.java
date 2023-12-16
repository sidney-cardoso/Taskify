package br.com.sidneycardoso.taskify.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sidneycardoso.taskify.core.exceptions.TaskNotFoundException;
import br.com.sidneycardoso.taskify.core.model.Task;
import br.com.sidneycardoso.taskify.core.repository.TaskRepository;
import br.com.sidneycardoso.taskify.web.dto.TaskForm;
import br.com.sidneycardoso.taskify.web.mappers.WebTaskMapper;

@Service
public class WebTaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private WebTaskMapper mapper;

    public List<Task> listAll() {
        return repository.findAll();
    }

    public Task create(TaskForm form) {
        Task model = mapper.toModel(form);

        return repository.save(model);
    }

    public Task findTaskById(Long id) {
        Optional<Task> taskFound = repository.findById(id);

        if (taskFound.isPresent()) {
            return taskFound.get();
        }

        String message = String.format("Tarefa com id %d", id);
        throw new TaskNotFoundException(message);
    }

    public Task edit(TaskForm form, Long id) {
        Task taskFound = findTaskById(id);

        Task model = mapper.toModel(form);
        model.setIdTask(taskFound.getIdTask());

        return repository.save(model);
    }

    public void deleteTaskById(Long id) {
        Task taskFound = findTaskById(id);
        repository.delete(taskFound);
    }
}
