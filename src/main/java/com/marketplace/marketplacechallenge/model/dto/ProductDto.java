package com.marketplace.marketplacechallenge.model.dto;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Dto que representa o produto.
 */
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private Date creationDate;
    private CategoryDto categoryDto;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String description, Date creationDate, CategoryDto categoryDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.categoryDto = categoryDto;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(description, that.description)
                && Objects.equals(creationDate, that.creationDate)
                && Objects.equals(categoryDto, that.categoryDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, creationDate, categoryDto);
    }

    @Override public String toString() {
        return new StringJoiner(", ", ProductDto.class.getSimpleName() + "[", "]")
                .add("id=" + id).add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("creationDate=" + creationDate)
                .add("categoryDto=" + categoryDto).toString();
    }
}
