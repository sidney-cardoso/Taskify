package br.com.sidneycardoso.taskify.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.sidneycardoso.taskify.model.User;
import br.com.sidneycardoso.taskify.repository.UserRepository;
import org.springframework.ui.ModelMap;

import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public ModelAndView registrationForm() {
        ModelAndView modelAndView = new ModelAndView("/users/register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/users")
    public ResponseEntity<?> registerUser(@ModelAttribute("user") User user, ModelMap model) {
        try {
            User savedUser = repository.save(user);
            model.addAttribute("successMessage", "Usuário cadastrado com sucesso!");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (DataIntegrityViolationException | ConstraintViolationException err) {
            model.addAttribute("errorMessage", "Erro ao cadastrar o usuário. Verifique os dados fornecidos.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro ao cadastrar o usuário. Verifique os dados fornecidos.");
        } catch (InternalError err) {
            model.addAttribute("errorMessage", "Ocorreu um erro interno ao processar a solicitação.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro interno ao processar a solicitação.");
        }
    }
}
