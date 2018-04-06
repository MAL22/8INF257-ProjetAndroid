package uqac8inf257.wikipersona.data;

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
    private Vector<Skill> skills;
    private Vector<DamageType> weaknesses;
    private Vector<DamageType> resistances;

    private int level;
    private int hp;
    private int sp;
    private String drop;
    private String skillCard;
    private int exp;
    private int yen;

    public Shadow(
            int id,
            String fakeName,
            String realName,
            String history,
            Arcana arcana,
            Personality personality,
            Statistics stats,
            Vector<Skill> skills,
            Vector<DamageType> weaknesses,
            Vector<DamageType> resistances,
            int level,
            int hp,
            int sp,
            String drop,
            String skillCard,
            int exp,
            int yen
    ) {
        this.id = id;
        this.realName = realName;
        this.fakeName = fakeName;
        this.history = history;
        this.arcana = arcana;
        this.personality = personality;
        this.stats = stats;
        this.skills = skills;
        this.weaknesses = weaknesses;
        this.resistances = resistances;
        this.level = level;
        this.hp = hp;
        this.sp = sp;
        this.drop = drop;
        this.skillCard = skillCard;
        this.exp = exp;
        this.yen = yen;
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

    public Vector<Skill> getSkills() {

        return skills;
    }

    public Vector<DamageType> getWeaknesses() {

        return weaknesses;
    }

    public Vector<DamageType> getResistances() {

        return resistances;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getSp() {
        return sp;
    }

    public String getDrop() {
        return drop;
    }

    public String getSkillCard() {
        return skillCard;
    }

    public int getExp() {
        return exp;
    }

    public int getYen() {
        return yen;
    }
}
