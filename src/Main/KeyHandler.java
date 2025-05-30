package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    //debug
    public boolean showDebugText = false;
    public KeyHandler(GamePanel gp){
            this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            if(gp.gameState == gp.titleState) {titleState(code);
            }
            else if (gp.gameState == gp.playState){
                playState(code);
            }
            else if (gp.gameState == gp.pauseState) {
                pauseState(code);
            }
            else if (gp.gameState == gp.dialogueState) {
                dialogueState(code);
            }
            else if (gp.gameState == gp.optionsState) {
                optionsState(code);
            } else if (gp.gameState == gp.cutsceneState) {
                cutsceneState(code);
            }

        if (gp.gameState == gp.minigameState && gp.currentMinigame instanceof SnakeMinigame snakeGame) {
            snakeGame.handleKeyPress(code);
        }
         if (gp.gameState == gp.minigameState && gp.currentMinigame instanceof HangmanMinigame hangmanGame) {
            hangmanGame.handleKeyPress(code);
        }
        if (gp.gameState == gp.minigameState && gp.currentMinigame instanceof RPSMinigame RPSGame) {
            RPSGame.handleKeyPress(code);
        }
    }

    public void titleState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum-=2;
            gp.playSE(1);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum+=2;
            gp.playSE(1);
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
            gp.playSE(2);
            if (gp.ui.commandNum == 0) {
                gp.ui.startTransition(gp.cutsceneState);
                gp.cutsceneManager.sceneNum = 0;
            }
            if (gp.ui.commandNum == 1) {
                //load game
            }
            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState(int code){
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }

        if(code == KeyEvent.VK_ENTER|| code == KeyEvent.VK_E) {
            enterPressed = true;
        }

        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
        }
        //Debug
        if (code == KeyEvent.VK_T) {
            showDebugText = !showDebugText;
        }
        //reloadmap
        if(code == KeyEvent.VK_R){
            switch(gp.currentMap){
                case 0: gp.tileM.loadMap("maps/bedroomMap(withFurniture).txt", 0); break;
                case 1: gp.tileM.loadMap("maps/LivingRoomMap.txt", 1); break;
            }
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.titleState;
            gp.stopMusic();
        }
    }
    public void dialogueState(int code){
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
            enterPressed = true;
        }
    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
            enterPressed = true;
            gp.playSE(2);
        }

        int maxCommandNum = switch (gp.ui.subState) {
            case 0 -> 5;
            case 3 -> 1;
            default -> 0;
        };
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(1);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(1);//change this to appropriate sound
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(1);
                }
                if(gp.ui.commandNum == 2 && gp.sound.volumeScale > 0) {
                    gp.sound.volumeScale--;
                    gp.playSE(1);
                }
            }
        }
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(1);
                }
                if(gp.ui.commandNum == 2 && gp.sound.volumeScale < 5) {
                    gp.sound.volumeScale++;
                    gp.playSE(1);
                }
            }
        }
    }
    public void cutsceneState(int code) {
        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_E) {
            enterPressed = true;
            gp.playSE(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
