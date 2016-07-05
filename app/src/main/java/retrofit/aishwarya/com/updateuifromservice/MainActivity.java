package retrofit.aishwarya.com.updateuifromservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Intent mIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, BroadcastService.class);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateUI(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        startService(mIntent);
        registerReceiver(mBroadcastReceiver, new IntentFilter(BroadcastService.BROADCAST_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
        stopService(mIntent);
    }

    private void updateUI(Intent intent) {
        String counter = intent.getStringExtra("counter");
        String time = intent.getStringExtra("time");
        Log.d(TAG, counter);
        Log.d(TAG, time);

        TextView txtDateTime = (TextView) findViewById(R.id.txtDateTime);
        TextView txtCounter = (TextView) findViewById(R.id.txtCounter);
        if (txtDateTime != null) {
            txtDateTime.setText(time);
        }
        if (txtCounter != null) {
            txtCounter.setText(counter);
        }
    }



}
