package com.bq2015.bqlib.event;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

/**
 * @version V1.0
 * @Description: 事件总线
 * @author: KyLin (SRS)
 * @date: 2016/8/18 11:14
 */
public class RxBus {

    private final SerializedSubject<Object, Object> mBus;

    //构造函数私有化
    private RxBus() {
        mBus = new SerializedSubject<>(PublishSubject.create());
    }

    //对外暴露获取单列的公共方法
    public static RxBus getInstance() {
        return RxBusHolder.instance;
    }

    //缓存单例对象
    private static  class RxBusHolder {
        private volatile static RxBus instance = new RxBus();
    }


    /**
     * 发送一个新的事件
     */
    public void postEvent(Object obj) {
        mBus.onNext(obj);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }
}
