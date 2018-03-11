package tw.org.iii.stopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView clock;
    private Button left, right;
    private ListView list;
    private boolean isStart;
    private Timer timer;
    private int i;
    private UIHandler handler;
    private ClockTask clockTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        left = findViewById(R.id.left_btn);
        right = findViewById(R.id.right_btn);
        list = findViewById(R.id.list);

        timer = new Timer();
        handler = new UIHandler();
    }

    public void doLeft(View view){

    }

    public void doRight(View view){
        isStart = !isStart;

        right.setText(isStart?"Stop":"Satrt");
        left.setText(isStart?"Lap":"Reset");
        if (isStart){
            doStart();
        }else{
            doStop();
        }
    }

    private void doStart(){
        clockTask = new ClockTask();
        timer.schedule(clockTask, 10, 10);
    }

    private void doStop(){
        if (clockTask != null){
            clockTask.cancel();
        }
    }

    private class ClockTask extends TimerTask {
        @Override
        public void run() {
            i++;
            handler.sendEmptyMessage(0);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            clock.setText("" + i);
        }
    }


    @Override
    public void finish() {
        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
        super.finish();
    }
}
