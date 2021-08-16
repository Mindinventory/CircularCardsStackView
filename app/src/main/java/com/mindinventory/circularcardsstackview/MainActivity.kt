package com.mindinventory.circularcardsstackview

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.mindinventory.circularcardsstackview.data.CardModel
import com.mindinventory.circularcardsstackview.listener.CardActionListener
import com.mindinventory.circularcardsstackview.listener.OnPageChangeListener
import com.mindinventory.circularcardsstackview.sample.databinding.ActivityMainBinding


/**
 * This class defines the available customization and  usage of CardCircularStackView
 * @author MindInventory
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    /**
     * Uses for view setup
     */
    private fun init() {
        setupCardActions()
        setCardItems()
    }

    /**
     * Uses for set card items list
     */
    private fun setCardItems() {
        binding.cardStack.setUpCardStack(getItemList(), object : OnPageChangeListener {
            override fun onPageScrolled(position: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    /**
     * Uses for card actions options setup
     */
    private fun setupCardActions() {
        binding.cardStack.setActionOptions(
            firstButtonResourceId = R.drawable.ic_message,
            secondButtonResourceId = R.drawable.ic_heart,
            object : CardActionListener {
                override fun onFirstButtonOptionClick(position: Int) {

                }

                override fun onSecondButtonOptionClick(position: Int) {

                }
            }
        )
    }

    /**
     * Uses for prepare dummy list
     */
    private fun getItemList(): ArrayList<CardModel> {
        return ArrayList<CardModel>().apply {
            add(
                CardModel(
                    primaryTitle = "Pamela",
                    secondaryTitle = "Phoenix",
                    image = "https://cdn.pixabay.com/photo/2017/08/06/15/13/woman-2593366__340.jpg"
                )
            )
            add(
                CardModel(
                    primaryTitle = "Massey",
                    secondaryTitle = "New York",
                    image = "https://cdn.pixabay.com/photo/2016/03/23/04/01/woman-1274056_960_720.jpg"
                )
            )
            add(
                CardModel(
                    primaryTitle = "Yema",
                    secondaryTitle = "Milano",
                    image = "https://picsum.photos/id/1011/5472/3648"
                )
            )
            add(
                CardModel(
                    primaryTitle = "Wilson",
                    secondaryTitle = "Chicago",
                    image = "https://picsum.photos/id/1025/4951/3301"
                )
            )
            add(
                CardModel(
                    primaryTitle = "John",
                    secondaryTitle = "Houston",
                    image = "https://picsum.photos/id/1012/3973/2639"
                )
            )
        }
    }
}