package com.mygdx.game.Game2D.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.Game2D.status.CurrentMusicDisplay;

import java.util.*;

public class AudioManager {
    public static AudioManager instance;
    public static HashMap<String, Sound> sounds;
    public static HashMap<String, Music> musics;
    public static boolean buttonSound = false;
    public Music currentMusic;
    public CurrentMusicDisplay currentMusicDisplay;
    public ArrayList<Music> fillers;
    Queue<Music> fillersQueue = new LinkedList<Music>();
    public float musicVolume = 100F;
    public float soundVolume = 100F;

    private AudioManager() {
        sounds = new HashMap<>();
        musics = new HashMap<>();
        fillers = new ArrayList<>();
        loadSound();
        loadMusic();
        currentMusicDisplay = new CurrentMusicDisplay();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void loadMusic() {
        FileHandle[] files = Gdx.files.internal("assets/media/musics").list();
        for (FileHandle file : files) {
            Music music = Gdx.audio.newMusic(file);

            music.setOnCompletionListener(new Music.OnCompletionListener() {
                @Override
                public void onCompletion(Music music) {
                    handleMusicCompletion(music);
                }
            });

            if (Objects.equals(file.nameWithoutExtension(), "Moonlighter OST - 02 - Will's Theme") ||
                    Objects.equals(file.nameWithoutExtension(), "Moonlighter OST - 03 - Village of Rynoka") ||
                    Objects.equals(file.nameWithoutExtension(), "Moonlighter OST - 04 - The Moonlighter") ||
                    Objects.equals(file.nameWithoutExtension(), "Moonlighter OST - 23 - The Heroic Merchant") ||
                    Objects.equals(file.nameWithoutExtension(), "Stardew Valley OST - Music Box Song") ||
                    Objects.equals(file.nameWithoutExtension(), "Volcano Mines (Forgotten World)") ||
                    Objects.equals(file.nameWithoutExtension(), "Celeste Original Soundtrack - 02 - First Steps")) {
                fillers.add(music);
                fillersQueue.add(music);
            }else{
                music.setLooping(true);
            }

            musics.put(file.nameWithoutExtension(), music);
        }
    }

    public void loadSound() {
        FileHandle[] files = Gdx.files.internal("assets/media/sounds").list();
        for (FileHandle file : files) {
            Sound sound = Gdx.audio.newSound(file);
            sounds.put(file.nameWithoutExtension(), sound);
        }
    }

    public void playSound(String name) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            long soundId = sound.play();
            sound.setVolume(soundId, soundVolume);
        }
    }

    public void playMusic(String name) {
        Music music = musics.get(name);
        if (music != null) {
            if (currentMusic != null) currentMusic.stop();
            currentMusic = music;
            currentMusic.play();
            currentMusic.setVolume(musicVolume);
            currentMusicDisplay.updateMusicLabel(name);
            currentMusicDisplay.startScrolling();
        }
    }

    public void playMusic(Music music) {
        if (music != null) {
            if (currentMusic != null) currentMusic.stop();
            currentMusic = music;
            currentMusic.play();
            currentMusicDisplay.updateMusicLabel(getMusicTitle(music));
        }
    }

    public void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            handleMusicCompletion(currentMusic);
        }
    }

    public void disposeSound(String name) {
        Sound sound = sounds.get(name);
        if (sound != null) {
            sound.dispose();
            sounds.remove(name);
        }
    }

    public void disposeAll() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }
        sounds.clear();
    }

    public Music getMusic(String music) {
        return musics.get(music);
    }

    private String getMusicTitle(Music music) {
        for (Map.Entry<String, Music> entry : musics.entrySet()) {
            if (entry.getValue().equals(music)) {
                return entry.getKey();
            }
        }
        return "";
    }

    public CurrentMusicDisplay getCurrentMusicDisplay() {
        return currentMusicDisplay;
    }

    private void handleMusicCompletion(Music music) {
        Gdx.app.log("AudioManager", "Music completed: " + getMusicTitle(music));

        if (!fillersQueue.isEmpty()) {
            Music nextMusic = fillersQueue.poll(); // Retrieve and remove the head of the queue
            if (nextMusic != null) {
                playMusic(nextMusic);
                fillersQueue.offer(music); // Add the finished music to the end of the queue
            }
        }
    }
}