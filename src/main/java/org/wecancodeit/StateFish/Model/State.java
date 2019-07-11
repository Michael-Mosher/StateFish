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
  private String name = "";
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
    State other = (State) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  /**
   * @return the id
   */
  public Long getId()
  {
    return id;
  }

  /**
   * @return the name
   */
  public String getName()
  {
    return name;
  }

  /**
   * @return the abbreviation
   */
  public String getAbbreviation()
  {
    return abbreviation;
  }

  /**
   * @return the motto
   */
  public String getMotto()
  {
    return motto;
  }

  /**
   * @return the fish
   */
  public Fish getFish()
  {
    return fish;
  }

  /**
   * @return the cities
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
    /**
     *   private String name;
  private String citiesUrl;
  private String abbreviation;
  private String motto;
  @Embedded
  private Fish fish;
  @OneToMany(mappedBy="enclosingState")
     */
    System.out.println("State.getJson is this null: " + (this instanceof State));
    String f, n;
    //f = IsNull(this)
    return "{name:" + (n = (this.getName()!=null) ? this.getName() : "") + ",abbreviation:"
        + this.getAbbreviation() + ",motto:"
        + this.getMotto() + ",citiesUrl:"+this.getCitiesUrl()
        +  ",fish:" + (f = (this.fish!=null) ? fish.getJson() : null) + "}";
  }
}