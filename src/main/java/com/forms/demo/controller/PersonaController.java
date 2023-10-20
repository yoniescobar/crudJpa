package com.forms.demo.controller;

import com.forms.demo.modelo.Persona;
import com.forms.demo.serviceInterface.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PersonaController {

    @Autowired
    private IPersonaService service;

    @GetMapping({"/","/listar"}) // http://localhost:8080/listar
    public String listar(Model model) {
        model.addAttribute("personas", service.listar());
        return "index.html";
    }
    @GetMapping("/listar/{id}")
    public String listarId(@PathVariable int id, Model model) {
        model.addAttribute("persona", service.listarId(id));
        return "form.html";
    }

    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("persona", new Persona());
        return "form.html";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("persona", service.listarId(id));
        return "form.html";
    }
    @PostMapping("/save")
    public String save(Persona p, Model model) {
        int id = service.save(p); // si es 0 es porque no se guardo y si es 1 es porque se guardo
        if (id == 0) {
            return "form.html";
        }
        return "redirect:/listar";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        service.delete(id);
        return "redirect:/listar";
    }

}
