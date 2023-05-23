package com.vodafone.demo.repository;

import com.vodafone.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//PagingAndSortingRepository
public interface AuthorRepository extends  JpaRepository<Author, Integer> {
}