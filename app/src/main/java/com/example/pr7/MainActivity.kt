package com.example.pr7

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.graphics.drawable.toDrawable
import com.example.pr7.databinding.ActivityMainBinding
import java.io.Console
import kotlin.math.E
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Enable(false)
    }
    fun Generate (view: View){
        binding.txtOperator.text = RandomOperator().toString()
        binding.txtFirstOperand.text = RandomOperand().toString()
        if (binding.txtOperator.text == "/") binding.txtSecondOperand.text  = Nod()
        else binding.txtSecondOperand.text = RandomOperand().toString()
        Enable(true)
        binding.linearLayoutValidate.background = Color.WHITE.toDrawable()
        binding.editTxtAnswer.text = null
    }
    fun Check (view: View){
        val x1 = binding.txtFirstOperand.text.toString().toInt()
        val x2 = binding.txtSecondOperand.text.toString().toInt()
        var answer = false
        if (binding.editTxtAnswer.text.toString() != "") {
            when (binding.txtOperator.text){
                "+" -> if (x1 + x2 == binding.editTxtAnswer.text.toString().toInt()) answer = true
                "-" -> if (x1 - x2 == binding.editTxtAnswer.text.toString().toInt()) answer = true
                "/" -> if (x1 / x2 == binding.editTxtAnswer.text.toString().toInt()) answer = true
                "*" -> if (x1 * x2 == binding.editTxtAnswer.text.toString().toInt()) answer = true
            }
        }
        Validate(answer)
        Enable(false)
    }
    fun RandomOperand(): Int = (10..99).random()
    fun RandomOperator(): Char = arrayOf('+', '-', '/', '*').random()
    fun Nod() : String = (10..99).filter { binding.txtFirstOperand.text.toString().toInt() % it == 0 }.random().toString()
    @SuppressLint("SetTextI18n")
    fun Validate(answer: Boolean){
        if (answer){
            binding.linearLayoutValidate.background = Color.GREEN.toDrawable()
            binding.txtNumberCorrect.text = (binding.txtNumberCorrect.text.toString().toInt() + 1).toString()
        }
        else {
            binding.linearLayoutValidate.background = Color.RED.toDrawable()
            binding.txtNumberNotCorrect.text = (binding.txtNumberNotCorrect.text.toString().toInt() + 1).toString()
        }
        binding.txtNumberCount.text = (binding.txtNumberCount.text.toString().toInt() + 1).toString()
        binding.txtPercent.text = String.format("%.2f%%", (binding.txtNumberCorrect.text.toString().toDouble() / binding.txtNumberCount.text.toString().toDouble()) * 100.0)
    }
    fun Enable(check: Boolean){
        binding.editTxtAnswer.isEnabled = check
        binding.btnCheck.isEnabled = check
        binding.btnStart.isEnabled = !check
    }
}
