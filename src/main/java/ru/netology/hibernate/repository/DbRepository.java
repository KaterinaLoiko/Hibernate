package ru.netology.hibernate.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.hibernate.entities.Person;
import ru.netology.hibernate.entities.Persons;

import java.util.List;

@Repository
public class DbRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<?> getPersonsByCity(String city) {
        return entityManager.createQuery("SELECT person, phoneNumber from Persons where cityOfLiving = ?1")
                .setParameter(1, city)
                .getResultList();
    }

    @Transactional
    public void createTablePersons() {
        List<Persons> persons = List.of(
                Persons.builder()
                        .person(new Person("Alexey", "Ivanov", 38))
                        .cityOfLiving("Saint-Petersburg")
                        .phoneNumber("5674488459")
                        .build(),
                Persons.builder()
                        .person(new Person("Alexey", "Morozov", 44))
                        .cityOfLiving("Moscow")
                        .phoneNumber("5678488456")
                        .build(),
                Persons.builder()
                        .person(new Person("Alexey", "Morozov", 54))
                        .cityOfLiving("Moscow")
                        .phoneNumber("5678488486")
                        .build()
        );

        persons.forEach(p -> {
            if (entityManager.find(Persons.class, p.getPerson()) == null) {
                entityManager.persist(p);
            }
        });
    }
}
