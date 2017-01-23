package com.saka.myapplication;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.saka.myapplication.R;
import com.example.saka.myapplication.databinding.ActivityCameraBinding;
import com.saka.myapplication.BaseComponent.BaseActivity;
import com.saka.myapplication.Utils.PermissionUtil;

public class CameraActivity extends BaseActivity {

    private ActivityCameraBinding binding;
    public static final String TAG = "CameraActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_camera);
        if (PermissionUtil.checkPermission(this, PermissionUtil.CAMERA)) {
            Toast.makeText(this, "继续执行", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "被授予了权限", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "授予权限");
                } else {
                    Toast.makeText(this, "被拒绝了权限", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "拒绝权限");
                    PermissionUtil.checkPermission(this, PermissionUtil.CAMERA);
                }
                break;
        }
    }
}
