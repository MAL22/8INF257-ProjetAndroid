package uqac8inf257.wikipersona.data;

import java.io.Serializable;

/**
 * Created by mimil on 2018-03-10.
 */

public class Skill implements Serializable {
    private final int id;
    private String name;
    private String effect;

    public Skill(int id, String name, String effect) {
        this.id = id;
        this.name = name;
        this.effect = effect;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }
}
