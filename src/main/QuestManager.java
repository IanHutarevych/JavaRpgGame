package main;

public class QuestManager {

    GamePanel gp;

    public boolean quest1Active = false;
    public boolean quest1Done = false;
    public boolean quest1Rewarded = false;
    public int ironCollected = 0;
    public final int ironRequired = 3;

    public QuestManager(GamePanel gp) {
        this.gp = gp;
    }

    public void startQuest1() {
        if (!quest1Active && !quest1Done) {
            quest1Active = true;
            ironCollected = 0;
        }
    }

    public void onIronCollected() {
        if (quest1Active && !quest1Done) {
            ironCollected = Math.min(ironCollected + 1, ironRequired);
            if (ironCollected == ironRequired) {
                quest1Done = true;
                quest1Active = false;
                gp.ui.addMessage("Quest completed!");
            }
        }
    }

    public boolean canClaimReward() {
        return quest1Done && !quest1Rewarded;
    }

    public void claimReward() {
        gp.player.coin += 50;
        quest1Rewarded = true;
        gp.ui.addMessage("Received 50 coins!");
    }
}