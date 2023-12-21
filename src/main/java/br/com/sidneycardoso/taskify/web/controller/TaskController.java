package br.com.sidneycardoso.taskify.web.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import br.com.sidneycardoso.taskify.core.model.Status;
import br.com.sidneycardoso.taskify.web.dto.FlashMessage;
import br.com.sidneycardoso.taskify.web.dto.TaskForm;
import br.com.sidneycardoso.taskify.web.service.WebTaskService;

@Controller
@RequestMapping("/admin/tasks")
public class TaskController {

    @Autowired
    private WebTaskService service;

    @GetMapping
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("admin/tasks/list");
        modelAndView.addObject("tasks", service.listAll());
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("admin/tasks/create-form");
        modelAndView.addObject("createForm", new TaskForm());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("createForm") TaskForm createForm,
            BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/tasks/create-form");
            modelAndView.addObject("createForm", createForm);
            return modelAndView;
        }

        service.create(createForm);

        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Tarefa criada com sucesso!"));

        return new ModelAndView("redirect:/admin/tasks");
    }

    @PostMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id,
            @Valid @ModelAttribute("registerForm") TaskForm form,
            BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/tasks/create-form");
            modelAndView.addObject("form", form);
            return modelAndView;
        }

        service.edit(form, id);

        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Tarefa atualizada com sucesso!"));

        return new ModelAndView("redirect:/admin/tasks");

    }

    @GetMapping("/{id}/delete")
    public RedirectView delete(@PathVariable Long id, RedirectAttributes attributes) {
        service.deleteTaskById(id);
        attributes.addFlashAttribute("alert", new FlashMessage("alert-success", "Tarefa excluida com sucesso!"));

        return new RedirectView("/admin/tasks");
    }

    @ModelAttribute("status")
    public Status[] getStatus() {
        return Status.values();
    }
}