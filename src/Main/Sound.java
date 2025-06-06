package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound(){

        soundURL[0] = getClass().getResource(("/sound/SoldierPoetKingHarmonica.wav"));
        soundURL[1] = getClass().getResource(("/sound/mixkit-wood-hard-hit-2182.wav"));
        soundURL[2] = getClass().getResource(("/sound/mixkit-select-click-1109.wav"));
        soundURL[3] = getClass().getResource(("/sound/mixkit-arcade-retro-game-over-213.wav"));
        soundURL[4] = getClass().getResource(("/sound/SoldierPoetKingKalimba.wav"));
        soundURL[5] = getClass().getResource(("/sound/SoldierPoetKingPercussive.wav"));
        soundURL[6] = getClass().getResource(("/sound/SoldierPoetKingTuba.wav"));
        soundURL[7] = getClass().getResource(("/sound/mixkit-final-level-bonus-2061.wav"));
        soundURL[8] = getClass().getResource(("/sound/mixkit-player-losing-or-failing-2042.wav"));
        soundURL[9] = getClass().getResource(("/sound/mixkit-creaky-door-open-195.wav"));
        soundURL[10]  = getClass().getResource(("/sound/mixkit-winning-a-coin-video-game-2069.wav"));
        soundURL[11]  = getClass().getResource(("/sound/BOWnSWORD.wav"));
        soundURL[12]  = getClass().getResource(("/sound/BOWnSHIELD.wav"));
        soundURL[13]  = getClass().getResource(("/sound/SWORDnSHEILD.wav"));
        soundURL[14]  = getClass().getResource(("/sound/HarpyHare.wav"));
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }

    public void checkVolume(){
        switch(volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f;break;
            case 2: volume = -12f;break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
