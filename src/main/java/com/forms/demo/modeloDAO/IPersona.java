package com.forms.demo.modeloDAO;

import com.forms.demo.modelo.Persona;
import org.springframework.data.repository.CrudRepository;

public interface IPersona extends CrudRepository<Persona, Integer> {
}
