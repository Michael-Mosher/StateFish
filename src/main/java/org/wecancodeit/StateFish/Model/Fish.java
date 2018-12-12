package org.wecancodeit.StateFish.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Fish {
  @Column(name="fishName")
  private String name;
  private String binomialNomenclature;
  private String imgUrl;
  
  public Fish(){}
  
  public Fish(String name, String zoologicalName, String imgUrl)
  {
    this.name = name;
    this.binomialNomenclature = zoologicalName;
    this.imgUrl = imgUrl;
  }
  /**
   * The Fish's (collequial) name
   * @return String
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * @return the binomialNomenclature
   */
  public String getBinomialNomenclature()
  {
    return binomialNomenclature;
  }
  /**
   * @return the imgUrl
   */
  public String getImgUrl()
  {
    return imgUrl;
  }
  
  public String getJson()
  {
    return "{name:" + this.getName() + ",binomialNomenclature:" + this.getBinomialNomenclature()
        + ",imageUrl:" + this.getImgUrl() + "}";
  }
}
