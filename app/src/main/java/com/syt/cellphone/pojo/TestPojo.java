package com.syt.cellphone.pojo;

/**
 * @author shenyutian
 * @data 2020-01-16 09:18
 * 功能 测试布局属性类
 */
public class TestPojo {

    public static final int TYPE_NONO  = -1;
    public static final int TYPE_ZERO  = 0;
    public static final int TYPE_ONE   = 1;
    public static final int TYPE_TWO   = 2;
    public static final int TYPE_THTEE   = 3;

    /**
     *  type  布局类型  对应应该是每个分类的view
     */
    private int id;
    private int type;
    private int index;
    private int width;
    private int height;

    public TestPojo() {
    }

    public TestPojo(int id, int type, int index, int width, int height) {
        this.id = id;
        this.type = type;
        this.index = index;
        this.width = width;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
