package android.academy.nytimes

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
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
    }

    override fun onStart() {
        super.onStart()

        button_email.setOnClickListener {
            val intent = makeSendIntent(text_message.text.toString())
            if (intent.resolveActivity(packageManager) != null)
                startActivity(intent)
            else
                Toast.makeText(this@AboutActivity,
                        R.string.no_app_found, Toast.LENGTH_SHORT).show()
        }

        showCopyRight()
    }

    private fun showCopyRight() {
        val textView = TextView(this)
        textView.text = DISCLAIMER
        val params = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        textView.gravity = Gravity.CENTER
        textView.layoutParams = params
        layout_about.addView(textView)

    }

    private fun makeSendIntent(text: String): Intent {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, EMAIL)
        intent.putExtra(Intent.EXTRA_SUBJECT, SUBJECT)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        return intent
    }
}
