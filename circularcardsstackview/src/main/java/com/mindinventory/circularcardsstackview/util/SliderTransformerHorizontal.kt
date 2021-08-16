package com.mindinventory.circularcardsstackview.util

import android.util.Log
import android.view.View
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * This class uses for set page slide transformation
 * @author MindInventory
 */
class SliderTransformerHorizontal(
    private val offscreenPageLimit: Int,
    private val defaultTranslationFactor: Float
) :
    ViewPager2.PageTransformer {

    companion object {
        private const val DEFAULT_TRANSLATION_X = .0f
        private const val DEFAULT_TRANSLATION_Y = .0f
        private const val DEFAULT_TRANSLATION_FACTOR_X = 1f

        private const val SCALE_FACTOR = .10f
        private const val DEFAULT_SCALE = 1f

        private const val ALPHA_FACTOR = .0f
        private const val DEFAULT_ALPHA = 1f

        private const val TAG = "SliderTransformer"
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            ViewCompat.setElevation(page, -abs(position))

            val scaleFactor = -SCALE_FACTOR * position + DEFAULT_SCALE
            val alphaFactor = -ALPHA_FACTOR * position + DEFAULT_ALPHA

            when {
                position <= 0f -> {
                    translationX = DEFAULT_TRANSLATION_X
                    translationY = DEFAULT_TRANSLATION_Y
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    //Current visible
                    alpha = DEFAULT_ALPHA + position
                    Log.d(TAG, "Position => $position  TranslationX -> $translationX  TranslationY -> $translationY ScaleX -> $scaleX  ScaleY -> $scaleY Alpha -> $alpha"  )
                }
                position <= offscreenPageLimit - 1 -> {
                    scaleX = scaleFactor
                    scaleY = scaleFactor

                    // To Move Page From it's position to up side(plus side) on Y-Axis
                    translationY = (width / defaultTranslationFactor) * position

                    // To Move Page From it's position to left side(minus side) on X-Axis
                    translationX = -(width / DEFAULT_TRANSLATION_FACTOR_X) * position
                    alpha = alphaFactor
                }
                else -> {
                    translationX = DEFAULT_TRANSLATION_X
                    translationY = DEFAULT_TRANSLATION_Y
                    scaleX = DEFAULT_SCALE
                    scaleY = DEFAULT_SCALE
                    alpha = DEFAULT_ALPHA

                    Log.d(TAG, "Position else => $position  TranslationX -> $translationX  TranslationY -> $translationY ScaleX -> $scaleX  ScaleY -> $scaleY Alpha -> $alpha")
                }
            }
        }
    }
}