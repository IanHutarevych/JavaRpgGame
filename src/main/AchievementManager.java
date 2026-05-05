package main;

public class AchievementManager {

    GamePanel gp;

    public int mobsKilled = 0;
    public int nightsSurvived = 0;

    public boolean killsDone = false;
    public boolean nightsDone = false;
    public boolean levelDone = false;

    public AchievementManager(GamePanel gp) {
        this.gp = gp;
    }

    public void update() {
        if (mobsKilled >= 20) killsDone = true;
        if (nightsSurvived >= 3) nightsDone = true;
        if (gp.player.level >= 3) levelDone = true;
    }

   public void onMonsterKilled() {
        if (!killsDone) mobsKilled++;
    }

    public void onNightSurvived() {
        if (!nightsDone) nightsSurvived++;
    }
}