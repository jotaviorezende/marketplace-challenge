package com.marketplace.marketplacechallenge.model.dto;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * Dto que representa a categoria.
 */
public class CategoryDto {

    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CategoryDto that = (CategoryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override public String toString() {
        return new StringJoiner(", ", CategoryDto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'").toString();
    }
}
