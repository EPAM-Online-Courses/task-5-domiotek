package efs.task.collections.game;

import efs.task.collections.data.DataProvider;
import efs.task.collections.entity.Hero;
import efs.task.collections.entity.Town;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;
public class GameLobby {

    public static final String HERO_NOT_FOUND = "Nie ma takiego bohatera ";
    public static final String NO_SUCH_TOWN = "Nie ma takiego miasta ";

    private final DataProvider dataProvider;
    private Map<Town, List<Hero>> playableTownsWithHeroesList;

    public GameLobby() {
        this.dataProvider = new DataProvider();
        this.playableTownsWithHeroesList =
                mapHeroesToStartingTowns(dataProvider.getTownsList(), dataProvider.getHeroesSet());
    }

    public Map<Town, List<Hero>> getPlayableTownsWithHeroesList() {
        return playableTownsWithHeroesList;
    }

    public void enableDLC() {
        List<Hero> heroList = List.copyOf(dataProvider.getDLCHeroesSet());
        for(Town town: dataProvider.getDLCTownsList()){
            if(!playableTownsWithHeroesList.containsKey(town)){
                playableTownsWithHeroesList.put(town,heroList);
            }
        }
    }
    public void disableDLC() {
        for(Town town: dataProvider.getDLCTownsList()){
            if(playableTownsWithHeroesList.containsKey(town)) {
                playableTownsWithHeroesList.remove(town);
            }
        }
    }

    public List<Hero> getHeroesFromTown(Town town) {
        if(playableTownsWithHeroesList.containsKey(town))
            return playableTownsWithHeroesList.get(town);
        else
            throw new NoSuchElementException(NO_SUCH_TOWN  + town.getTownName());
    }
    public Map<Town, List<Hero>> mapHeroesToStartingTowns(List<Town> availableTowns, Set<Hero> availableHeroes) {
        Map<Town, List<Hero>> heroesmap = new TreeMap<>(Comparator.comparing(Town::getTownName));

        for(Town town : availableTowns){
            List<String> startingHeroes = town.getStartingHeroClasses();
            List<Hero> heroesInTown = new ArrayList<>();

            for(Hero hero : availableHeroes){
                if(startingHeroes.contains(hero.getHeroClass()))
                    heroesInTown.add(hero);
            }
            heroesmap.put(town,heroesInTown);
        }
        return heroesmap;
    }

    public Hero selectHeroByName(Town heroTown, String name) {
        if(playableTownsWithHeroesList.containsKey(heroTown)){
            List<Hero> heroList = playableTownsWithHeroesList.get(heroTown);
            for (Hero hero : heroList) {
                if (hero.getName().equals(name)) {
                    heroList.remove(hero);
                    return hero;
                }
            }

        }

        throw new NoSuchElementException(HERO_NOT_FOUND  + name);
    }
}