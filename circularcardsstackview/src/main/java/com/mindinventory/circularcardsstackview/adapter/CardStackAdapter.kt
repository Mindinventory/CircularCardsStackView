package com.mindinventory.circularcardsstackview.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindinventory.circularcardsstackview.data.CardModel
import com.mindinventory.circularcardsstackview.databinding.ItemCardBinding
import com.mindinventory.circularcardsstackview.listener.CardActionListener
import java.io.File


/**
 * This class uses for set card list items
 * @author MindInventory
 */
class CardStackAdapter(
    @ColorInt cardStrokeColor: Int? = null,
    @ColorInt cardBackgroundColor: Int? = null,
    @ColorInt primaryTextColor: Int? = null,
    @ColorInt secondaryTextColor: Int? = null,
    @ColorInt profileViewBackgroundColor: Int? = null,
    @Dimension cardStrokeWidth: Int? = null,
    @Dimension cardCornerRadius: Float? = null,
    @Dimension cardChildPadding: Int? = null,
    @Dimension primaryTextSize: Int? = null,
    @Dimension secondaryTextSize: Int? = null,
    @Dimension childViewHeight: Int? = null,
    actionOptionsVisibility: Boolean? = null,
    primaryTextFontFamily: Typeface? = null,
    secondaryTextFontFamily: Typeface? = null
) :
    CircularAdapter<CardModel>() {

    companion object {
        const val DEFAULT_CARD_STROKE_WIDTH = 5
        const val DEFAULT_CARD_STROKE_COLOR = Color.BLACK
        const val DEFAULT_CARD_BACKGROUND_COLOR = Color.WHITE
        const val DEFAULT_CARD_CORNER_RADIUS = 50f
        const val DEFAULT_CARD_CHILD_PADDING = 10
        const val DEFAULT_PROFILE_VIEW_BACKGROUND_COLOR = Color.WHITE
    }

    private var cardStrokeColor: Int? = null
    private var cardStrokeWidth: Int? = null
    private var cardCornerRadius: Float? = null
    private var cardBackgroundColor: Int? = null
    private var cardChildPadding: Int? = null
    private var firstButtonId: Int? = null
    private var secondButtonId: Int? = null
    private var primaryTextSize: Int? = null
    private var secondaryTextSize: Int? = null
    private var primaryTextColor: Int? = null
    private var secondaryTextColor: Int? = null
    private var cardActionListener: CardActionListener? = null
    private var primaryTextFontFamily: Typeface? = null
    private var secondaryTextFontFamily: Typeface? = null
    private var profileViewBackgroundColor: Int? = null
    private var childViewHeight: Int? = null
    private var actionOptionsVisibility: Boolean? = null


    init {
        this.cardStrokeColor = cardStrokeColor
        this.cardStrokeWidth = cardStrokeWidth
        this.cardCornerRadius = cardCornerRadius
        this.cardBackgroundColor = cardBackgroundColor
        this.cardChildPadding = cardChildPadding
        this.primaryTextSize = primaryTextSize
        this.secondaryTextSize = secondaryTextSize
        this.primaryTextColor = primaryTextColor
        this.secondaryTextColor = secondaryTextColor
        this.primaryTextFontFamily = primaryTextFontFamily
        this.secondaryTextFontFamily = secondaryTextFontFamily
        this.profileViewBackgroundColor = profileViewBackgroundColor
        this.childViewHeight = childViewHeight
        this.actionOptionsVisibility = actionOptionsVisibility
    }

    override fun createItemViewHolder(parent: ViewGroup, viewType: Int): CardStackViewHolder {
        return CardStackViewHolder(
            ItemCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setActionOptions(
        firstButtonResourceId: Int?,
        secondButtonResourceId: Int?
    ) {
        this.firstButtonId = firstButtonResourceId
        this.secondButtonId = secondButtonResourceId
        notifyDataSetChanged()
    }

    fun setCardStackListener(cardActionListener: CardActionListener) {
        this.cardActionListener = cardActionListener
    }

    override fun bindItemViewHolder(
        holder: RecyclerView.ViewHolder,
        item: CardModel,
        actualPosition: Int,
        position: Int,
    ) {

        (holder as CardStackViewHolder).bind(item, position)
    }

    inner class CardStackViewHolder(private val itemCardBinding: ItemCardBinding) :
        RecyclerView.ViewHolder(itemCardBinding.root) {

        @SuppressLint("ClickableViewAccessibility")
        fun bind(cardModel: CardModel, position: Int) {

            itemCardBinding.btnOptionFirst.isVisible = firstButtonId != null
            itemCardBinding.btnOptionSecond.isVisible = secondButtonId != null

            firstButtonId?.let {
                itemCardBinding.btnOptionFirst.setImageResource(it)
            }

            secondButtonId?.let {
                itemCardBinding.btnOptionSecond.setImageResource(it)
            }

            primaryTextSize?.let {
                itemCardBinding.tvPrimary.setTextSize(TypedValue.COMPLEX_UNIT_PX, it.toFloat())
            }

            primaryTextColor?.let {
                itemCardBinding.tvPrimary.setTextColor(it)
            }

            primaryTextFontFamily?.let {
                itemCardBinding.tvPrimary.typeface = it
            }

            itemCardBinding.tvPrimary.text = cardModel.primaryTitle

            secondaryTextSize?.let {
                itemCardBinding.tvSecondary.setTextSize(TypedValue.COMPLEX_UNIT_PX, it.toFloat())
            }

            secondaryTextColor?.let {
                itemCardBinding.tvSecondary.setTextColor(it)
            }

            secondaryTextFontFamily?.let {
                itemCardBinding.tvSecondary.typeface = it
            }

            itemCardBinding.tvSecondary.text = cardModel.secondaryTitle

            (itemCardBinding.clChild.background as GradientDrawable).setStroke(
                cardStrokeWidth ?: DEFAULT_CARD_STROKE_WIDTH,
                cardStrokeColor ?: DEFAULT_CARD_STROKE_COLOR
            )

            (itemCardBinding.clChild.background as GradientDrawable).cornerRadius =
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS

            itemCardBinding.ivProfileImage.cornerRadius =
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS

            itemCardBinding.cvMain.radius = cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS

            (itemCardBinding.clChild.background as GradientDrawable).setColor(
                cardBackgroundColor ?: DEFAULT_CARD_BACKGROUND_COLOR
            )

            itemCardBinding.clChild.setPadding(
                cardChildPadding ?: DEFAULT_CARD_CHILD_PADDING,
                cardChildPadding ?: DEFAULT_CARD_CHILD_PADDING,
                cardChildPadding ?: DEFAULT_CARD_CHILD_PADDING,
                cardChildPadding ?: DEFAULT_CARD_CHILD_PADDING
            )

            val shape = itemCardBinding.flUserInfo.background as GradientDrawable
            shape.cornerRadii = floatArrayOf(
                0f,
                0f,
                0f,
                0f,
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS,
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS,
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS,
                cardCornerRadius ?: DEFAULT_CARD_CORNER_RADIUS,
            )
            shape.setColor(profileViewBackgroundColor ?: DEFAULT_PROFILE_VIEW_BACKGROUND_COLOR)
            itemCardBinding.flUserInfo.background = shape

            childViewHeight?.let {
                itemCardBinding.ivProfileImage.layoutParams.height = it
                itemCardBinding.ivProfileImage.requestLayout()
            }

            when (cardModel.image) {
                is File -> {
                    Glide.with(itemCardBinding.root.context)
                        .load(Uri.fromFile(cardModel.image))
                        .into(itemCardBinding.ivProfileImage)
                }
                is String -> {
                    Glide.with(itemCardBinding.root.context)
                        .load(cardModel.image)
                        .into(itemCardBinding.ivProfileImage)
                }
                is Drawable -> {
                    itemCardBinding.ivProfileImage.setImageDrawable(cardModel.image)
                }
                else -> {
                    Log.e("CardStack", "Added image options is invalid")
                }
            }

            actionOptionsVisibility?.let {
                itemCardBinding.clActionOptions.isVisible = it
            }

            itemCardBinding.btnOptionFirst.tag = position
            itemCardBinding.btnOptionSecond.tag = position

            itemCardBinding.btnOptionFirst.setOnClickListener { view ->
                cardActionListener?.onFirstButtonOptionClick(view.tag as Int)
            }
            itemCardBinding.btnOptionSecond.setOnClickListener {
                cardActionListener?.onSecondButtonOptionClick(it.tag as Int)
            }
        }
    }
}