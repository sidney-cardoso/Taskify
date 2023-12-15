package br.com.sidneycardoso.taskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sidneycardoso.taskify.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
