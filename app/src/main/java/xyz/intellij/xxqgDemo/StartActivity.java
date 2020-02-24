package xyz.intellij.xxqgDemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends Activity implements View.OnClickListener {
    //设置倒计时
    private int recLen = 5;
    private TextView tv;
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    String skip_ad;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        skip_ad = this.getString(R.string.skip_ad);
        setContentView(R.layout.activity_start);

        initView();
        intent= new Intent(StartActivity.this, MainActivity.class);
        timer.schedule(task, 1000, 1000);
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    private void initView() {
        tv = findViewById(R.id.tv);//跳过
        tv.setOnClickListener(this);//跳过监听
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() { // UI thread
                @Override
                public void run() {
                    recLen--;
                    tv.setText(skip_ad+recLen);
                    if (recLen < 0) {
                        timer.cancel();
                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
                    }
                }
            });
        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv:
                startActivity(intent);
                finish();
                if (runnable != null) {
                    handler.removeCallbacks(runnable);
                }
                break;
            default:
                break;
        }
    }
}
