package com.ljheee.util.json.jackson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author lijianhua.
 */
public class JacksonInternal {


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Book {
        private Long id;
        private String isbn;
        private String name;
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

    public static void main(String[] args) {

        BookRepository repository = new BookRepository();
        JsonTool.writeToString(repository);


        Book book = new Book(1L, "aba", "name");
        System.out.println(JsonTool.writeToString(book));

    }
}
