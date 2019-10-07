package com.example.classes.repository;

import com.example.classes.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    @Query(
            value = "SELECT * FROM class c WHERE c.name = ?1",
            nativeQuery = true)
    ArrayList<Classes> findAllByName(String name);
}