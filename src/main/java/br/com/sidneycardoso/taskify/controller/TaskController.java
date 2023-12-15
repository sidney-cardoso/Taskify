package br.com.sidneycardoso.taskify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.ConstraintViolationException;

import br.com.sidneycardoso.taskify.model.Status;
import br.com.sidneycardoso.taskify.model.Task;
import br.com.sidneycardoso.taskify.repository.TaskRepository;
import br.com.sidneycardoso.taskify.response.RegistrationResponse;
import br.com.sidneycardoso.taskify.response.TaskResponse;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @PostMapping("/create")
    public ResponseEntity<RegistrationResponse> register(@RequestBody Task task) {
        try {
            Task savedUser = repository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(new RegistrationResponse(savedUser));
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RegistrationResponse("Erro ao criar a tarefa. Verifique os dados fornecidos."));
        } catch (Exception err) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RegistrationResponse("Ocorreu um erro interno ao processar a solicitação."));
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<TaskResponse> listar() {
        try {
            Iterable<Task> tasks = repository.findAll();
            return ResponseEntity.ok(new TaskResponse(tasks));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TaskResponse("Ocorreu um erro ao obter a lista de tarefass."));
        }
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }
}