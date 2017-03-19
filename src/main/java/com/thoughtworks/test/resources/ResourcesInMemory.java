package com.thoughtworks.test.resources;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ResourcesInMemory implements ResourcesRepository {

    private Set<Resource> resources = new HashSet<>();

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public Optional<Resource> getResourceByName(String name) {
        return resources.stream().filter(resource -> resource.getName().equals(name)).findAny();
    }
}
