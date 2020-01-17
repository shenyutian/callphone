package com.syt.cellphone.util;

import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.syt.cellphone.pojo.TestPojo;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-16 16:46
 * 功能 自定义布局管理器
 *
 * 写绘画布局算法
 * 判定单元格是否被画  ->  画了 -> 移动画笔 到下一个单元格
 * -> 单元格干净 -> 画布局 -> 记录画笔区域
 */
public class TestLayoutManager extends RecyclerView.LayoutManager {

    /**
     * 设置管理器的外边距  四边距暂时统一
     * @param margin 单位 px 16
     */
    public void setMargin(int margin) {
        this.margin = margin;
    }

    /**
     * itemWidth    根据布局计算的item宽度
     * itemHeight   根据布局计算的item高度
     * bools        判定格子是否被占用
     * pojoList     布局的bean集合
     */
    private int             itemWidth;
    private int             itemHeight;
    private List<TestPojo>  pojoList;
    private boolean[][]     bools;
    private String TAG =    "Test";


    public TestLayoutManager(int width, int height, List<TestPojo> pojos) {
        this.itemWidth = width/maxWidth;
        this.itemHeight = height/maxHeight;
        pojoList = pojos;
    }

    /**
     * 设置 布局的最大宽高
     * MARGIN       外边距
     */
    private int maxWidth  = 5;
    private int maxHeight = 4;
    private int margin    = 16;

    /**
     * 设置管理器的最大宽度
     * @param maxWidth 最大宽度  单位 int  默认为 4
     */
    public void setMaxWidth(int maxWidth) {
        itemWidth  = itemWidth * 4 /maxWidth;
        this.maxWidth = maxWidth;
    }

    /**
     * 设置管理器的最大高度
     * @param maxHeight 最大高度 单位 int  默认4
     */
    public void setMaxHeight(int maxHeight) {
        itemHeight = itemHeight * 4 / maxHeight;
        this.maxHeight = maxHeight;
    }


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    /**
     * allItemFrames    保存item 的 上下左右的信息
     *
     * hasAttachedItems 保存item是否出现在屏幕上，且还没有被回收。
     * true 表示出现过屏幕上，并且还没有被回收
     */
    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    private SparseBooleanArray hasAttachedItems = new SparseBooleanArray();


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 判定item数据 空就不加载
        if (getItemCount() <= 0) {
            return;
        }
        // 布局前 ， 将 所有子view 先 detach 掉, 放入到Scrap 缓存中
        detachAndScrapAttachedViews(recycler);
        Log.d(TAG, "onLayoutChildren: itemWidth = " + itemWidth + "\titemHeight = " + itemHeight);

        // 必要的参数进行初始化
        bools = new boolean[maxHeight][maxWidth];
        // 下面四个参数是基础的item的位置 单位 int
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        for (int i = 0; i < maxHeight; i++) {
            for (int j = 0; j < maxWidth; j++) {
                bools[i][j] = true;
            }
        }

        for (int i = 0; i < getItemCount(); i++) {
            // 里就是缓存中取出
            View view = recycler.getViewForPosition(i);
            // 将View 加入 RecyclerView 中
            addView(view);
            // 对子View进行测量
            measureChildWithMargins(view, 0, 0);
            right = left + pojoList.get(i).getWidth();
            bottom = top + pojoList.get(i).getHeight();
            // 默认为单元格全白
            boolean noWrite = true;
            // 判定单元格是否被画
            do {
                noWrite = true;
                // 只要有一个单元格黑了，就跳转到下一个单元格
                for (int k = top; k < bottom && k < maxHeight; k++) {
                    for (int j = left; j < right && j < maxWidth; j++) {
                        if (!bools[k][j]) {
                            Log.d(TAG, "onLayoutChildren: k = " + k + "\t j = " + j);
                            noWrite = false;
                        }
                    }
                }
                // 如果 右 or 下 越过边界。移动到下一个绘画点
                if (right > maxWidth || bottom > maxHeight) {
                    noWrite = false;
                }
                // 如果 左 or 上 越过边界。 直接跳出循环
                if (left >= maxWidth || top >= maxHeight) {
                    // 后面的布局不画了
                    return;
                }
                if (!noWrite) {
                    // 画点的偏移
                    left +=  1;
                    if (left >= maxWidth) {
                        left = 0;
                        top += 1;
                    }
                    right = left + pojoList.get(i).getWidth();
                    bottom = top + pojoList.get(i).getHeight();
                }
                Log.d(TAG, "onLayoutChildren: noWrite = " + noWrite);
            } while (!noWrite);
            // 真正的画布局
            layoutDecorated(view, left * itemWidth + margin, top * itemHeight + margin,
                        right * itemWidth - margin, bottom * itemHeight - margin);
            Log.d(TAG, "onLayoutChildren: left = " + left + "\t top = " + top +
                    "\tright = " + right + "\tbottom = " + bottom);

            // 将当前的Item的Rect边界数据保存
            Rect frame = allItemFrames.get(i);
            frame.set(left * itemWidth + margin,
                    top * itemHeight + margin,
                    right * itemWidth + margin,
                    bottom * itemHeight - margin);
            allItemFrames.append(i, frame);

            // 由于已经调用了detachAndScrapAttachedViews，因此需要将当前的Item设置为未出现过
            hasAttachedItems.put(i, false);

            // 对画格进行进行占用 -> 描黑
            for (int k = top; k < bottom && k < maxHeight; k++) {
                for (int j = left; j < right && j < maxWidth; j++) {
                    bools[k][j] = false;
                }
            }

            // 回收 item
            recyclerAndFillItems(recycler, state);

            // 画点 left top 进行偏移
            left +=  1;
            if (left >= maxWidth) {
                left = 0;
                top += 1;
            }
            if (top > maxHeight - 1) {
                return;
            }
        }
    }

    // 由于当前布局没有滑动，暂时不起作用
    private void recyclerAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {

        Rect childFrame = new Rect();

        /**
         * 屏幕上不显示的 items 回收到 Recycle 缓存中
         */
        for (int i = 0, length = getChildCount(); i < length; i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            //如果Item没有在显示区域，就说明需要回收
//            if (!Rect.intersects(displayFrame, childFrame)) {
                //回收掉滑出屏幕的View
//                removeAndRecycleView(child, recycler);
//            }
        }
        /**
         * 重现显示在屏幕上的字 item
         */
        for (int i = 0, length = getItemCount(); i < length; i++) {
            View scrap = recycler.getViewForPosition(i);
            measureChildWithMargins(scrap, 0, 0);
            addView(scrap);

            Rect frame = allItemFrames.get(i);
            layoutDecorated(scrap,
                    frame.left, frame.top, frame.right, frame.bottom);
        }
    }
}
