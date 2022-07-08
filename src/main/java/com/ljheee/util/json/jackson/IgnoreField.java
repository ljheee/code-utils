package com.ljheee.util.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lijianhua.
 */
public class IgnoreField {


    @Data
    @NoArgsConstructor
    static class Book {
        private Long id;
        private String isbn;
        private String name;

        @JsonIgnore
        private BookRepository repository = new BookRepository();

        @JsonIgnore
        public BookRepository getBookRepository(){
            return new BookRepository();
        }



        public Book(Long id, String isbn, String name) {
            this.id = id;
            this.isbn = isbn;
            this.name = name;
        }
    }


    /**
     * getBookId() 酷似 getter方法，jackson序列化时会被调用
     */
    static class BookRepository {

        public Long getBookId() {
            // require from network
            System.out.println("require from network");
            return ThreadLocalRandom.current().nextLong();
        }
    }

    public static void main(String[] args) throws JsonProcessingException {

        BookRepository repository = new BookRepository();


        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(repository));


        Book book = new Book(1L, "aba", "name");
        String s = mapper.writeValueAsString(book);
        System.out.println(s);

    }
}
