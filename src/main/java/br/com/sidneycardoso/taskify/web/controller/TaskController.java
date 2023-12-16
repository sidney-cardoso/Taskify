package br.com.sidneycardoso.taskify.web.controller;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import br.com.sidneycardoso.taskify.core.model.Status;
import br.com.sidneycardoso.taskify.core.model.Task;
import br.com.sidneycardoso.taskify.core.response.*;
import br.com.sidneycardoso.taskify.web.dto.TaskForm;
import br.com.sidneycardoso.taskify.web.service.WebTaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private WebTaskService service;

    @PostMapping("/create")
    public ResponseEntity<RegistrationResponse> register(@RequestBody TaskForm task) {
        try {
            Task savedTask = service.create(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(savedTask));
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegistrationResponse("Erro ao criar a tarefa. Verifique os dados fornecidos."));
        } catch (InternalServerError err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Ocorreu um erro interno ao processar a solicitação."));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<TaskResponse> list() {
        try {
            Iterable<Task> tasks = service.listAll();
            return ResponseEntity.ok(new TaskResponse(tasks));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TaskResponse("Ocorreu um erro ao obter a lista de tarefass."));
        }
    }

    @PutMapping("/{id}/editar")
    public ResponseEntity<RegistrationResponse> editar(@PathVariable Long id,
            @Valid TaskForm form) {
        try {
            service.edit(form, id);
            return ResponseEntity.ok(new RegistrationResponse("Tarefa atualizada com sucesso!"));
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegistrationResponse("Erro ao atualizar a tarefa. Verifique os dados fornecidos."));
        } catch (ServerErrorException err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Ocorreu um erro ao atualizar a tarefa!"));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        try {
            service.deleteTaskById(id);
            return ResponseEntity.ok(new DeleteResponse("Tarefa excluída com sucesso!"));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new DeleteResponse("Ocorreu um erro ao excluir a tarefa!"));
        }
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }
}