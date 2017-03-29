package com.oridway.videopush.net;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by lihao on 2016/12/17.
 */

public class PageSet implements KvmSerializable {

    public int pageSize;
    public int curPage;
    public int rows;
    public int pages;

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return pageSize;
            case 1:
                return curPage;
            case 2:
                return rows;
            case 3:
                return pages;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                pageSize = (Integer) o;
                break;
            case 1:
                curPage = (Integer) o;
                break;
            case 2:
                rows = (Integer) o;
                break;
            case 3:
                pages = (Integer) o;
                break;
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "pageSize";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "curPage";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "rows";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "pages";
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "PageSet{" +
                "pageSize=" + pageSize +
                ", curPage=" + curPage +
                ", rows=" + rows +
                ", pages=" + pages +
                '}';
    }
}
