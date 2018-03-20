package com.wawrow.startup.audio;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Created by Wawrów on 19.01.2018.
 */
public class Stream {
    HeadlessMediaPlayer mediaPlayer;
    private String url = null;


    public Stream() {
        new NativeDiscovery().discover();
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        this.url = url;

    }
    public void start(String url){
        mediaPlayer.startMedia(url);
    }
    public void stop(){
        mediaPlayer.stop();
    }
    public boolean isConn(){
        return mediaPlayer.isPlaying();
    }





}

