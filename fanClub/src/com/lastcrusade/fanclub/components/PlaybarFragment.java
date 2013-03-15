package com.lastcrusade.fanclub.components;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.lastcrusade.fanclub.R;
import com.lastcrusade.fanclub.audio.SingleFileAudioPlayer;
import com.lastcrusade.fanclub.service.PlaylistService;
import com.lastcrusade.fanclub.service.PlaylistService.PlaylistServiceBinder;
import com.lastcrusade.fanclub.service.ServiceLocator;
import com.lastcrusade.fanclub.service.ServiceNotBoundException;
import com.lastcrusade.fanclub.util.BroadcastRegistrar;
import com.lastcrusade.fanclub.util.IBroadcastActionHandler;

public class PlaybarFragment extends Fragment {

    private static final String TAG = PlaybarFragment.class.getName();
    
    private BroadcastRegistrar registrar;
    
    private ServiceLocator<PlaylistService> playlistServiceLocator;
    ImageButton playPause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.playlistServiceLocator = new ServiceLocator<PlaylistService>(
                this.getActivity(), PlaylistService.class, PlaylistServiceBinder.class);
        registerReceivers();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        playlistServiceLocator.unbind();
        unregisterReceivers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_playbar, container, false);
        playPause = ((ImageButton) view.findViewById(R.id.btn_play_pause));

        playPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PlaylistService service = playlistServiceLocator.getService();
                    if (service.isPlaying()) {
                        service.pause();
                    } else {
                        service.play();
                    }
                    smartSetPlayPauseImage(service);
                }catch (ServiceNotBoundException e) {
                    Log.wtf(TAG, e);
                }
            }

        });

        ((ImageButton) view.findViewById(R.id.btn_skip))
            .setOnClickListener(new OnClickListener() {
    
                @Override
                public void onClick(View v) {
                    try {
                        PlaylistService service = playlistServiceLocator.getService();
                        service.skip();
                    } catch (ServiceNotBoundException e) {
                        Log.wtf(TAG, e);
                    }
                }
            });

        return view;
    }

    private void setPlayImage() {
        playPause.setImageDrawable(getResources().getDrawable(R.drawable.av_play));
    }

    private void setPauseImage(){
        playPause.setImageDrawable(getResources().getDrawable(R.drawable.av_pause));
    }

    private void smartSetPlayPauseImage(PlaylistService service){
        if(service.isPlaying()){
            setPauseImage();
        } else {
            setPlayImage();
        }
    }

    /**
     * Register intent receivers to control this service
     * 
     */
    private void registerReceivers() {
        this.registrar = new BroadcastRegistrar();
        this.registrar.addAction(SingleFileAudioPlayer.ACTION_SONG_FINISHED, new IBroadcastActionHandler() {
            
            @Override
            public void onReceiveAction(Context context, Intent intent) {
                setPlayImage();
            }
        }).register(this.getActivity());
    }

    private void unregisterReceivers() {
        this.registrar.unregister();
    }

}
