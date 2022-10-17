package com.arun.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val viewModel = ViewModelProviders.of(this).get(DoubleViewModel::class.java)
        val viewModel = ViewModelProviders.of(this).get(FloatViewModel::class.java)
        viewModel.calcData.observe(this, { newNumberTextView.setText(it) })
        viewModel.calcResult.observe(this, { resultTextView.setText(it) })
        viewModel.calcOperator.observe(this, { displayOp.text = it })

        val dataClickListener = View.OnClickListener {
            viewModel.digitPressed((it as Button).text.toString())
        }
        val negBtnClickListener = View.OnClickListener {
            viewModel.negPressed()
        }
        val delClickListener = View.OnClickListener {
            viewModel.delPressed()
        }
        val clearClickListener = View.OnClickListener {
            viewModel.clearPressed()
        }
        val operatorClickListener = View.OnClickListener {
            viewModel.operatorPressed((it as Button).text.toString())
        }

        // adding data click listeners
        button0.setOnClickListener(dataClickListener)
        button1.setOnClickListener(dataClickListener)
        button2.setOnClickListener(dataClickListener)
        button3.setOnClickListener(dataClickListener)
        button4.setOnClickListener(dataClickListener)
        button5.setOnClickListener(dataClickListener)
        button6.setOnClickListener(dataClickListener)
        button7.setOnClickListener(dataClickListener)
        button8.setOnClickListener(dataClickListener)
        button9.setOnClickListener(dataClickListener)
        period.setOnClickListener(dataClickListener)
        buttonNeg.setOnClickListener(negBtnClickListener)
        buttonDelete.setOnClickListener(delClickListener)
        buttonClear.setOnClickListener(clearClickListener)

        // operator click listener
        plus.setOnClickListener(operatorClickListener)
        minus.setOnClickListener(operatorClickListener)
        multiplier.setOnClickListener(operatorClickListener)
        divider.setOnClickListener(operatorClickListener)
        equals.setOnClickListener(operatorClickListener)
    }
}

