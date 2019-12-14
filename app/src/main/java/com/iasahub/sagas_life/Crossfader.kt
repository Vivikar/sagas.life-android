package com.iasahub.sagas_life
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ViewSwitcher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class Crossfader constructor(
    context: Context,
    attrs: AttributeSet
) : ViewSwitcher(context, attrs) {

    private var switchAction: Runnable? = null

    init {
        setTransition()

        addImageViews()
    }

    fun crossfade(imageUrl: String?) {

        (nextView as? ImageView)?.let { nextImageView ->
            loadImage(nextImageView, imageUrl, SwitchImageWhenReady(nextImageView))
        }
    }

    private inner class SwitchImageWhenReady(private val imageView: ImageView) : RequestListener<Drawable> {

        init {
            switchAction?.let { imageView.removeCallbacks(it) }
        }

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {

            switchAction = Runnable { showNext() }

            imageView.post(switchAction)

            return false
        }
    }

    private fun loadImage(imageView: ImageView, imageUrl: String?, listener: RequestListener<Drawable>) {
        Glide.with(imageView)
            .load(imageUrl)
            .listener(listener)
            .into(imageView)

    }

    private fun setTransition() {
        inAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        inAnimation.duration = 3222
        outAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        outAnimation.duration = 3222
    }

    private fun addImageViews() {
        addView(ImageView(context))
        addView(ImageView(context))
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)

        child?.visibility =
            if (childCount == 1) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
    }

}
