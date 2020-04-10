package com.syt.cellphone.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.syt.cellphone.R;

//弹窗工具类
public class DialogUtils {

    //loading
    public static AlertDialog alertDialog;
    private static RotateAnimation rotateAnimation;

    public static void showLoadingDialog(Context context, String txt) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        alertDialog.setContentView(view);
        ImageView dialog_loading_ic = view.findViewById(R.id.dialog_loading_ic);
        TextView textView = view.findViewById(R.id.dialog_loading_tv);
        textView.setText(txt);
        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        dialog_loading_ic.startAnimation(rotateAnimation);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public static void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            rotateAnimation.cancel();
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}
