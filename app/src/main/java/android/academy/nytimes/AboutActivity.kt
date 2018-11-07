package android.academy.nytimes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_include.*


class AboutActivity : AppCompatActivity() {

    companion object {
        private const val EMAIL = "justbucket416@gmail.com"
        private const val SUBJECT = "NYTimes"
        private const val DISCLAIMER = "© 2018 Roman"

        fun newIntent(context: Context): Intent {
            return Intent(context, AboutActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        buttonEmail.setOnClickListener {
            val intent = makeSendIntent(textMessage.text.toString())
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Snackbar.make(layout_about, R.string.aboutactivity_no_app_found, Snackbar.LENGTH_LONG).show()
            }
        }

        showCopyRight()
    }

    private fun showCopyRight() = with(TextView(this)) {
        text = DISCLAIMER
        gravity = Gravity.CENTER
        layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        }

        layout_about.addView(this)
    }

    private fun makeSendIntent(text: String) =
            Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, EMAIL)
                putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
                putExtra(Intent.EXTRA_TEXT, text)
            }
}
