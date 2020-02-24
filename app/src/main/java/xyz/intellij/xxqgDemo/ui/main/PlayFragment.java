package xyz.intellij.xxqgDemo.ui.main;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import xyz.intellij.xxqgDemo.MainActivity;
import xyz.intellij.xxqgDemo.R;
import xyz.intellij.xxqgDemo.VideoItem;


public class PlayFragment extends Fragment implements View.OnClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }
    VideoView videoView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取列表传来的参数
        Bundle bundle = getArguments();
        String date = bundle.getString("arg");

        final ArrayList<VideoItem.Star> video = new ArrayList<VideoItem.Star>();


        //实现播放

        int pos = Integer.parseInt(date);
        VideoItem aaa = new VideoItem();
        aaa.loadData(this.getContext());
        for(int i=1, count=aaa.ITEM_MAP.size(); i<=count; i++){
            video.add(aaa.ITEMS.get(i));
        }
        VideoItem.Star des = video.get(pos);

        videoView = (VideoView)getActivity().findViewById(R.id.showvideo);
        Button btnPlay = (Button)getActivity().findViewById(R.id.btnPlay);
        Button btnPause = (Button)getActivity().findViewById(R.id.btnPause);
        Button btnReplay = (Button)getActivity().findViewById(R.id.btnReplay);

        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnReplay.setOnClickListener(this);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }else {
            initVideoPath(des);//初始化MediaPlayer
        }


        TextView tv = getActivity().findViewById(R.id.video_detail);
        tv.setText(des.contents);


        Configuration configuration = getActivity().getResources().getConfiguration();
        int ori = configuration.orientation;

    }
    private void initVideoPath(VideoItem.Star in) {
        videoView.setVideoPath(in.url);//指定视频文件路径
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);//让电影循环播放
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPlay:
                if(!videoView.isPlaying()){
                    videoView.start();//播放
                }
                break;
            case R.id.btnPause:
                if(videoView.isPlaying()){
                    videoView.pause();//暂停
                }
                break;
            case R.id.btnReplay:
                if(videoView.isPlaying()){
                    videoView.resume();//重新播放
                }
                break;
        }
    }
}
