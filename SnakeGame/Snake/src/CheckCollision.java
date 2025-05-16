public class CheckCollision {
    GamePanel panel;

    public CheckCollision(GamePanel panel) {
        this.panel = panel;
    }

    public void checkBorder() {
        // Check self-collision: head vs body
        for (int i = 1; i < panel.snakeLength; i++) {
            if (panel.snakeXLength[0] == panel.snakeXLength[i] &&
                panel.snakeYLength[0] == panel.snakeYLength[i]) {
                panel.running = false;
            }
        }

        // Check wall collision: left/right border
        if (panel.snakeXLength[0] < 0 || panel.snakeXLength[0] >= panel.screenWidth) {
            panel.running = false;
        }

        // Check wall collision: top/bottom border
        if (panel.snakeYLength[0] < 0 || panel.snakeYLength[0] >= panel.screenHeight) {
            panel.running = false;
        }
        if(!panel.running){
            panel.timer.stop();
        }
    }
}
