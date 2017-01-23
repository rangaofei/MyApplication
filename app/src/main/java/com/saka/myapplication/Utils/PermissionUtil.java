package com.saka.myapplication.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;


/**
 * Created by saka on 2017/1/22.
 */

public class PermissionUtil {
    public static final String TAG = "PermissionUtil";

    public static final String CALENDAR = Manifest.permission_group.CALENDAR;
    public static final String CAMERA = Manifest.permission.CAMERA;
    public static final String CONTACTS = Manifest.permission_group.CONTACTS;
    public static final String LOCATION = Manifest.permission_group.LOCATION;
    public static final String MICROPHONE = Manifest.permission.RECORD_AUDIO;
    public static final String PHONE = Manifest.permission_group.PHONE;
    public static final String SENSORS = Manifest.permission_group.SENSORS;
    public static final String SMS = Manifest.permission_group.SMS;
    public static final String STORAGE = Manifest.permission_group.STORAGE;

    public static boolean checkPermission(Activity activity, String targetPermission) {
        boolean havePermission = false;
        try {
            int permissionResult = ContextCompat.checkSelfPermission(activity, targetPermission);
            if (permissionResult == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "已拥有该权限" + targetPermission);
                havePermission = true;
            } else {
                Log.d(TAG, "没有该权限" + targetPermission);
                havePermission = false;
                if(showPermissionExplainDialog(activity,targetPermission)){
                    Log.d(TAG,"显示解释性对话框");
                    createPermissionExplainDialog(activity);
                }else {
                    Log.d(TAG,"显示申请权限对话框");
                    requestPermission(activity,new String[]{targetPermission},1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return havePermission;
    }

    public static boolean showPermissionExplainDialog(Activity activity,String targetPermission){
        return ActivityCompat.shouldShowRequestPermissionRationale(activity,targetPermission);
    }

    public static void createPermissionExplainDialog(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setMessage("缺少必须权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent();
                        Log.d(TAG,"启动设置:"+activity.getPackageName());
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri=Uri.fromParts("package",activity.getPackageName(),null);
                        intent.setData(uri);
                        activity.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }

    public static void requestPermission(Activity activity,String[] strings,int requestCode){
        ActivityCompat.requestPermissions(activity,strings,requestCode);
    }



}
