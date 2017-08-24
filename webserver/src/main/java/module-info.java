module org.detoeuf.hexagonal.webserver {
    exports org.detoeuf.hexagonal.webserver;
    requires org.detoeuf.hexagonal.domain;
    requires spark.core;
    provides org.detoeuf.hexagonal.domain.BlogServer
            with org.detoeuf.hexagonal.webserver.WebServer;
}