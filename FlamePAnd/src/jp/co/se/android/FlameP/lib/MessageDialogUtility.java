package jp.co.se.android.FlameP.lib;

import java.util.concurrent.CountDownLatch;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;

public class MessageDialogUtility {
	public static ProgressDialog progressDialog = null;
	private static int showingCount = 0;

	/**
	 */
	public static void Init()
	{
		if (progressDialog != null && progressDialog.isShowing())
		{
			try{
				progressDialog.dismiss();
			}catch(Exception ex){}
		}
		showingCount = 0;
	}



	/**
	 *
	 * @param activity
	 * @param messageId
	 */
	public static void Show(Activity activity, int messageId) {
		MessageDialogUtility.Show(activity,ProgressDialog.STYLE_SPINNER, messageId, true, null);
	}

	/**
	 *
	 * @param activity
	 * @param messageId
	 * @param cancellistener
	 */
	public static void Show(Activity activity, int messageId, DialogInterface.OnCancelListener listener)
	{
		MessageDialogUtility.Show(activity,ProgressDialog.STYLE_SPINNER, messageId, true, listener);
	}


	/**
	 *
	 * @param activity
	 * @param messageId
	 * @param cancelable
	 * @param cancellistener
	 */
	public static void Show(
			Activity activity,
			int progressStyle,
			int messageId,
			Boolean cancelable,
			DialogInterface.OnCancelListener listener
			)
	{
		Library.TraseMsg("Show 1");
		
		if (progressDialog != null && progressDialog.isShowing()) {
			try{
				progressDialog.dismiss();
			}catch(Exception ex)
			{}
		}
		Library.TraseMsg("Show 2");
		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage(activity.getString(messageId));
		progressDialog.setProgressStyle(progressStyle);
		Library.TraseMsg("Show 3");
		if (listener != null)
		{
			progressDialog.setOnCancelListener(listener);
		}
		Library.TraseMsg("Show 4");
		progressDialog.setCancelable(cancelable);
		progressDialog.show();
		Library.TraseMsg("Show 5");

		showingCount++;

	}

	/**
	 *
	 * @param activity
	 * @param messageId
	 */
	public static void ShowHorizontal(
			Activity activity,
			int messageId,
			Boolean cancelable,
			int max,
			DialogInterface.OnCancelListener listener
			) {
		if (progressDialog != null && progressDialog.isShowing()) {
			try{
				progressDialog.dismiss();
			}catch(Exception ex)
			{}
		}
		progressDialog = new ProgressDialog(activity);
		progressDialog.setMessage(activity.getString(messageId));
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		progressDialog.setMax(max);
		progressDialog.incrementProgressBy(0);
		progressDialog.incrementSecondaryProgressBy(1);
		progressDialog.setCancelable(cancelable);
		progressDialog.setOnCancelListener(listener);
		progressDialog.show();
		showingCount++;
	}

	public static void SetCancel(Boolean cancel)
	{
		progressDialog.setCancelable(cancel);
	}

	public static void SetProgress(int count)
	{
		progressDialog.setProgress(count);
	}



	/**
	 * プログレス繝???アログ非表示
	 */
	public static void Close() {
		int err = 0;
		try
		{
			err = 1;
			// 表示数縺?になる・合・み非表示を実施
//			if (--showingCount <= 0) {
//				// 荳???荳??イナスになってしまった・合・補正
//				err = 2;
//				showingCount = 0;
//				err = 3;				
//				if (progressDialog != null && progressDialog.isShowing()) {
//					err = 4;
//					progressDialog.dismiss();
//				}
//			}
			
			
			if (progressDialog != null && progressDialog.isShowing()) {
				err = 4;
				progressDialog.hide();
			}
			
		}catch (Exception e) {
		}
	}

	
	public static final void showDialogWaitDismiss(final Handler handler,
			final Dialog dialog, final OnDismissListener dismissListener) {
		final CountDownLatch signal = new CountDownLatch(1);
		dialog.setOnDismissListener(new OnDismissListener() {
			//
			public void onDismiss(DialogInterface dialog) {
				try {
					if (dismissListener != null) {
						dismissListener.onDismiss(dialog);
					}
				} finally {
					signal.countDown();
				}
			}
		});
		if (Thread.currentThread() != handler.getLooper().getThread()) {
			handler.post(new Runnable() {
				//@Override
				public void run() {
					dialog.show();
				}
			});
			try {
				signal.await();
			} catch (InterruptedException e) {
				// ignore
			}
		} else {
			dialog.show();
		}
	}
}
