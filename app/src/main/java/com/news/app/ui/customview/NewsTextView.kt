package com.news.app.ui.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.news.app.R
/**
 * This  Class for to create custom textviews
 *
 * @author Srisailam
 * @version 1.0
 * @since   2018-12-07
 */
class NewsTextView : TextView {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        style(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        style(context, attrs)

    }

    private fun style(context: Context, attrs: AttributeSet) {

        val a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomFontTextView)
        val cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0)
        var fontName = 0
        when (cf) {
            1 -> fontName = R.string.montserrat_bold
            2 -> fontName = R.string.montserrat_light
            3 -> fontName = R.string.montserrat_medium
            4 -> fontName = R.string.montserrat_regular

            else -> fontName = R.string.montserrat_regular
        }

        val customFont = resources.getString(fontName)

        val tf = Typeface.createFromAsset(context.assets,
                "font/$customFont.otf")
        typeface = tf
        a.recycle()
    }
}