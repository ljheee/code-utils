package com.ljheee.util.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lijianhua.
 */
public class CircularReferenceField {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Book {
        private Long id;
        private String isbn;
        private String name;

        // 忽略List<BookComment> 里面的book属性
        @JsonIgnoreProperties("book")
        private List<BookComment> commentList;

    }

    @Data
    @AllArgsConstructor
    static class BookComment {

        private Book book;
        private String comment;
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


        Book book = new Book(1L, "aba", "name", null);
        BookComment comment = new BookComment(book, "good");
        book.setCommentList(Collections.singletonList(comment));

        String s = mapper.writeValueAsString(book);
        System.out.println(s);

    }
}
