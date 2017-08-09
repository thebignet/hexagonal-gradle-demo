package org.detoeuf.hexagonal.twitter;

import org.detoeuf.hexagonal.domain.SocialMedia;

public class TwitterConnector implements SocialMedia {
    @Override
    public String name() {
        return "Twitter";
    }
}
