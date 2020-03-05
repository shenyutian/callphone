package com.syt.cellphone.adapter;

import android.view.View;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.syt.cellphone.R;
import com.syt.cellphone.pojo.Estimate;
import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.util.TimeUtil;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author shenyutian
 * @data 2020-01-20 09:02
 * 功能 主布局Rv列表适配器
 */
public class DetailsAdapter extends BaseNodeAdapter {

    public DetailsAdapter() {
        // 需要占满一行的，使用此方法（例如section）
        addFullSpanNodeProvider(new RootNodeProvider());
        // 普通的item provider
        addNodeProvider(new ChildNodeProvider());
        // 设置标题布局 provider  item
        addNodeProvider(new TitleProvider());
        // 设置评论提供者
        addNodeProvider(new EstimateProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode baseNode = list.get(i);
        if (baseNode instanceof RootNode) {
            return 1;
        } else if (baseNode instanceof ChildNode) {
            return 2;
        } else if (baseNode instanceof TitleNode) {
            return 0;
        } else if (baseNode instanceof EstimateNode) {
            return 3;
        }
        return -1;
    }

    /**
     * 标题 内容的节点布局
     */
    public class TitleProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 0;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_details_title;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @org.jetbrains.annotations.Nullable BaseNode baseNode) {
            TitleNode titleNode = (TitleNode) baseNode;
            baseViewHolder
                    .setText(R.id.tv_details_title_price, "￥" + titleNode.getPrice())
                    .setText(R.id.tv_details_title_feature, titleNode.getFeature())
                    .setText(R.id.tv_details_title_name, titleNode.getName());
        }
    }

    /**
     * 第0个节点TitleNode，标题节点。用来放标题内容
     */
    public static class TitleNode extends BaseNode {

        private PhoneBase phoneBase;

        public TitleNode(PhoneBase phoneBase) {
            this.phoneBase = phoneBase;
        }

        public String getName() {
            return phoneBase.getBaseName();
        }

        public String getFeature() {
            return phoneBase.getBaseFeature();
        }

        public String getPrice() {
            return phoneBase.getBasePrice();
        }

        public PhoneBase getPhoneBase() {
            return phoneBase;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }

    /**
     * 根节点 = 参数列表的标题
     */
    public class RootNodeProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 1;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_root_head;
        }

        @Override
        public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
            // 数据类型需要自己强转
            RootNode entity = (RootNode) data;
            helper.setText(R.id.tv_details_root_head, entity.getTitle());
        }

        @Override
        public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
            getAdapter().expandOrCollapse(position);
        }
    }

    /**
     * 第一个节点RootNode，里面放子节点ChildNode
     * 这也是小标题栏
     */
    public static class RootNode extends BaseExpandNode {

        private List<BaseNode> childNode;
        private String title;

        public RootNode(List<BaseNode> childNode, String title) {
            this.childNode = childNode;
            this.title = title;
            // 设置默认不展开
            setExpanded(false);
        }

        public String getTitle() {
            return title;
        }

        /**
         * 重写此方法，返回子节点
         */
        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return childNode;
        }
    }

    /**
     * 子节点类
     */
    public class ChildNodeProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 2;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_second_node;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable BaseNode baseNode) {
            ChildNode childNode = (ChildNode) baseNode;
            baseViewHolder.setText(R.id.tv_details_second_node, childNode.getTitle());
            baseViewHolder.setText(R.id.tv_details_second_data, childNode.getData());

        }
    }

    /**
     * 第二个节点ChildNode，里面没有子节点了
     */
    public static class ChildNode extends BaseNode {

        private String title;
        private String data;

        public ChildNode(String title, String data) {
            this.title = title;
            this.data = data;
        }

        public String getTitle() {
            return title;
        }

        public String getData() {
            return data;
        }

        /**
         * 重写此方法，返回子节点
         */
        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }

    /**
     * 评价类 提供者
     */
    public class EstimateProvider extends BaseNodeProvider {

        @Override
        public int getItemViewType() {
            return 3;
        }

        @Override
        public int getLayoutId() {
            return R.layout.item_estimate;
        }

        @Override
        public void convert(@NotNull BaseViewHolder baseViewHolder, @org.jetbrains.annotations.Nullable BaseNode baseNode) {
            EstimateNode estimate = (EstimateNode) baseNode;
            baseViewHolder.setText(R.id.tv_item_estimate_content, estimate.getEstimateComment());
            baseViewHolder.setText(R.id.tv_item_estimate_time, estimate.getEstimateTime());
        }
    }

    /**
     * 评论 节点
     */
    public static class EstimateNode extends BaseNode {

        private Estimate estimate;

        public EstimateNode(Estimate estimate) {
            this.estimate = estimate;
        }

        public String getEstimateComment() {
            return estimate.getEstimateComment();
        }

        public String getEstimateTime() {
            return TimeUtil.getEstimateTime(estimate.getEstimateTime());
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }
}
