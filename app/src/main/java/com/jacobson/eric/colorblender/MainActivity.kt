package com.jacobson.eric.colorblender

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var REQUEST_CODE = 10
    var colorOneVals: IntArray = intArrayOf(0, 0, 0)
    var colorTwoVals: IntArray = intArrayOf(0, 0, 0)

//    var redBlendVal = 128
//    var greenBlendVal = 128
//    var blueBlendVal = 128
//    var redLeftVal = 255
//    var greenLeftVal = 255
//    var blueLeftVal = 255
//    var redRightVal = 0
//    var greenRightVal = 0
//    var blueRightVal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // temporarily set background colors to black so I can see them
        colorOneView.setBackgroundColor(Color.BLACK)
        colorTwoView.setBackgroundColor(Color.BLACK)
        colorBlendView.setBackgroundColor(Color.BLACK)

        colorButton.setOnClickListener {
            getColors()
        }

        blendSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data!!.hasExtra("color_1")) {
                val colorOne = data.getStringExtra("color_1")
                val item = colorOne.split(" ")
                colorOneVals[0] = item[0].toInt()
                colorOneVals[1] = item[1].toInt()
                colorOneVals[2] = item[2].toInt()
                colorOneView.setBackgroundColor(Color.rgb(colorOneVals[0], colorOneVals[1], colorOneVals[2]))
            }
            if (data.hasExtra("color_2")) {
                val colorOne = data.getStringExtra("color_2")
                val item = colorOne.split(" ")
                colorTwoVals[0] = item[0].toInt()
                colorTwoVals[1] = item[1].toInt()
                colorTwoVals[2] = item[2].toInt()
                colorOneView.setBackgroundColor(Color.rgb(colorTwoVals[0], colorTwoVals[1], colorTwoVals[2]))
            }
        }
    }

    private fun getColors() {
        val intent = Intent(Intent.ACTION_DEFAULT)
        intent.action = "com.jacobson.eric.colorpicker"
        intent.putExtra("color_1", colorOneVals)
        intent.putExtra("color_2", colorTwoVals)
        startActivityForResult(intent, REQUEST_CODE)
    }
}