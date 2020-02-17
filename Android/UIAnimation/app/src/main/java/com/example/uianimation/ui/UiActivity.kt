package com.example.uianimation.ui

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import com.example.uianimation.R
import com.example.uianimation.databinding.ActivityUiBinding
import java.util.*

class UiActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityUiBinding>(this, R.layout.activity_ui)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        binding.btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            TimePickerDialog(this, { _, hour, minute ->
                Log.d("MYTAG", "hour: $hour, minute: $minute")
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }
    }
}
