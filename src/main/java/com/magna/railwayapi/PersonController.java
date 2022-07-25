package com.magna.railwayapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Value("${my_environment_property}")
    private String environmentVar;

    private List<Person> persons;

    @PostConstruct
    public void init() {
        persons = new ArrayList<>();
        persons.add(new Person("Misa", 27));
        persons.add(new Person("Tom", 26));
        persons.add(new Person("Clau", 15));
    }

    @GetMapping("/environment")
    public String getEnvironment() {
        return environmentVar;
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return persons;
    }

    @GetMapping("/persons/{name}")
    public Optional<Person> getPerson(@PathVariable String name) {
        return persons.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @PostMapping("/persons")
    public Person createPerson(@RequestBody Person person) {
        persons.add(person);
        return person;
    }
}
