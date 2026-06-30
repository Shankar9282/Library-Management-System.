package com.nt.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nt.demo.Entity.Book;
import com.nt.demo.Reprository.BookRepository;

@Service
public class BookService
{
    @Autowired
    private BookRepository repo;

    public Page<Book> getAllBooks(
    int page,
    String sortField,
    String sortDir)
    {
        Sort sort = sortDir.equalsIgnoreCase("asc")
        ? Sort.by(sortField).ascending()
        : Sort.by(sortField).descending();

        Pageable pageable =
        PageRequest.of(page, 5, sort);

        return repo.findAll(pageable);
    }

    public void saveBook(Book book)
    {
        repo.save(book);
    }

    public Book getBookById(int id)
    {
        return repo.findById(id).get();
    }

    public void deleteBook(int id)
    {
        repo.deleteById(id);
    }
}