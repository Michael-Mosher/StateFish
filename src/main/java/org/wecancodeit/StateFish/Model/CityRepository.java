package org.wecancodeit.StateFish.Model;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long>{

  Collection<City> findByEnclosingStateId(Long id);
  
}
