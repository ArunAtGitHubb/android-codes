package com.arun.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class FloatViewModel : ViewModel() {

    companion object Static {
        private var operand1: Float? = null
        private var operand2 = 0.0f
        private var operator = "="
    }

    private val newNumber = MutableLiveData<String>()
    val calcData: LiveData<String>
        get() = newNumber
    private val displayOp = MutableLiveData<String>()
    val calcOperator: LiveData<String>
        get() = displayOp
    private val result = MutableLiveData<Float>()
    val calcResult: LiveData<String>
        get() = Transformations.map(result) { it.toString() }


    fun digitPressed(digit: String) {
        if (newNumber.value == null) {
            newNumber.value = digit
        } else {
            newNumber.value += digit
        }
    }

    fun negPressed() {
        if (newNumber.value != null) {
            newNumber.value = "-${newNumber.value}"
        } else {
            newNumber.value = "-"
        }
    }

    fun operatorPressed(op: String) {
        try {
            val value = newNumber.value.toString()
            if (value.isNotEmpty()) {
                performMath(value.toFloat(), op)
            }
        } catch (e: NumberFormatException) {

        }
        operator = op
        displayOp.value = operator
        newNumber.value = ""
    }

    fun delPressed() {
        newNumber.value = newNumber.value?.dropLast(1)
    }

    fun clearPressed() {
        operand1 = null
        operator = "="
        newNumber.value = ""
        result.value = 0.0f
    }

    private fun performMath(value: Float, op: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            operand2 = value
            if (operator == "=") {
                operator = op
            }
            when (operator) {
                "+" -> operand1 = operand1!! + operand2
                "-" -> operand1 = operand1!! - operand2
                "*" -> operand1 = operand1!! * operand2
                "/" -> operand1 =
                    if (operand2 == 0.0f) Float.NaN else operand1!! / operand2
                "=" -> operand1 = operand2
            }
        }
        result.value = operand1
    }
}