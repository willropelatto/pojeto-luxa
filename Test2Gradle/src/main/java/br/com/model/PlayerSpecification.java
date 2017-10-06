package br.com.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

//	public static Specification<PlayerTite> whereAttributes(String name) {
//		return new PlayerSpecification() {
//
//			@Override
//			public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				Path join = root.join("PlayerAttributes").get("attribute");
//				Path field = join.get("name");		
//
////		        FetchParent<Categoria, Mercadoria> fetch = root.fetch("mercadorias");
////		        Join<Categoria, Mercadoria> join = 
////		          (Join<Categoria, Mercadoria>) fetch; //truque
////		 
////		        return builder.between(join.get("preco"),
////		          precoDe, precoAte);	
////		        
////	        	Query consulta = entityManager .createQuery( "SELECT ctrl FROM ControleCartaProtocolada ctrl INNER JOIN ctrl.usuario u INNER JOIN ctrl.condomino c where ctrl.statusCarta = :status and c.nome like :name") .setParameter("status", statusCarta) .setParameter("name", nomeCondomino); controleCartaProtocolada = (ControleCartaProtocolada) consulta.getSingleResult()		        
//				
//				return cb.equal(field, name);
//			}
//		};
//	}
//	
//	public static Specification<PlayerTite> whereAssociation(int val) {
//		return new PlayerSpecification() {
//
//			@Override
//			public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//				Path join = root.join("PlayerAttributeAssociation").get("player");
//				Path field = join.get("value");
//			
//				return cb.greaterThanOrEqualTo(field, val);
//			}
//		};
//	}	


	@Override
	public Predicate toPredicate(Root<PlayerTite> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		// TODO Auto-generated method stub
		return null;
	}
}


