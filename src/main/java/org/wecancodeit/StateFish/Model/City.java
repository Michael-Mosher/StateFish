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
  
  /**
   * @return the id
   */
  public Long getId()
  {
    return id;
  }
  /**
   * @return the enclosingState
   */
  public State getEnclosingState()
  {
    return enclosingState;
  }
  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }
  /**
   * @return the population
   */
  public long getPopulation()
  {
    return population;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    City other = (City) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  public String toJson()
  {
    return "{name:" + this.getName() + ",population:" + this.getPopulation() + "}";
  }
}
