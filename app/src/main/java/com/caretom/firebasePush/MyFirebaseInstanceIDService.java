package com.caretom.firebasePush;

import android.util.Log;

import com.caretom.common.KeyConstants;
import com.caretom.common.SaveData;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by techelogy2 on 22/4/17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService implements KeyConstants {
    private final String TAG = "firebaseToken";
    SaveData saveData;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        saveData = new SaveData(getApplicationContext());
        saveData.save(DEVICE_TOKEN, refreshedToken);


    }
}
