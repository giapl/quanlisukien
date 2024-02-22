package org.example.quanlisukien.controller.categories;

import org.example.quanlisukien.data.request.CategoriesRequest;
import org.example.quanlisukien.service.categories.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoriesController {

  private final CategoriesService categoriesService;

  @Autowired
  public CategoriesController(CategoriesService categoriesService) {
    this.categoriesService = categoriesService;
  }

  @PostMapping("/create")
  public ResponseEntity<?> createCategories(@RequestBody CategoriesRequest categoriesRequest) {
    return ResponseEntity.ok(categoriesService.createCategories(categoriesRequest));
  }

  @GetMapping("/all")
  public ResponseEntity<?> findByAllCategories() {
    return ResponseEntity.ok(categoriesService.findByAllCategories());
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteByIdCategories(@RequestParam Long category_id) {
    categoriesService.deleteByIdCategories(category_id);
    return ResponseEntity.ok("delete By id successful");
  }

  @PutMapping("/update")
  public ResponseEntity<?> updateByIdCategories(@RequestParam Long category_id,
      @RequestBody CategoriesRequest categoriesRequest) {
    return ResponseEntity.ok(
        categoriesService.updateByIdCategories(category_id, categoriesRequest));
  }
}
