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

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("personas", service.listar());
        return "index";
    }
    @GetMapping("/listar/{id}")
    public String listarId(@PathVariable int id, Model model) {
        model.addAttribute("persona", service.listarId(id));
        return "form";
    }

    @GetMapping("/new")
    public String nuevo(Model model) {
        model.addAttribute("persona", new Persona());
        return "form";
    }

    @PostMapping("/save")
    public String save(Persona p, Model model) {
        int id = service.save(p);
        if (id == 0) {
            return "form";
        }
        return "redirect:/listar";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable int id, Model model) {
        service.delete(id);
        return "redirect:/listar";
    }


}
