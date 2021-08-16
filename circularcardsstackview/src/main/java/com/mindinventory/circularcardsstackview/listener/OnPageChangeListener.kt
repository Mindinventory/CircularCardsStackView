package com.mindinventory.circularcardsstackview.listener

interface OnPageChangeListener {
    fun onPageScrolled(position: Int)

    fun onPageSelected(position: Int)

    fun onPageScrollStateChanged(state: Int)
}