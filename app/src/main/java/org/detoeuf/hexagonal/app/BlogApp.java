package org.detoeuf.hexagonal.app;


import org.detoeuf.hexagonal.domain.BlogServer;

import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;

public class BlogApp {
    public static void main(String[] args) throws Exception {
        Iterator<BlogServer> iterator = ServiceLoader.load(BlogServer.class).iterator();
        Optional<BlogServer> blogServer = (iterator.hasNext() ? Optional.ofNullable(iterator.next()) : Optional.empty());
        blogServer.ifPresent(BlogServer::start);
    }
}
