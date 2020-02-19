package com.example.uianimation.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.uianimation.R
import com.example.uianimation.databinding.ActivityUiBinding
import org.koin.android.ext.android.inject
import java.util.*

class UiActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityUiBinding>(this, R.layout.activity_ui)
    }

    private val viewModel: UiViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui)

        binding.btnTimePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dialog = TimePickerDialog(
                this, { _, hour, minute ->
                    Log.d("MYTAG", "hour: $hour, minute: $minute")
                    val cal = Calendar.getInstance(Locale.US).apply {
                        set(Calendar.HOUR_OF_DAY, hour)
                        set(Calendar.MINUTE, minute)
                    }
                    val isFuture = (cal.timeInMillis - Date().time) > 0
                    if (isFuture) {
                        Toast.makeText(this@UiActivity, "Future~!", Toast.LENGTH_LONG).show()
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false
            )
            dialog.show()
        }
    }
}
