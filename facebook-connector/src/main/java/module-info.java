module org.detoeuf.hexagonal.facebook {
    exports org.detoeuf.hexagonal.facebook;
    requires org.detoeuf.hexagonal.domain;
    provides org.detoeuf.hexagonal.domain.SocialMedia
            with org.detoeuf.hexagonal.facebook.FacebookConnector;
}