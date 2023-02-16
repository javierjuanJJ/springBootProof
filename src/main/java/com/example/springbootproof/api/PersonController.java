package com.example.springbootproof.api;

import com.example.springbootproof.model.Person;
import com.example.springbootproof.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> selectAllPeople(){
        return personService.selectAllPeople();
    }
    @GetMapping(path = "{id}")
    public Person selectByIdPeople(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deleteByIdPeople(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updateByIdPeople(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Person person){
        personService.updatePerson(id, person);
    }
}
