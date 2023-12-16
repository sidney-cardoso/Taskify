package br.com.sidneycardoso.taskify.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.server.ServerErrorException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import br.com.sidneycardoso.taskify.core.model.User;
import br.com.sidneycardoso.taskify.core.response.*;
import br.com.sidneycardoso.taskify.web.dto.UserForm;
import br.com.sidneycardoso.taskify.web.service.WebUserService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private WebUserService service;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody UserForm user) {
        try {
            User savedUser = service.create(user);
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
            Iterable<User> users = service.listAll();
            return ResponseEntity.ok(new UserResponse(users));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new UserResponse("Ocorreu um erro ao obter a lista de usuários."));
        }
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<RegistrationResponse> editar(@PathVariable Long id,
            @Valid UserForm form) {
        try {
            service.edit(form, id);
            return ResponseEntity.ok(new RegistrationResponse("Usuário atualizado com sucesso!"));
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegistrationResponse("Erro ao atualizar o usuário. Verifique os dados fornecidos."));
        } catch (ServerErrorException err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Ocorreu um erro ao atualizar o usuário!"));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DeleteResponse> delete(@PathVariable("id") Long id) {
        try {
            service.findUserById(id);
            return ResponseEntity.ok(new DeleteResponse("Usuário excluído com sucesso", id));
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeleteResponse("Ocorreu um erro interno ao processar a exclusão do usuário"));
        }
    }
}