package com.richard.jetpacklessons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var imageState : ImageView
    private var state = LemonadeState.SELECT
    private var squeezeCount = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Find Image on the screen
        imageState = findViewById(R.id.image_lemon_state)

        //Set Default image when app start
        imageState.setImageResource(R.drawable.lemon_tree)
        imageState.setOnClickListener{ clickLemonImage() }
    }

    private fun clickLemonImage() {
        val drawableId : Int
        when(state){
            LemonadeState.SELECT ->  {
                state = LemonadeState.SQUEEZE
                drawableId = R.drawable.lemon_squeeze
            }
            LemonadeState.DRINK -> {
                state = LemonadeState.RESTART
                drawableId = R.drawable.lemon_restart
            }
            LemonadeState.SQUEEZE -> {
               if(squeezeCount == 0){
                   state = LemonadeState.DRINK
                   drawableId = R.drawable.lemon_drink
               }else{
                   squeezeCount -= 1
                   state = LemonadeState.SQUEEZE
                   drawableId = R.drawable.lemon_squeeze
               }
            }
            LemonadeState.RESTART -> {
                state = LemonadeState.SELECT
                drawableId = R.drawable.lemon_tree
                squeezeCount = 4
            }
        }
        imageState.setImageResource(drawableId)
        setViewElements()

    }

    private fun setViewElements() {
        val textAction : TextView = findViewById(R.id.text_action)
        val stateString = when(state){
            LemonadeState.SELECT ->  R.string.lemon_select
            LemonadeState.DRINK ->  R.string.lemon_drink
            LemonadeState.SQUEEZE -> R.string.lemon_squeeze
            LemonadeState.RESTART -> R.string.lemon_empty_glass
        }
        textAction.text = getString(stateString)
    }


}