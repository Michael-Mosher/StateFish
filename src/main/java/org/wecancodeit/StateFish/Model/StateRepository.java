package org.wecancodeit.StateFish.Model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.wecancodeit.StateFish.Model.State;

public interface StateRepository extends CrudRepository<State, Long>
{
  Optional<State> findByAbbreviation(String stateAbbreviation);
  Optional<State> findByName(String name);
}