package com.nt.demo.Controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.demo.Entity.Book;
import com.nt.demo.service.BookService;


@Controller
public class BookController
{
    @Autowired
    private BookService service;

    @GetMapping("/")
    public String homePage(
    Model model,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "title") String sortField,
    @RequestParam(defaultValue = "asc") String sortDir)
    {
        Page<Book> bookPage =
        service.getAllBooks(page, sortField, sortDir);

        model.addAttribute("books",
        bookPage.getContent());

        model.addAttribute("currentPage", page);

        model.addAttribute("totalPages",
        bookPage.getTotalPages());

        return "index";
    }

    @GetMapping("/add")
    public String addBook(Model model)
    {
        model.addAttribute("book", new Book());

        return "add-book";
    }

    @PostMapping("/save")
    public String saveBook(Book book)
    {
        service.saveBook(book);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editBook(
    @PathVariable int id,
    Model model)
    {
        model.addAttribute("book",
        service.getBookById(id));

        return "add-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id)
    {
        service.deleteBook(id);

        return "redirect:/";
    }
    
}