package uqac8inf257.wikipersona.data;

import java.io.Serializable;

/**
 * Created by mimil on 2018-03-08.
 */

public class Personality implements Serializable {

    private int id;
    private String name;

    public Personality(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}