package com.mindinventory.circularcardsstackview.util

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue

/**
 * This class contains display utility functions
 * @author MindInventory
 */
object DisplayUtils {

    /**
     * Uses for convert DP to Pixel
     */
    fun dpToPx(dp: Float, context: Context): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            context.resources.displayMetrics
        )
    }

    /**
     * Uses for get screen size
     */
    fun getScreenSize(context: Context): ScreenSize {
        return when (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) {
            Configuration.SCREENLAYOUT_SIZE_LARGE -> ScreenSize.LARGE
            Configuration.SCREENLAYOUT_SIZE_NORMAL -> ScreenSize.NORMAL
            else -> throw Exception("Screen size is neither large, normal or small")
        }
    }
}