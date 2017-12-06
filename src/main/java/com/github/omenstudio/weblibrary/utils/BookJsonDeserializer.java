package com.github.omenstudio.weblibrary.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.omenstudio.weblibrary.entity.Book;
import com.github.omenstudio.weblibrary.repository.AuthorRepository;
import com.github.omenstudio.weblibrary.repository.BookRepository;
import com.github.omenstudio.weblibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class BookJsonDeserializer extends JsonDeserializer<Book> {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Book deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Book book = new Book();

        JsonNode node = jp.getCodec().readTree(jp);

        book.setTitle(getText(node, "title"));
        book.setOriginalTitle(getText(node, "originalTitle"));
        book.setDescription(getText(node, "description"));
        book.setCopyrightYear(getInt(node, "copyrightYear"));
        book.setBookEdition(getInt(node, "bookEdition"));
        book.setNumberOfPages(getInt(node, "numberOfPages"));
        book.setIsbn(getText(node, "isbn"));

        Long authorId = getHypermediaIdHack(node, "author");
        if (authorId != null)
            book.setAuthor(authorRepository.findOne(authorId));

        Long publisherId = getHypermediaIdHack(node, "publisher");
        if (publisherId != null)
            book.setPublisher(publisherRepository.findOne(publisherId));

        return book;
    }

    private static String getText(JsonNode parent, String title) {
        if (parent.has(title) && !parent.get(title).isNull()) {
            String res = parent.get(title).asText();
            if (res.length() != 0)
                return res;
        }
        return null;
    }

    private static Long getLong(JsonNode parent, String title) {
        if (parent.has(title) && !parent.get(title).isNull()) {
            return parent.get(title).asLong();
        }
        return null;
    }

    private static Integer getInt(JsonNode parent, String title) {
        if (parent.has(title) && !parent.get(title).isNull()) {
            return parent.get(title).asInt();
        }
        return null;
    }

    private static Long getHypermediaIdHack(JsonNode parent, String title) {
        if (parent.has(title) && !parent.get(title).isNull()) {
            String linkStr = parent.get(title).asText().trim();

            if (!linkStr.isEmpty()) {
                try {
                    return Long.parseLong(linkStr);
                } catch (NumberFormatException e) {/* do nothing */}

                String[] tokens = linkStr.split("/");
                if (tokens.length > 0) {
                    return Long.parseLong(tokens[tokens.length - 1]);
                }
            }
        }

        return null;
    }
}
