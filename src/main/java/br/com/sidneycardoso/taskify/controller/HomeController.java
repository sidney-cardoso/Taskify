package br.com.sidneycardoso.taskify.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.sidneycardoso.taskify.dto.TaskForm;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("userName", "Pessoa");
        return "home";
    }

    @GetMapping("/task-form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView("task-form");
        modelAndView.addObject("task-form", new TaskForm());
        return modelAndView;
    }

}
