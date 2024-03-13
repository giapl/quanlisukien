package org.example.quanlisukien.service.categories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.request.CategoriesRequest;
import org.example.quanlisukien.data.response.CategoriesResponse;
import org.example.quanlisukien.exception.InternalServerException;
import org.example.quanlisukien.exception.NotFoundException;
import org.example.quanlisukien.mapper.ICategoriesMapper;
import org.example.quanlisukien.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

  private final CategoriesRepository categoriesRepository;
  private final ICategoriesMapper categoriesMapper;

  @Autowired
  public CategoriesServiceImpl(CategoriesRepository categoriesRepository,
      ICategoriesMapper categoriesMapper) {
    this.categoriesRepository = categoriesRepository;
    this.categoriesMapper = categoriesMapper;
  }

  @Override
  public Categories createCategories(CategoriesRequest categoriesRequest) {
    Categories categories = categoriesMapper.categoriesMapper(categoriesRequest);
    try {
      return categoriesRepository.save(categories);
    } catch (DataAccessException ex) {
      throw new InternalServerException("error save database");
    }
  }

  @Override
  public List<CategoriesResponse> findByAllCategories() {
    return categoriesRepository.findAll()
        .stream()
        .map(categoriesMapper::convertEntityCategoriesMapper)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteByIdCategories(Long categoryId) {
    if (categoriesRepository.existsById(categoryId)) {
      categoriesRepository.deleteById(categoryId);
    } else {
      throw new NotFoundException("no id delete categories admin");
    }
  }

  @Override
  public Categories updateByIdCategories(Long categoryId, CategoriesRequest categoriesRequest) {
    Optional<Categories> optionalCategories = categoriesRepository.findById(categoryId);
    if (optionalCategories.isPresent()) {
      Categories categories = optionalCategories.get();
      if (categoriesRequest.getName() != null) {
        categories.setName(categoriesRequest.getName());
      }
      if (categoriesRequest.getDescription() != null) {
        categories.setDescription(categoriesRequest.getDescription());
      }
      categories.setUpdateTime(LocalDateTime.now());
      return categoriesRepository.save(categories);
    } else {
      throw new NotFoundException("no id update categories database admin");
    }
  }
}
