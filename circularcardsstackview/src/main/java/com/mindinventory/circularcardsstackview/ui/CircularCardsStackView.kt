package com.mindinventory.circularcardsstackview.ui

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.mindinventory.circularcardsstackview.R
import com.mindinventory.circularcardsstackview.adapter.CardStackAdapter
import com.mindinventory.circularcardsstackview.data.CardModel
import com.mindinventory.circularcardsstackview.databinding.CircularCardsStackViewBinding
import com.mindinventory.circularcardsstackview.listener.CardActionListener
import com.mindinventory.circularcardsstackview.listener.OnPageChangeListener
import com.mindinventory.circularcardsstackview.util.DisplayUtils
import com.mindinventory.circularcardsstackview.util.ScreenSize
import com.mindinventory.circularcardsstackview.util.SliderTransformerHorizontal
import kotlin.math.roundToInt

/**
 * This class uses for set CardCircularStackView properties
 * @author MindInventory
 */
class CircularCardsStackView(context: Context, attrs: AttributeSet) :
    ConstraintLayout(context, attrs) {
    private var binding: CircularCardsStackViewBinding? = null
    private var screenSize: ScreenSize? = null
    private var cardStackAdapter: CardStackAdapter
    private var onPageChangeListener: OnPageChangeListener? = null
    private var inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        binding = CircularCardsStackViewBinding.inflate(inflater, this, true)
        initScreenSize()

        val typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CircularCardsStackView)

        var primaryTextFontFamily: Typeface? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            primaryTextFontFamily = typedArray.getFont(
                R.styleable.CircularCardsStackView_primaryTextFontFamily
            )
        }

        var secondaryTextFontFamily: Typeface? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            secondaryTextFontFamily = typedArray.getFont(
                R.styleable.CircularCardsStackView_secondaryTextFontFamily
            )
        }

        cardStackAdapter = CardStackAdapter(
            cardStrokeColor = typedArray.getColor(
                R.styleable.CircularCardsStackView_stackCardStrokeColor,
                ContextCompat.getColor(context, R.color.white)
            ),
            cardBackgroundColor = typedArray.getColor(
                R.styleable.CircularCardsStackView_stackCardBackgroundColor,
                ContextCompat.getColor(context, R.color.white)
            ),
            primaryTextColor = typedArray.getColor(
                R.styleable.CircularCardsStackView_primaryTextColor,
                Color.WHITE
            ),
            secondaryTextColor = typedArray.getColor(
                R.styleable.CircularCardsStackView_secondaryTextColor,
                Color.WHITE
            ),
            profileViewBackgroundColor = typedArray.getColor(
                R.styleable.CircularCardsStackView_profileViewBackgroundColor,
                ContextCompat.getColor(context, R.color.white)
            ),
            cardStrokeWidth = typedArray.getDimension(
                R.styleable.CircularCardsStackView_stackCardStrokeWidth,
                resources.getDimension(R.dimen._2dp)
            ).roundToInt(),
            cardCornerRadius = typedArray.getDimension(
                R.styleable.CircularCardsStackView_stackCardCornerRadius,
                resources.getDimension(R.dimen._20sdp)
            ),
            cardChildPadding = typedArray.getDimension(
                R.styleable.CircularCardsStackView_stackCardChildPadding,
                resources.getDimension(R.dimen._15sdp)
            ).roundToInt(),
            primaryTextSize = typedArray.getDimensionPixelSize(
                R.styleable.CircularCardsStackView_primaryTextSize,
                resources.getDimension(R.dimen._16ssp).roundToInt()
            ),
            secondaryTextSize = typedArray.getDimensionPixelSize(
                R.styleable.CircularCardsStackView_secondaryTextSize,
                resources.getDimension(R.dimen._14ssp).roundToInt()
            ),
            childViewHeight = typedArray.getDimensionPixelSize(
                R.styleable.CircularCardsStackView_childViewHeight,
                resources.getDimensionPixelSize(R.dimen._300sdp)
            ),
            actionOptionsVisibility = typedArray.getBoolean(
                R.styleable.CircularCardsStackView_actionOptionsVisibility,
                true
            ),
            primaryTextFontFamily = primaryTextFontFamily,
            secondaryTextFontFamily = secondaryTextFontFamily,
        )

        binding?.vpCardStack?.adapter = cardStackAdapter

        typedArray.recycle()
    }

    /**
     * Uses for get screen size
     */
    private fun initScreenSize() {
        screenSize = DisplayUtils.getScreenSize(context)
    }

    /**
     * Uses for set action button
     */
    fun setActionOptions(
        @DrawableRes firstButtonResourceId: Int?,
        @DrawableRes secondButtonResourceId: Int?,
        cardActionListener: CardActionListener
    ) {
        cardStackAdapter.setActionOptions(firstButtonResourceId, secondButtonResourceId)
        cardStackAdapter.setCardStackListener(cardActionListener)
    }

    /**
     * Uses for set card items
     */
    fun setUpCardStack(items: ArrayList<CardModel>, onPageChangeListener: OnPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener

        cardStackAdapter.setItems(items)

        setPageTransformation(items)

        registerViewPager()

        val number: Int = Int.MAX_VALUE / items.size / 2
        binding?.vpCardStack?.setCurrentItem(number * items.size, false)
    }

    /**
     * Uses for register viewpager for callBack
     */
    private fun registerViewPager() {
        binding?.vpCardStack?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val positionInList = position % (cardStackAdapter.getItems().size)
                onPageChangeListener?.onPageSelected(positionInList)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                onPageChangeListener?.onPageScrollStateChanged(state)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                onPageChangeListener?.onPageScrolled(position % (cardStackAdapter.getItems().size))
            }
        })
    }

    /**
     * Uses for set page transformation
     */
    private fun setPageTransformation(items: ArrayList<CardModel>) {
        val numberOfCards = items.size
        val translationFactor = when (Resources.getSystem().displayMetrics.heightPixels) {
            in 500..1000 -> {
                7f
            }
            in 1000..1500 -> {
                6f
            }
            in 1500..2000 -> {
                4.0f
            }
            in 2000..2500 -> {
                3.3f
            }
            in 2500..3000 -> {
                3.0f
            }
            else -> {
                3.5f
            }
        }
        if (numberOfCards >= 3) {
            binding?.vpCardStack?.offscreenPageLimit = 3
            binding?.vpCardStack?.setPageTransformer(
                SliderTransformerHorizontal(
                    3,
                    DisplayUtils.dpToPx(translationFactor, context)
                )
            )
        } else {
            binding?.vpCardStack?.offscreenPageLimit = numberOfCards
            binding?.vpCardStack?.setPageTransformer(
                SliderTransformerHorizontal(
                    numberOfCards,
                    DisplayUtils.dpToPx(translationFactor, context)
                )
            )
        }
    }
}