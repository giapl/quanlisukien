package org.example.quanlisukien.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.example.quanlisukien.data.entity.Categories;
import org.example.quanlisukien.data.entity.Events;
import org.example.quanlisukien.data.entity.Locations;
import org.example.quanlisukien.data.request.EventSearchRequest;
import org.springframework.data.jpa.domain.Specification;

public class EventSpecification implements Specification<Events> {

  private final EventSearchRequest search;

  public EventSpecification(EventSearchRequest search) {
    this.search = search;
  }

  @Override
  public Predicate toPredicate(Root<Events> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {

    List<Predicate> predicates = new ArrayList<>();

    Join<Events, Categories> eventJoinCategories = root.join("categories", JoinType.INNER);
    Join<Events, Locations>  eventsLocationsJoin = root.join("locations",JoinType.INNER);

    if(search.getNameEvent() !=null && !search.getNameEvent().isEmpty()) {
      predicates.add(
          criteriaBuilder.like(root.get("nameEvent"), "%"+ search.getNameEvent()+"%"));
    }

    if(search.getNameCategory() !=null && !search.getNameCategory().isEmpty()) {
      predicates.add(criteriaBuilder.like(eventJoinCategories.get("name"),search.getNameCategory()));
    }

    if (search.getNameLocation() != null && !search.getNameLocation().isEmpty()) {
      predicates.add(criteriaBuilder.like(eventsLocationsJoin.get("name"), search.getNameLocation()));
    }

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}
