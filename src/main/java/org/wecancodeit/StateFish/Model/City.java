package org.wecancodeit.StateFish.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class City {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  private State enclosingState;
  private String name;
  private long population;

  public City(){}

  public City(String name, long populationQty, State owningState)
  {
    this.name = name;
    this.population = populationQty;
    this.enclosingState = owningState;
  }


  public Long getId()
  {
    return this.id;
  }

  public State getEnclosingState()
  {
    return this.enclosingState;
  }

  public String getName()
  {
    return this.name;
  }

  public long getPopulation()
  {
    return this.population;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
    return result;
  }

  @Override
  public boolean equals(Object otherCity)
  {
    if (this == otherCity) return true;
    if (otherCity == null) return false;
    if (this.getClass() != otherCity.getClass()){
      return false;
    }
    City other = (City) otherCity;
    if (this.getId() == null) {
      if (other.getId() != null)
        return false;
    } else if (!this.getId().equals(other.getId())) return false;
    return true;
  }
}