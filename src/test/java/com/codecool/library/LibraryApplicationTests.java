package com.codecool.library;

import com.codecool.library.testmodel.Book;
import com.codecool.library.testmodel.Writer;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LibraryApplicationTests {

    @LocalServerPort
    private Integer port;

    private String BASE_URL;

    private static TestRestTemplate restTemplate = new TestRestTemplate();

    private static List<Book> books = new ArrayList<>(){{
        add(new Book("Harry Potter and the Sorcerer's Stone","J.K. Rowling","fantasy",10,7));
        add(new Book("Harry Potter and the Chamber of Secrets","J.K. Rowling","fantasy",12,12));
        add(new Book("Harry Potter and the Prisoner of Azkaban","J.K. Rowling","fantasy",3,3));
        add(new Book("The Hobbit","J. R. R. Tolkien","fantasy",30,3));
        add(new Book("The Hitchhiker's Guide to the Galaxy","Douglas Adams","comedy",42,42));
        add(new Book("Foundation","Isaac Asimov","sci-fy",5,3));
    }};

    private static List<Writer> writers = new ArrayList<>(){{
        add(new Writer("J.K. Rowling","UK"));
        add(new Writer("J. R. R. Tolkien","UK"));
        add(new Writer("Douglas Adams","UK"));
        add(new Writer("Isaac Asimov","US"));
    }};

    @BeforeEach
    public void init(){
        BASE_URL = "http://localhost:" + port;
        for (Book book:books) {
            HttpEntity<Book> request = new HttpEntity<>(book);
            restTemplate.postForObject(BASE_URL + "/book", request, Book.class);
        }
        for (Writer writer: writers) {
            HttpEntity<Writer> request = new HttpEntity<>(writer);
            restTemplate.postForObject( BASE_URL +"/writer", request, Writer.class);
        }
    }

    @Test
    public void testGetNumOfBookByGenre_fantasy(){
        long expected = 4;
        long actual = restTemplate.getForObject(BASE_URL + "/book?genre=fantasy", Long.class);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testGetBooksByWriterBirthPlace_US(){
        List<Book> expected = new ArrayList<>(){{
            add(new Book("Foundation","Isaac Asimov","sci-fy",5,3));
        }};
        ResponseEntity<Book[]> responseEntity =  restTemplate.getForEntity(BASE_URL + "/book/byWriter?writersBirthPlace=US", Book[].class);
        List<Book> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void testgetAllBorrowed(){
        List<String> expected = new ArrayList<>(){{
            add("Harry Potter and the Chamber of Secrets");
            add("Harry Potter and the Prisoner of Azkaban");
            add("The Hitchhiker's Guide to the Galaxy");
        }};
        ResponseEntity<String[]> responseEntity = restTemplate.getForEntity(BASE_URL + "/book/allBorrowed", String[].class);
        List<String> actual = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(expected,actual);
    }
}
