package net.pmkjun.planetskilltimer.file;

import java.io.Serializable;

public class Data implements Serializable {
    public long[] lastSkillTime = new long[4];
    public boolean toggleSkilltimer = true;
    public boolean[] toggleSkills = new boolean[4];
    public int SkillTimerXpos = 0;
    public int SkillTimerYpos = 0;
}
