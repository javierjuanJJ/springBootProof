package com.example.springbootproof.api;

import com.example.springbootproof.model.Person;
import com.example.springbootproof.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }

    public List<Person> selectAllPeople(){
        return personService.selectAllPeople();
    }
    @GetMapping(path = "{id}")
    public Person selectByIdPeople(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }
}
