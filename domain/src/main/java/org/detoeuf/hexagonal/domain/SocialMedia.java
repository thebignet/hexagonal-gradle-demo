package org.detoeuf.hexagonal.domain;

public interface SocialMedia {
    default void share(BlogPost blogPost) {
        System.out.println("Sharing " + blogPost.toString() + " on " + name());
    }

    String name();
}
