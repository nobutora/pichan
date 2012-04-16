package jp.co.se.android.FlameP.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.List;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.util.Log;

public class bagtrease implements
		java.lang.Thread.UncaughtExceptionHandler {
	private static Context sContext = null;
	private static final String BUG_FILE = "BugReport";
	private static final UncaughtExceptionHandler sDefaultHandler = Thread
			.getDefaultUncaughtExceptionHandler();
	private Activity activity = null;

	private static PackageInfo sPackInfo = null;

	/**
	 * �R���X�g���N�^
	 *
	 * @param context
	 */
	public bagtrease(Context context) {
		sContext = context;

		try {
			// �p�b�P�[�W���
			sPackInfo = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �L���b�`����Ȃ���O�ɂ���Ďw�肳�ꂽ�X���b�h���I�������Ƃ��ɌĂяo����܂�

	 *
	 * ��O�X�^�b�N�g���[�X�̓��e���t�@�C���ɏo�͂��܂�

	 */
	public void uncaughtException(Thread thread, Throwable ex) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(sContext.openFileOutput(BUG_FILE,
					Context.MODE_WORLD_READABLE));
			ex.printStackTrace(pw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (pw != null)
				pw.close();
		}
		sDefaultHandler.uncaughtException(thread, ex);
	}

	/**
	 * �o�O���|�[�g�̓��e�����[���ő��M���܂��B

	 *
	 * @param activity
	 */
	public static String getBag(final Activity activity) {
		// �o�O���|�[�g���Ȃ���Έȍ~�̏������s���܂���B

		final File bugfile = activity.getFileStreamPath(BUG_FILE);
		if (!bugfile.exists()) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			String str;
			BufferedReader br = new BufferedReader(new FileReader(bugfile));
			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SENDTO);
		intent.setData(Uri.parse("mailto:" + "gasutorageta@gmail.com"));
		intent
				.putExtra(Intent.EXTRA_SUBJECT, "�yBugReport�z");
		intent.putExtra(Intent.EXTRA_TEXT, sb.toString());
		activity.startActivity(intent);
		
		bugfile.delete();
		return sb.toString();		
	}
}
