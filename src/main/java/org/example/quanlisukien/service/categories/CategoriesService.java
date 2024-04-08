package org.example.quanlisukien.service.categories;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.request.CategoriesRequest;
import org.example.quanlisukien.data.response.CategoriesResponse;

public interface CategoriesService {

  Categories createCategories(CategoriesRequest categoriesRequest); //method them danh muc moi

  List<CategoriesResponse> findByAllCategories() throws JsonProcessingException;//hien thi danh sach danh muc

  void deleteByIdCategories(Long categoryId);//method xoa theo id

  Categories updateByIdCategories(Long categoryId , CategoriesRequest categoriesRequest); //update categories bang id
}
