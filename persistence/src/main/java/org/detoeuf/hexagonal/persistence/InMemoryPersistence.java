package org.detoeuf.hexagonal.persistence;

import org.detoeuf.hexagonal.domain.BlogPost;
import org.detoeuf.hexagonal.domain.Persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPersistence implements Persistence {

    private Map<String, String> posts = new HashMap<>();

    @Override
    public void savePost(BlogPost blogPost) {
        posts.put(blogPost.getTitle(), blogPost.getBody());
    }

    @Override
    public Optional<BlogPost> findPostWithTitle(String title) {
        return Optional.ofNullable(posts.getOrDefault(title, null))
                .map(body -> new BlogPost(title, body));
    }
}
