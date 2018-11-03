package android.academy.nytimes.mvp

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.state.Resource
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val model: Model) : ViewModel() {

    private val liveData = MutableLiveData<Resource<List<NewsItem>>>()
    private var category = ""

    fun fetchNews(category: String) {
        if (this.category == category) return
        liveData.postValue(Resource.loading())
        model.loadNews(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { liveData.postValue(Resource.success(it)) }
                .doOnError { liveData.postValue(Resource.error(it.localizedMessage)) }
                .subscribe()
    }

    fun loadCategories() = model.loadCategories()

    fun getData() = liveData

}
