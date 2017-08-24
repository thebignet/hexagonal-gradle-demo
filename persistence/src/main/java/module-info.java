module org.detoeuf.hexagonal.persistence {
    exports org.detoeuf.hexagonal.persistence;
    requires org.detoeuf.hexagonal.domain;
    provides org.detoeuf.hexagonal.domain.Persistence
            with org.detoeuf.hexagonal.persistence.InMemoryPersistence;
}