package android.academy.nytimes.recycler

import android.academy.nytimes.R
import android.academy.nytimes.data.CategorizedNewsItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.delegateadapter.delegate.BaseDelegateAdapter
import com.example.delegateadapter.delegate.BaseViewHolder
import kotlinx.android.synthetic.main.recycler_item.view.*
import java.text.SimpleDateFormat

class CategorizedNewsDelegateAdapter(private val onClick: (String) -> Unit)
    : BaseDelegateAdapter<BaseViewHolder, CategorizedNewsItem>() {

    override fun getLayoutId() = R.layout.recycler_item

    override fun isForViewType(items: MutableList<*>, index: Int) = items[index] is CategorizedNewsItem

    override fun onBindViewHolder(view: View, item: CategorizedNewsItem, holder: BaseViewHolder) = with(holder.itemView) {
        if (item.imageUrl != null)
            Glide.with(imageView.context)
                    .load(item.imageUrl)
                    .into(imageView)

        textCont.text = item.previewText
        textTitle.text = item.title
        textCat.text = item.category
        textTime.text = SimpleDateFormat.getDateInstance().format(item.publishDate)

        cardView.setOnClickListener { onClick(item.url) }
    }

    override fun createViewHolder(parent: View?): BaseViewHolder {
        return BaseViewHolder(parent)
    }
}