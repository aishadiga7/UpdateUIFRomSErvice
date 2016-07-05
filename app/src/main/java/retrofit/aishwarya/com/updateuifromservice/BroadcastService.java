package retrofit.aishwarya.com.updateuifromservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

/**
 * Created by aishwarya on 28/6/16.
 */
public  class BroadcastService extends Service {
    private static final String TAG = BroadcastService.class.getSimpleName();
    public static final String BROADCAST_ACTION = "retrofit.aishwarya.com.updateuifromservice.displayevent";
    private static final Handler sHandler = new Handler();
    private Intent mIntent;
    private int mCounter = 0;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mIntent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        sHandler.removeCallbacks(sendUpdatesToUI);
        sHandler.postDelayed(sendUpdatesToUI, 1000);
    }

    private Runnable sendUpdatesToUI = new Runnable() {
        public void run() {
            DisplayLoggingInfo();
            sHandler.postDelayed(this, 5000); // 5 seconds
        }
    };

    private void DisplayLoggingInfo() {
        Log.d(TAG, "entered DisplayLoggingInfo");

        mIntent.putExtra("time", new Date().toLocaleString());
        mIntent.putExtra("counter", String.valueOf(++mCounter));
        sendBroadcast(mIntent);
    }

    @Override
    public void onDestroy() {
        sHandler.removeCallbacks(sendUpdatesToUI);
        super.onDestroy();
    }

}
