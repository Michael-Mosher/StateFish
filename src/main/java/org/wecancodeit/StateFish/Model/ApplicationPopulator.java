package org.wecancodeit.StateFish.Model;

import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
@Component
public class ApplicationPopulator implements CommandLineRunner {
  @Resource
  private StateRepository stateRepo;
  @Resource
  private CityRepository cityRepo;
  @Override
  public void run(String ...args) throws Exception
  {
    State ohio = stateRepo.save(new State("Ohio", "OH", "Birthplace of Aviation", null));
    State hawaii = stateRepo.save(
        new State(
            "Hawai'i", "HI", "The Islands of Aloha",
            new Fish(
                "Humuhumunukunukuāpuaʻa",
                "Rhinecanthus rectangulus",
                "https://upload.wikimedia.org/wikipedia/commons/5/52/Reef_Triggerfish_1.JPG")
            )
        );
    City honolulu = cityRepo.save(new City("Honolulu", 337256L, hawaii));
    City waipahu = cityRepo.save(new City("Waipahu", 38216L, hawaii));
    City wailuku = cityRepo.save(new City("Wailuku", 15313, hawaii));
  }
}
