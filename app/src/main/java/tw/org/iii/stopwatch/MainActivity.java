package tw.org.iii.stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView clock;
    private Button left, right;
    private ListView list;
    private boolean isStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clock = findViewById(R.id.clock);
        left = findViewById(R.id.left_btn);
        right = findViewById(R.id.right_btn);
        list = findViewById(R.id.list);
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

    }

    private void doStop(){

    }

}
