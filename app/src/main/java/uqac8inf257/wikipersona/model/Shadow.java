package uqac8inf257.wikipersona.model;

/**
 * Created by mimil on 2018-03-08.
 */

public class Shadow {

    private final int id;
    private String fakeName;
    private String realName;
    private String history;
    private Arcana arcana;
    private Personality personality;
    private Statistics stats;

    public Shadow(int id, String fakeName, String realName, String history, int arcana_id, String arcana_name, int personality_id, String personality_name, Statistics stats) {
        this.id = id;
        this.realName = realName;
        this.fakeName = fakeName;
        this.history = history;
        this.arcana = new Arcana(arcana_id, arcana_name);
        this.personality = new Personality(personality_id, personality_name);
        this.stats = stats;
    }

    public int getId() {
        return id;
    }

    public String getFakeName() {
        return fakeName;
    }

    public String getRealName() {
        return realName;
    }

    public String getHistory() {
        return history;
    }

    public Arcana getArcana() {
        return arcana;
    }

    public Personality getPersonality() {
        return personality;
    }

    public Statistics getStats() {
        return stats;
    }
}
