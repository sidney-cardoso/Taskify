package br.com.sidneycardoso.taskify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sidneycardoso.taskify.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
