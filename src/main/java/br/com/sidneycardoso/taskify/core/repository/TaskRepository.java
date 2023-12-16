package br.com.sidneycardoso.taskify.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sidneycardoso.taskify.core.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
