package com.carter.javaAndroid.modules.main.contract;

import com.carter.javaAndroid.base.presenter.IPresenter;
import com.carter.javaAndroid.base.view.IView;

public interface CollectEventContract {

    interface View extends IView {
        void showCollectSuccess(int position);

        void showCancelCollectSuccess(int position);
    }

    interface Presenter<V extends View> extends IPresenter<V> {
        void addCollectArticle(int position, int id);

        void cancelCollectArticle(int position, int id);
    }

}
