package org.detoeuf.hexagonal.domain;

import java.util.Optional;

public interface Persistence {
    void savePost(BlogPost blogPost);

    Optional<BlogPost> findPostWithTitle(String title);
}
