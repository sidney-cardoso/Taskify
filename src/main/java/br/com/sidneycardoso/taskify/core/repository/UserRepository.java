package br.com.sidneycardoso.taskify.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sidneycardoso.taskify.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
