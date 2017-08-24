module org.detoeuf.hexagonal.twitter {
    exports org.detoeuf.hexagonal.twitter;
    requires org.detoeuf.hexagonal.domain;
    provides org.detoeuf.hexagonal.domain.SocialMedia
            with org.detoeuf.hexagonal.twitter.TwitterConnector;
}