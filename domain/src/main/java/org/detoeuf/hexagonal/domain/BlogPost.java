package org.detoeuf.hexagonal.domain;

public class BlogPost {
    private final String title;
    private final String body;

    public BlogPost(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
