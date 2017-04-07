package com.caretom.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtils {

	public static boolean isNetworkAvailable(Context context) {
		boolean connected = false;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState() == NetworkInfo.State.CONNECTED
				|| connectivityManager.getNetworkInfo(
						ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
			// we are connected to a network
			connected = true;
		} else
			connected = false;
		return connected;
	}

	public static void showInternetDialog(Context context) {

		try {
			AlertDialog alertDialog = new AlertDialog.Builder(context).create();

			alertDialog.setTitle("Info");
			alertDialog
					.setMessage("Internet not available, check your internet connectivity and try again");
			alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			});

			alertDialog.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
