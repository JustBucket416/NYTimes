package android.academy.nytimes

import android.academy.nytimes.data.NewsItem
import android.academy.nytimes.di.ViewModelFactory
import android.academy.nytimes.mvp.MainViewModel
import android.academy.nytimes.recycler.CategorizedNewsDelegateAdapter
import android.academy.nytimes.recycler.UncategorizedNewsDelegateAdapter
import android.academy.nytimes.state.Resource
import android.academy.nytimes.state.ResourceState
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.delegateadapter.delegate.CompositeDelegateAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var adapter: CompositeDelegateAdapter<NewsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        mainViewModel.getData().observe(this, Observer { handleNewsDataState(it) })

        recyclerView.layoutManager = GridLayoutManager(this,
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) 2
                else 1
        )

        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect,
                                        view: View,
                                        parent: RecyclerView,
                                        state: RecyclerView.State) {
                outRect.left = resources.getDimension(R.dimen.recycler_item_offset).toInt()
                outRect.bottom = resources.getDimension(R.dimen.recycler_item_offset).toInt()
            }
        })

        adapter = CompositeDelegateAdapter.Builder<NewsItem>()
                .add(CategorizedNewsDelegateAdapter(::onHolderClicked))
                .add(UncategorizedNewsDelegateAdapter(::onHolderClicked))
                .build()


        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu.findItem(R.id.spinner)
        val spinner = menuItem.actionView as Spinner

        val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, mainViewModel.loadCategories())
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                mainViewModel.fetchNews(mainViewModel.loadCategories()[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.about) startActivity(AboutActivity.newIntent(this))
        return true
    }

    private fun onHolderClicked(url: String) {
        startActivity(DetailActivity.newIntent(this, url))
    }

    private fun handleNewsDataState(resource: Resource<List<NewsItem>>?) {
        if (resource == null) return
        when (resource.status) {
            ResourceState.LOADING -> showLoading()
            ResourceState.SUCCESS -> showItems(resource.data)
            ResourceState.ERROR -> showError()
        }
    }

    private fun showItems(items: List<NewsItem>?) {
        loading_group.visibility = View.GONE
        if (items?.isNotEmpty() == true) {
            error_group.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            adapter.swapData(items)
        } else error_group.visibility = View.VISIBLE
    }

    private fun showError() {
        loading_group.visibility = View.GONE
        recyclerView.visibility = View.GONE
        error_group.visibility = View.VISIBLE
    }

    private fun showLoading() {
        recyclerView.visibility = View.GONE
        error_group.visibility = View.GONE
        loading_group.visibility = View.VISIBLE
    }
}
