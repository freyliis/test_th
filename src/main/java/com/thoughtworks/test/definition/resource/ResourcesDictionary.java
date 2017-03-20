package com.thoughtworks.test.definition.resource;

import com.thoughtworks.test.definition.DefinitionDictionary;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ResourcesDictionary implements DefinitionDictionary<Resource> {

    private Set<Resource> resources = new HashSet<>();

    public void addDefinition(Resource resource) {
        resources.add(resource);
    }

    public Optional<Resource> getDefinitionByKey(String name) {
        return resources.stream().filter(resource -> resource.getName().equals(name)).findAny();
    }
}
