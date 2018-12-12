package org.wecancodeit.StateFish.Model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<State, Long> {

  Optional<State> findByAbbreviation(String stateAbbreviation);

}
