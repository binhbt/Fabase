package com.vn.fa.base.helper;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by binhbt on 8/31/2016.
 */
public class RxHelper {
    public interface IFunction {
        public Object doFunction(Object params);
    }

    public static <T> Observable<T> makeObservable(final IFunction function, final Object params) {
        // Note that below code is not optimal but it helps in demonstration of concepts
        // A better version is shown in the next section
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    T result = (T)function.doFunction(params);
                    subscriber.onNext(result);    // Pass on the data to subscriber
                    subscriber.onCompleted();     // Signal about the completion subscriber
                } catch (Exception e) {
                    subscriber.onError(e);        // Signal about the error to subscriber
                }
            }
        });
    }
}
