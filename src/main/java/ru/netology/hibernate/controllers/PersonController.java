package ru.netology.hibernate.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.hibernate.entities.Person;
import ru.netology.hibernate.repository.DbRepository;

import java.util.List;

@RestController
public class PersonController {

    private final DbRepository repository;

    public PersonController(DbRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = "/persons/by-city")
    public List<Person> getPerson(@RequestParam("city") String city) {
        return repository.getPersonsByCity(city);
    }

    @GetMapping(value = "/create")
    public void createTable() {
        repository.createTablePersons();
    }
}
