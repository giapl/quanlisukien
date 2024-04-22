package org.example.quanlisukien.service.categories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

  private final CategoriesRepository categoriesRepository;
  private final ICategoriesMapper categoriesMapper;

  private final ObjectMapper objectMapper;

  private final RedisTemplate redisTemplate;

  @Autowired
  public CategoriesServiceImpl(CategoriesRepository categoriesRepository,
      ICategoriesMapper categoriesMapper,
      ObjectMapper objectMapper, RedisTemplate redisTemplate) {
    this.categoriesRepository = categoriesRepository;
    this.categoriesMapper = categoriesMapper;
    this.objectMapper = objectMapper;
    this.redisTemplate = redisTemplate;
  }

  @Override
  public Categories createCategories(CategoriesRequest categoriesRequest) {
    Categories categories = categoriesMapper.categoriesMapper(categoriesRequest);
    try {
      redisTemplate.delete("categoriesAll");
      return categoriesRepository.save(categories);
    } catch (DataAccessException ex) {
      throw new InternalServerException("error save database");
    }
  }

  @Override
  public List<CategoriesResponse> findByAllCategories() throws JsonProcessingException {
    String categoriesAsString = (String) redisTemplate.opsForValue().get("categoriesAll");

    // Cache hit
    if (categoriesAsString != null) {
      TypeReference<List<CategoriesResponse>> mapType = new TypeReference<>() {
      };
      List<CategoriesResponse> categoriesList = objectMapper.readValue(categoriesAsString, mapType);
      return categoriesList;
    }

    // Cache miss
    List<Categories> categoriesList = categoriesRepository.findAll();
    List<CategoriesResponse> categoriesResponses = categoriesList.stream()
        .map(categoriesMapper::convertEntityCategoriesMapper)
        .collect(Collectors.toList());

    String categoriesAsJsonString = objectMapper.writeValueAsString(categoriesResponses);
    // luu cache vao redis
    redisTemplate.opsForValue()
        .set("categoriesAll", categoriesAsJsonString, Duration.ofSeconds(60));

    return categoriesResponses;
  }


  @Override
  public void deleteByIdCategories(Long categoryId) {
    if (categoriesRepository.existsById(categoryId)) {
      redisTemplate.delete("categoriesAll");
      categoriesRepository.deleteById(categoryId);
    } else {
      throw new NotFoundException("no id delete categories admin");
    }
  }

  @Override
  public Categories updateByIdCategories(Long categoryId, CategoriesRequest categoriesRequest) {
    Categories categories = categoriesRepository.findById(categoryId)
        .orElseThrow(() -> new NotFoundException("No ID Categories"));
    if (categoriesRequest.getName() != null && !categoriesRequest.getName().isEmpty()) {
      categories.setName(categoriesRequest.getName());
      categories.setUpdateTime(LocalDateTime.now());
    }
    if (categoriesRequest.getDescription() != null) {
      categories.setDescription(categoriesRequest.getDescription());
      categories.setUpdateTime(LocalDateTime.now());
    }
    redisTemplate.delete("categoriesAll");
    return categoriesRepository.save(categories);
  }
}
