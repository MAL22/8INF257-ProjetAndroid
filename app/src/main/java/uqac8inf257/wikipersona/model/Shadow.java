package uqac8inf257.wikipersona.model;

/**
 * Created by mimil on 2018-03-08.
 */

public class Shadow {

    private int id;
    private String fakeName;
    private String realName;
    private String history;
    private String arcana;
    private String personality;
    private Statistics stats;

    public Shadow(int id, String fakeName, String realName, String history, String arcana, String personality, Statistics stats) {
        this.id = id;
        this.realName = realName;
        this.fakeName = fakeName;
        this.history = history;
        this.arcana = arcana;
        this.personality = personality;
        this.stats = stats;

    }


}
