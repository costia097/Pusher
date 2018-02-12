package main.controllers;

import main.entities.Person;
import main.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/save")
    public void save() {
        Person person = new Person();
        person.setLogin("a");
        person.setPassword("123");
        personService.savePerson(person);
    }
}
