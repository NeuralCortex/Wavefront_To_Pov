package com.fx.w2p.pojo;

/**
 *
 * @author pscha
 */
public class ObjIndex {

    private int idx;
    private String objName;

    public ObjIndex(int idx, String objName) {
        this.idx = idx;
        this.objName = objName;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }
}
