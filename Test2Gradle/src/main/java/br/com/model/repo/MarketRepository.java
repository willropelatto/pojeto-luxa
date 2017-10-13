package br.com.model.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.model.bean.MarketMO;

public interface MarketRepository extends CrudRepository<MarketMO, Integer> {

}
