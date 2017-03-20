package com.thoughtworks.test.definition;

import java.util.Optional;

public interface DefinitionDictionary <T> {

    void addDefinition(T definition);

    Optional<T> getDefinitionByKey(String key);
}
