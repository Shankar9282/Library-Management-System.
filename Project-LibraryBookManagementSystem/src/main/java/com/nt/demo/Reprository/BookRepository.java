package com.nt.demo.Reprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.demo.Entity.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
