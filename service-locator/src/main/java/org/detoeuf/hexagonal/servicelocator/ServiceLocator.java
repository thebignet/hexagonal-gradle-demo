package org.detoeuf.hexagonal.servicelocator;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceLocator {
    public static <T> Optional<T> lookup(Class<T> clazz) {
        Iterator<T> iterator = ServiceLoader.load(clazz).iterator();
        return iterator.hasNext() ? Optional.ofNullable(iterator.next()) : Optional.empty();
    }

    public static <T> Collection<T> collection(Class<T> clazz) {
        Iterator<T> iterator = ServiceLoader.load(clazz).iterator();
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .collect(Collectors.toList());
    }
}
