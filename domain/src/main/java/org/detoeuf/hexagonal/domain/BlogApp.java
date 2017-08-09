package org.detoeuf.hexagonal.domain;


import org.detoeuf.hexagonal.servicelocator.ServiceLocator;

public class BlogApp {
    public static void main(String[] args) throws Exception {
        ServiceLocator.lookup(BlogServer.class).ifPresent(BlogServer::start);
    }
}
