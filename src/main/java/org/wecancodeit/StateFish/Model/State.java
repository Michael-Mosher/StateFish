package org.wecancodeit.StateFish.Model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class State {
  @Id
  @GeneratedValue
  private Long id;
  @Column(name="stateName")
  private String name;
  private String abbreviation;
  private String motto;
  @Embedded
  private Fish fish;
  @OneToMany(mappedBy="enclosingState")
  private Collection<City> cities;

  public State(){}

  public State(String name, String abbreviation, String motto)
  {
    this.name = name;
    this.abbreviation = abbreviation;
    this.motto = motto;
    this.fish = null;
  }

  public State(String name, String abbreviation, String motto, Fish fish)
  {
    this.name = name;
    this.abbreviation = abbreviation;
    this.motto = motto;
    this.fish = fish;
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

  @Override
  public boolean equals(Object otherState)
  {
    if(this == otherState)
      return true;
    if(otherState == null)
      return false;
    if(this.getClass() != otherState.getClass())
      return false;
    State other = (State) otherState;
    if(this.getId() == null){
      if(other.getId() != null) return false;
    } else if(!this.getId().equals(other.getId())){
      return false;
    } else {
      return true;
    }
    return false;
  }

  /**
   * @return the id
   */
  public Long getId()
  {
    return this.id;
  }

  /**
   * @return the name
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * @return the abbreviation
   */
  public String getAbbreviation()
  {
    return this.abbreviation;
  }

  /**
   * @return the motto
   */
  public String getMotto()
  {
    return this.motto;
  }

  /**
   * @return the Fish
   */
  public Fish getFish()
  {
    return this.fish;
  }

  /**
   * @return the cities (Set<City>)
   */
  public Collection<City> getCities()
  {
    return cities;
  }

  public String getCitiesUrl()
  {
    return "/states/" + this.getAbbreviation() + "/cities";
  }

  public String getJson()
  {
    String f;
    return "{name:" + this.getName() + ",abbreviation:" + this.getAbbreviation() + ",motto:"
        + this.getMotto() + ",citiesUrl:"+this.getCitiesUrl()+  ",fish:" + (f = (this.fish!=null) ? fish.getJson() : null) + "}";
  }
} 