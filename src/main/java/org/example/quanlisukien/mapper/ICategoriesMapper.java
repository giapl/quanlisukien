package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.response.CategoriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoriesMapper {

  @Mapping(source = "category_id" , target = "category_id")
  CategoriesResponse convertEntityCategoriesMapper(Categories categories); // convert categories sang categoriesResponse
}
