package com.bookstore.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class Book {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private Date  publishDate;
    private String publisher;
    private int pages;
    private String description;
    private String website;


    public Book(String isbn, String title, String subTitle, String author,
                Date publishDate, String publisher, int pages,
                String description, String website) {
        this.isbn = isbn;
        this.title = title;
        this.subTitle = subTitle;
        this.author = author;
        this.publishDate = publishDate;
        this.publisher = publisher;
        this.pages = pages;
        this.description = description;
        this.website = website;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date  getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date  publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", description='" + description + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

    public static Book createBookFromResponse(Map<String, Object> bookData) {

        String publishDateString = (String) bookData.get("publish_date");
        Date publishDate = null;

        if (publishDateString != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                publishDate = sdf.parse(publishDateString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Use Optional to safely extract values from the map
        String isbn = Optional.ofNullable((String) bookData.get("isbn")).orElse("");
        String title = Optional.ofNullable((String) bookData.get("title")).orElse("");
        String subTitle = Optional.ofNullable((String) bookData.get("subTitle")).orElse("");
        String author = Optional.ofNullable((String) bookData.get("author")).orElse("");
        String publisher = Optional.ofNullable((String) bookData.get("publisher")).orElse("");
        String description = Optional.ofNullable((String) bookData.get("description")).orElse("");
        String website = Optional.ofNullable((String) bookData.get("website")).orElse("");

        // Use Optional to handle possible null for 'pages' safely
        Integer pages = Optional.ofNullable((Integer) bookData.get("pages")).orElse(0);

        return new Book(
                isbn,
                title,
                subTitle,
                author,
                publishDate,
                publisher,
                pages,
                description,
                website
        );
    }
}
