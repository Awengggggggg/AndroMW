package com.android.amw.mwb;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SmsDeliveredHelper extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent arg1) {
        switch (getResultCode()) {
            case Activity.RESULT_OK:
           // SMS DELIEVERED
                break;
            case Activity.RESULT_CANCELED:
          // SMS NOT DELIEVERED
                break;

        }
    }
}


