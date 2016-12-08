package com.example.demo.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class PhoneMonitorService extends IntentService {
    private static final String TAG = "PhoneMonitorService";
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_PhoneMonitor = "com.example.demo.services.action.PhoneMonitor";
    private static final String ACTION_BAZ = "com.example.demo.services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PATH = "com.example.demo.services.extra.PATH";
    private static final String EXTRA_PARAM2 = "com.example.demo.services.extra.PARAM2";

    public PhoneMonitorService() {
        super("PhoneMonitorService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionPhone(Context context, String path, String param2) {
        Intent intent = new Intent(context, PhoneMonitorService.class);
        intent.setAction(ACTION_PhoneMonitor);
        intent.putExtra(EXTRA_PATH, path);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String path, String param2) {
        Intent intent = new Intent(context, PhoneMonitorService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PATH, path);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_PhoneMonitor.equals(action)) {
                final String path = intent.getStringExtra(EXTRA_PATH);
                handleActionPhone(path);
            } else if (ACTION_BAZ.equals(action)) {
                final String path = intent.getStringExtra(EXTRA_PATH);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(path, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionPhone(String path) {
        TelephonyManager telephonyManager= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d(TAG, "onCallStateChanged: 空闲状态");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.d(TAG, "onCallStateChanged: 响铃状态");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d(TAG, "onCallStateChanged: 接通状态");
                        break;
                    default:
                        Log.d(TAG, "onCallStateChanged: 12345");
                }
            }
        },PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String path, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
