package com.digitalinnovationone.personrestapi.repository;

import com.digitalinnovationone.personrestapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
