package model;

import java.util.HashMap;
import java.util.Map;

public class Detective extends Citizen{
    private Map<Integer , Boolean> playersMap; //todo
    public Detective(String name, int id) {
        super(name, id);
        playersMap = new HashMap<>();
    }

    public Detective(String name) {
        this(name , -1);
    }

    public void detect(int id , boolean isMafia){
        playersMap.put(id , isMafia);
    }

    public boolean isMafia(int id){
        return playersMap.getOrDefault(id , false);
    }
}
