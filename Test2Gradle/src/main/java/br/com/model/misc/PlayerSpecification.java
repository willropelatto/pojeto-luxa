package br.com.model.misc;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.model.bean.PlayerMO;

public class PlayerSpecification implements Specification<PlayerMO> {

	public static Specification<PlayerMO> search(SearchCriteria criteria) {
		return new PlayerSpecification() {	
			@Override
			public Predicate toPredicate(Root<PlayerMO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				switch (criteria.getOperation()) {
				case CONTAINING:
					return cb.like(cb.upper(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toUpperCase() + "%");
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

	@Override
	public Predicate toPredicate(Root<PlayerMO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}
}


