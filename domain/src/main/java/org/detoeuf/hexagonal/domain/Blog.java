package org.detoeuf.hexagonal.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Blog {
    private final Persistence persistence;
    private final Collection<SocialMedia> socialMedias;

    public Blog() {
        Iterator<Persistence> persistences = ServiceLoader.load(Persistence.class).iterator();
        Optional<Persistence> persistence = (persistences.hasNext() ? Optional.ofNullable(persistences.next()) : Optional.empty());
        this.persistence = persistence.orElseThrow(RuntimeException::new);
        Iterator<SocialMedia> socialMedias = ServiceLoader.load(SocialMedia.class).iterator();
        this.socialMedias = StreamSupport.stream(Spliterators.spliteratorUnknownSize(socialMedias, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }

    public void post(BlogPost blogPost) {
        persistence.savePost(blogPost);
    }

    public Optional<BlogPost> getPostWithTitle(String title) {
        return persistence.findPostWithTitle(title);
    }

    private Optional<SocialMedia> socialMediasWithName(String name) {
        return socialMedias.stream().filter(socialMedia -> socialMedia.name().equals(name)).findFirst();
    }

    public void share(String title, String socialMediaName) {
        socialMediasWithName(socialMediaName)
                .ifPresent(socialMedia -> getPostWithTitle(title).ifPresent(socialMedia::share));
    }

}
