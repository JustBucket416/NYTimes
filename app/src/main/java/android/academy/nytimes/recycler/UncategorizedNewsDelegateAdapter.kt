package android.academy.nytimes.recycler

import android.academy.nytimes.R
import android.academy.nytimes.data.NewsItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.delegateadapter.delegate.BaseDelegateAdapter
import com.example.delegateadapter.delegate.BaseViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_another_item.*
import java.text.SimpleDateFormat

class UncategorizedNewsDelegateAdapter(private val onClick: (String) -> Unit)
    : BaseDelegateAdapter<UncategorizedNewsDelegateAdapter.UncategorizedNewHolder, NewsItem>() {

    override fun getLayoutId() = R.layout.recycler_another_item

    override fun isForViewType(p0: MutableList<*>, p1: Int) = (p0[p1] as NewsItem).category == null

    override fun onBindViewHolder(p0: View, p1: NewsItem, p2: UncategorizedNewHolder) {
        if (p1.imageUrl != null)
            Glide.with(p2.imageView.context)
                    .load(p1.imageUrl)
                    .into(p2.imageView)

        p2.textCont.text = p1.previewText
        p2.textTitle.text = p1.title
        p2.textTime.text = SimpleDateFormat.getDateInstance().format(p1.publishDate)

        p2.cardView.setOnClickListener { onClick(p1.url) }
    }

    override fun createViewHolder(parent: View?): UncategorizedNewHolder {
        return UncategorizedNewHolder(parent)
    }

    class UncategorizedNewHolder(override val containerView: View?)
        : BaseViewHolder(containerView), LayoutContainer
}