package com.thoughtworks.test.resources;

import java.util.Optional;

public interface ResourcesRepository {

    void addResource(Resource resource);

    Optional<Resource> getResourceByName(String name);
}
