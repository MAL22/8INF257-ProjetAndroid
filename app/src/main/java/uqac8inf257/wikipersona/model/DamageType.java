package uqac8inf257.wikipersona.model;

/**
 * Created by mimil on 2018-03-26.
 */

public class DamageType {
    private final int id;
    private String name;

    public DamageType(int id, String name) {
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
