package com.thoughtworks.test.definition.resource;

public class Resource {
    private String name;
    private double price;

    public Resource(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return name != null ? name.equals(resource.name) : resource.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
