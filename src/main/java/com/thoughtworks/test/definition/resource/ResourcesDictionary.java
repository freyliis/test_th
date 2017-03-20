package com.thoughtworks.test.definition.resource;

import com.thoughtworks.test.definition.DefinitionDictionary;

import java.util.*;

public class ResourcesDictionary implements DefinitionDictionary<Resource> {

    private Set<Resource> resources = new HashSet<>();

    public void addDefinition(Resource resource) {
        resources.add(resource);
    }

    public Optional<Resource> getDefinitionByKey(String name) {
        return resources.stream().filter(resource -> resource.getName().equals(name)).findAny();
    }

    @Override
    public List<Resource> parseInput(String[] input) {
        List<Resource> resources = new ArrayList<>();
        for (String possibleNumber : input) {
            Optional<Resource> resource = this.getDefinitionByKey(possibleNumber);
            if (resource.isPresent()) {
                resources.add(resource.get());
            } else {
                break;
            }
        }
        return resources;
    }
}
