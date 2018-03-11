package tw.org.iii.stopwatch;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;
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

    private LinkedList<HashMap<String,String>> data;
    private SimpleAdapter adapter;
    private String[] from = {"text"};
    private int[] to = {R.id.item_text};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        left = findViewById(R.id.left_btn);
        right = findViewById(R.id.right_btn);
        list = findViewById(R.id.list);
        initListView();

        timer = new Timer();
        handler = new UIHandler();
    }

    private void initListView(){
        data = new LinkedList<>();
        adapter = new SimpleAdapter(this, data,R.layout.item,from,to);
        list.setAdapter(adapter);
    }

    public void doLeft(View view){
        if (isStart){
            doLap();
        }else{
            doReset();
        }
    }

    private void doLap(){
        HashMap<String,String> lap = new HashMap<>();
        lap.put(from[0], "" + i);
        data.add(0, lap);
        adapter.notifyDataSetChanged();
    }

    private void doReset(){
        i = 0;

        //clock.setText("0");
        handler.sendEmptyMessage(0);

        data.clear();
        adapter.notifyDataSetChanged();
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
