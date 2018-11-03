package android.academy.nytimes;

import android.academy.nytimes.mvp.Model;
import android.academy.nytimes.mvp.Presenter;
import android.academy.nytimes.mvp.RemoteRepository;
import android.academy.nytimes.network.RemoteRepositoryImpl;
import android.academy.nytimes.network.RetrofitHelper;
import android.app.Application;

/**
 * DI graph root
 */
public class NYApp extends Application {

    private final RetrofitHelper retrofitHelper = RetrofitHelper.getInstance();
    private final RemoteRepository remoteRepository = new RemoteRepositoryImpl(retrofitHelper);
    private final Model model = new Model(remoteRepository);
    private final Presenter presenter = new Presenter(model);

    public Presenter getPresenter() {
        return presenter;
    }
}
