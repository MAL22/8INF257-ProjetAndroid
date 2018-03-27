package uqac8inf257.wikipersona.model;

import java.util.Vector;

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
    private Vector<DamageType> weaknesses;
    private Vector<DamageType> resistances;

    public Shadow(
            int id,
            String fakeName,
            String realName,
            String history,
            Arcana arcana,
            Personality personality,
            Statistics stats,
            Vector<DamageType> weaknesses,
            Vector<DamageType> resistances
    ) {
        this.id = id;
        this.realName = realName;
        this.fakeName = fakeName;
        this.history = history;
        this.arcana = arcana;
        this.personality = personality;
        this.stats = stats;
        this.weaknesses = weaknesses;
        this.resistances = resistances;
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

    public Vector<DamageType> getWeaknesses() {
        return weaknesses;
    }

    public Vector<DamageType> getResistances() {
        return resistances;
    }

}
