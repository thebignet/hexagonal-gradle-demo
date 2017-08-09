package org.detoeuf.hexagonal.facebook;

import org.detoeuf.hexagonal.domain.SocialMedia;

public class FacebookConnector implements SocialMedia {
    @Override
    public String name() {
        return "Facebook";
    }
}
