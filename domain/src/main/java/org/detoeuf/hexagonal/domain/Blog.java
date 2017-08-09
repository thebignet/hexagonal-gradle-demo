package org.detoeuf.hexagonal.domain;

import org.detoeuf.hexagonal.servicelocator.ServiceLocator;

import java.util.Collection;
import java.util.Optional;

public class Blog {
    private final Persistence persistence;
    private final Collection<SocialMedia> socialMedias;

    public Blog() {
        this.persistence = ServiceLocator.lookup(Persistence.class).orElseThrow(RuntimeException::new);
        this.socialMedias = ServiceLocator.collection(SocialMedia.class);
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
