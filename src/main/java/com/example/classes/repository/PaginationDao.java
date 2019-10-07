package com.example.classes.repository;


import com.example.classes.Classes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaginationDao extends PagingAndSortingRepository<Classes, Integer> {

}