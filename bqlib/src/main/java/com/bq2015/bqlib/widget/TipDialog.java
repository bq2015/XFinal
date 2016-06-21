package com.bq2015.bqlib.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bq2015.bqlib.R;

/************************************************************
 * Author:  bq2015
 * Description:     // 公共对话框
 * Date: 2016/4/18
 ************************************************************/

public class TipDialog {

    public static final int TYPE_SELECT = 1;//两个按钮
    public static final int TYPE_SHOW = 2;//没有按钮
    public static final int TYPE_SHOW_BTN = 3;//一个按钮
    public static final int TYPE_CALL = 4;//呼叫
    public static final int TYPE_PB = 5;//进度

    private Context context;
    private Dialogcallback dialogcallback;
    private DialogDismissCallBack dismissCallback;
    private Callback callback;
    private Dialog dialog;
    private ImageView tagIv;
    private boolean canceledOntouchOutside = true;
    private TextView cancleTv, sureTv, tipTv, tv_sure;
    private View lineView;
    private LinearLayout selectLin;
    private int dialogType = TYPE_SHOW;

    /**
     * init the dialog
     *
     * @return
     */
    public TipDialog(Context con) {
        this.context = con;
        init();
    }

    public TipDialog(Context con, int type) {
        this.context = con;
        dialogType = type;
        init();
    }

    public TipDialog(Context con, boolean canceledOntouchOutside) {
        this.context = con;
        this.canceledOntouchOutside = canceledOntouchOutside;
        init();
    }

    private void init() {
        dialog = new Dialog(context, R.style.translucentDialogStyle);
        Window window = dialog.getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_common);
        dialog.setCanceledOnTouchOutside(canceledOntouchOutside);
        WindowManager m = ((Activity) context).getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = window.getAttributes(); // 获取对话框当前的参数值
        int tempWidth = (int) (d.getWidth() / 1.38);
        int tempHeight = 0;
        if (dialogType == TYPE_CALL) {
            tempHeight = d.getHeight() / 7;
        } else {
            tempHeight = (int) (d.getHeight() / 4.18);
        }
        p.width = tempWidth;
        p.height = tempHeight;
        window.setAttributes(p);

        tagIv = (ImageView) dialog.findViewById(R.id.tagIv);
        tagIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });

        cancleTv = (TextView) dialog.findViewById(R.id.cancleTv);
        sureTv = (TextView) dialog.findViewById(R.id.sureTv);
        tipTv = (TextView) dialog.findViewById(R.id.tipTv);
        tv_sure = (TextView) dialog.findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.btnConfirm();
                }
                dismiss();
            }
        });
        sureTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogcallback != null) {
                    dialogcallback.btnConfirm();
                }
                dismiss();
            }
        });
        cancleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogcallback != null) {
                    dialogcallback.cancel();
                }
                dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (dismissCallback != null) {
                    dismissCallback.dialogDismiss();
                }

            }
        });
        sureTv.setBackgroundResource(R.drawable.dialog_r_bg);
        tv_sure.setBackgroundResource(R.drawable.dialog_bottom_corner_bg);
        cancleTv.setBackgroundResource(R.drawable.dialog_l_bg);
        lineView = (View) dialog.findViewById(R.id.lineView);
        selectLin = (LinearLayout) dialog.findViewById(R.id.selectLin);
        if (dialogType == TYPE_SHOW) {
            lineView.setVisibility(View.GONE);
            selectLin.setVisibility(View.GONE);
        } else if (dialogType == TYPE_SELECT) {
            lineView.setVisibility(View.VISIBLE);
            selectLin.setVisibility(View.VISIBLE);
        } else if (dialogType == TYPE_SHOW_BTN) {
            selectLin.setVisibility(View.GONE);
            tv_sure.setVisibility(View.VISIBLE);
        } else if (dialogType == TYPE_PB) {
            lineView.setVisibility(View.GONE);
            selectLin.setVisibility(View.GONE);
            tagIv.setImageResource(R.mipmap.pb_login);
            animation = getAnimation();
            tagIv.startAnimation(getAnimation());
        } else if (dialogType == TYPE_CALL) {
            tagIv.setVisibility(View.GONE);
        }
    }

    public Animation animation;

    public void setTagIvAnimation() {
        if (tagIv != null) {
            animation = getAnimation();
            tagIv.startAnimation(animation);
        }
    }

    public void cancleAnimation() {
        if (animation != null) {
            animation.cancel();
        }
    }

    private Animation getAnimation() {
        Animation animation = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());//不停顿  
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(-1);
        return animation;
    }

    public void setTagImage(int id) {
        tagIv.setImageResource(id);
    }

    public void setTagImageDrawable(Drawable drawable) {
        tagIv.setBackgroundDrawable(drawable);
    }

    public void setSureText(String sure) {
        tv_sure.setText(sure);
        sureTv.setText(sure);
    }

    public void setSureText(SpannableString sure) {
        tv_sure.setText(sure);
        sureTv.setText(sure);
    }

    public void setcancleText(String cancle) {
        cancleTv.setText(cancle);
    }

    public void setcancleText(SpannableString cancle) {
        cancleTv.setText(cancle);
    }

    public void setTipText(String tipText) {
        tipTv.setText(tipText);
    }

    public void setTipText(SpannableString tipText) {
        tipTv.setText(tipText);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setDialogCallback(Dialogcallback dialogcallback) {
        this.dialogcallback = dialogcallback;
    }

    public void show() {

        try {
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hide() {
        dialog.hide();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public void setSureTextColor(int color) {

        sureTv.setTextColor(color);
        tv_sure.setTextColor(color);
    }

    public boolean isShowing() {
        if (dialog.isShowing()) {
            return true;
        }
        return false;
    }

    public void setCanceledOnTouchOutside(boolean bool) {
        dialog.setCanceledOnTouchOutside(bool);
    }

    public void setCancleTextColor(int color) {
        cancleTv.setTextColor(color);
    }

    /**
     * 两个按钮的回调
     */
    public interface Dialogcallback {
        public void btnConfirm();

        public boolean cancel();
    }

    /**
     * 一个个按钮的回调
     */
    public interface Callback {
        public void btnConfirm();
    }

    public interface DialogDismissCallBack {
        public void dialogDismiss();
    }

    public void setDialogDismissCallback(DialogDismissCallBack dismissCallback) {
        this.dismissCallback = dismissCallback;
    }
}