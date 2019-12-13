package com.syt.cellphone.ui;

import android.content.Intent;

import com.syt.cellphone.base.BasePresenter;
import com.syt.cellphone.base.Config;
import com.syt.cellphone.util.SharedConfigUtil;

/**
 * @author：syt
 * Date: 2019-12-05
 * 作用: 主 控制数据model的 P
 */
public class SytMainPresenter extends BasePresenter<SytMainView> {

    public SytMainPresenter(SytMainView baseView) {
        super(baseView);
    }

    /**
     *     选择底部菜单
     *        判定活动启动时，有没有参数
     *        判定运行中 是否有底部参数
     *        判定默认的启动底部参数
     *        等级 传入的底部参数 > config中的底部参数 > 默认的参数
     */
    protected void switchMenus(Intent intent) {
        int bottomMenu = Config.getBottomMenu();
        bottomMenu = intent.getIntExtra("param", bottomMenu);
        int switchMenusNum = SharedConfigUtil.getBottomMenus();

        // 通知主视图显示底部菜单 + 加载碎片 0为不存在，加载默认的。
        baseView.showFragment(bottomMenu == 0 ? switchMenusNum : bottomMenu);
    }


}
