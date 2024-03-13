package org.example.quanlisukien.mapper;

import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.request.CategoriesRequest;
import org.example.quanlisukien.data.response.CategoriesResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ICategoriesMapper {

  @Mapping(source = "categoryId", target = "categoryId")
  CategoriesResponse convertEntityCategoriesMapper(
      Categories categories); // convert categories sang categoriesResponse

  @Mapping(target = "dateTime",expression = "java(java.time.LocalDateTime.now())")
  @Mapping(target = "updateTime",expression = "java(java.time.LocalDateTime.now())")
  Categories categoriesMapper(CategoriesRequest categoriesRequest); //map request sang entity
}
