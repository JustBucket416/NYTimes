package android.academy.nytimes.recycler

import android.academy.nytimes.R
import android.academy.nytimes.data.UncategorizedNewsItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.delegateadapter.delegate.BaseDelegateAdapter
import com.example.delegateadapter.delegate.BaseViewHolder
import kotlinx.android.synthetic.main.recycler_another_item.view.*
import java.text.SimpleDateFormat

class UncategorizedNewsDelegateAdapter(private val onClick: (String) -> Unit)
    : BaseDelegateAdapter<BaseViewHolder, UncategorizedNewsItem>() {

    override fun getLayoutId() = R.layout.recycler_another_item

    override fun isForViewType(items: MutableList<*>, index: Int) = items[index] is UncategorizedNewsItem

    override fun onBindViewHolder(view: View, item: UncategorizedNewsItem, holder: BaseViewHolder) = with(holder.itemView) {
        if (item.imageUrl != null)
            Glide.with(imageView.context)
                    .load(item.imageUrl)
                    .into(imageView)

        textCont.text = item.previewText
        textTitle.text = item.title
        textTime.text = SimpleDateFormat.getDateInstance().format(item.publishDate)

        cardView.setOnClickListener { onClick(item.url) }
    }

    override fun createViewHolder(parent: View?): BaseViewHolder {
        return BaseViewHolder(parent)
    }
}