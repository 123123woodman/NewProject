package wangjie.com.library.adapter;


import java.io.Serializable;
import java.util.List;

/**
 * Created by someHui on 3/24/16.
 */
public interface IPageModel<T extends Serializable> extends IModel {
    void setList(List<T> list);
    void addList(List<T> list);
    List<T> getList();
    T get(int position);
    int getCount();
}
