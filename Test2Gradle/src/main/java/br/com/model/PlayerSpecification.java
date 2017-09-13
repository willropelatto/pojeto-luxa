package br.com.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification implements Specification<PlayerTite> {

	private SearchCriteria criteria;

	public PlayerSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

		switch (criteria.getOperation()) {
		case CONTAINING:
			return cb.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");			
		case GREATER_THAN_EQUALS:
			return cb.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());		
		case LESS_THAN_EQUAL:
			return cb.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
		default:			
			return cb.equal(root.get(criteria.getKey()), criteria.getValue());	
		}
		

	}

}
