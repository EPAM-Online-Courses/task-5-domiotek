package efs.task.collections.data;

import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.*;

public class DataProvider {

        public static final String DATA_SEPARATOR = ",";

        public List<Town> getTownsList() {
            List<Town> townList = new ArrayList<>(Data.baseTownsArray.length);

            for (String town : Data.baseTownsArray) {
                String[] sections = town.split(DATA_SEPARATOR);

                for (int i = 0; i < sections.length; i++) {
                    sections[i] = sections[i].replace(" ", "");
                }

                townList.add(new Town(sections[0], new ArrayList<>(Arrays.asList(sections[1], sections[2]))));
            }

            return townList;
        }

        public List<Town> getDLCTownsList() {
            List<Town> townList = new ArrayList<>( Data.dlcTownsArray.length);

            for (String town : Data.dlcTownsArray) {
                String[] sections = town.split(DATA_SEPARATOR);

                for (int i = 0; i < sections.length; i++)
                    sections[i] = sections[i].replace(" ", "");

                townList.add(new Town(sections[0], new ArrayList<>(Arrays.asList(sections[1], sections[2]))));
            }

            return townList;
        }

        public Set<Hero> getHeroesSet() {
            Set<Hero> heroes  = new HashSet<>();

            for (String hero : Data.baseCharactersArray) {
                String[] sections = hero.split(DATA_SEPARATOR);

                for (int i = 0; i < sections.length; i++)
                    sections[i] = sections[i].replace(" ", "");

                heroes.add(new Hero(sections[0],sections[1]));
            }

            return heroes;
        }

        public Set<Hero> getDLCHeroesSet() {
            Set<Hero> heroesDlcSet  = new HashSet<>();

            for (String hero : Data.dlcCharactersArray) {
                String[] sections = hero.split(DATA_SEPARATOR);

                for (int i = 0; i < sections.length; i++)
                    sections[i] = sections[i].replace(" ", "");

                heroesDlcSet.add(new Hero(sections[0],sections[1]));
            }

            return heroesDlcSet;
        }


}
