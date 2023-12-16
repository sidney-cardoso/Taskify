package br.com.sidneycardoso.taskify.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.server.ServerErrorException;

import jakarta.validation.ConstraintViolationException;
import br.com.sidneycardoso.taskify.core.model.User;
import br.com.sidneycardoso.taskify.core.repository.UserRepository;
import br.com.sidneycardoso.taskify.core.response.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody User user) {
        try {
            User savedUser = repository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(savedUser));
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegistrationResponse("Erro ao cadastrar o usuário. Verifique os dados fornecidos."));
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Ocorreu um erro interno ao processar a solicitação."));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<UserResponse> list() {
        try {
            Iterable<User> users = repository.findAll();
            return ResponseEntity.ok(new UserResponse(users));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserResponse("Ocorreu um erro ao obter a lista de usuários."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            Optional<User> userToDelete = repository.findById(id);

            if (userToDelete.isPresent()) {
                repository.deleteById(id);
                return ResponseEntity.ok(new DeleteResponse("Usuário excluído com sucesso", id));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new DeleteResponse("Usuário não encontrado para exclusão"));
            }
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeleteResponse("Ocorreu um erro interno ao processar a exclusão do usuário"));
        }
    }
}