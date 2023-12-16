package br.com.sidneycardoso.taskify.controller;

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
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.ConstraintViolationException;
import br.com.sidneycardoso.taskify.core.model.Status;
import br.com.sidneycardoso.taskify.core.model.Task;
import br.com.sidneycardoso.taskify.core.repository.TaskRepository;
import br.com.sidneycardoso.taskify.core.response.DeleteResponse;
import br.com.sidneycardoso.taskify.core.response.RegistrationResponse;
import br.com.sidneycardoso.taskify.core.response.TaskResponse;

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

    @GetMapping("/list")
    public ResponseEntity<TaskResponse> list() {
        try {
            Iterable<Task> tasks = repository.findAll();
            return ResponseEntity.ok(new TaskResponse(tasks));
        } catch (ServerErrorException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TaskResponse("Ocorreu um erro ao obter a lista de tarefass."));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        try {
            repository.deleteById(id);
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