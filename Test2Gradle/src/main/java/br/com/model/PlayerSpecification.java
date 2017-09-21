package br.com.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecification implements Specification<PlayerTite> {

	public static Specification<PlayerTite> search(SearchCriteria criteria) {
		return new PlayerSpecification() {	
			@Override
			public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				switch (criteria.getOperation()) {
				case CONTAINING:
					return cb.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
				case GREATER_THAN_EQUALS:
					return cb.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
				case LESS_THAN_EQUAL:
					return cb.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
				default:
					return cb.equal(root.get(criteria.getKey()), criteria.getValue());
				}
			}			
		};
	}

	public static Specification<PlayerTite> whereAttributes(String name) {
		return new PlayerSpecification() {

			@Override
			public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path join = root.join("PlayerAttributes").get("attribute");
				Path field = join.get("name");			
				
				return cb.equal(field, name);
			}
		};
	}
	
	public static Specification<PlayerTite> whereAssociation(int val) {
		return new PlayerSpecification() {

			@Override
			public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path join = root.join("PlayerAttributeAssociation").get("player");
				Path field = join.get("value");
			
				return cb.greaterThanOrEqualTo(field, val);
			}
		};
	}	


	@Override
	public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}
}


