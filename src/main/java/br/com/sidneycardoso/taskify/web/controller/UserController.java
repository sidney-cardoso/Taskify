package br.com.sidneycardoso.taskify.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.validation.Valid;
import br.com.sidneycardoso.taskify.web.dto.FlashMessage;
import br.com.sidneycardoso.taskify.web.dto.UserForm;
import br.com.sidneycardoso.taskify.web.service.WebUserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private WebUserService service;

    @GetMapping
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("admin/users/list");
        modelAndView.addObject("users", service.listAll());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("admin/users/register-form");
        modelAndView.addObject("registerForm", new UserForm());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("registerForm") UserForm registerForm,
            BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/users/register-form");
            modelAndView.addObject("form", registerForm);
            return modelAndView;
        }

        service.create(registerForm);

        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário cadastrado com sucesso!"));

        return new ModelAndView("redirect:/admin/users");
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/users/register-form");

        modelAndView.addObject("registerForm", service.findUserById(id));

        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView editar(@PathVariable Long id, @Valid @ModelAttribute("registerForm") UserForm form,
            BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/users/register-form");
            modelAndView.addObject("form", form); // Adicione o objeto form novamente
            return modelAndView;
        }

        service.edit(form, id);

        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário editado com sucesso!"));

        return new ModelAndView("redirect:/admin/users");
    }

    @GetMapping("/{id}/delete")
    public RedirectView excluir(@PathVariable Long id, RedirectAttributes attributes) {
        service.deleteUserById(id);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuário excluido com sucesso!"));

        return new RedirectView("/admin/users");
    }
}