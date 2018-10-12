package Welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.music.MainActivity;
import com.example.music.R;

/**
 * Created by 雨生百谷 on 2018/10/12.
 */

public class WelcomAct extends Activity {

    private boolean isFirstIn = false;
    private static final int TIME = 2000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcom);
        init();
    }

    private void init() {
        SharedPreferences perPreferences = getSharedPreferences("jike", MODE_PRIVATE);
        isFirstIn = perPreferences.getBoolean("isFirstIn", true);
        if(!isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            //如果进入过引导界面则将数据储存
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SharedPreferences.Editor editor = perPreferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
        }
    }

    //从当前界面跳转到主界面
    private void goHome() {
        Intent i = new Intent(WelcomAct.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    //从当前界面跳转到引导界面
    private void goGuide() {
        Intent i = new Intent(WelcomAct.this, Guide.class);
        startActivity(i);
        finish();
    }

}
