package br.com.pofexo.model.repo;

import org.springframework.data.repository.CrudRepository;

import br.com.pofexo.model.bean.MarketMO;

public interface MarketRepo extends CrudRepository<MarketMO, Integer> {

}
