package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var num:Boolean=false
    var dot=false
    var op=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun removeZero(value: String):String{
        var check=value
        if(check.contains(".0")){
            check=check.substring(0,check.length-2)
        }
        return check
    }

    fun equal(view: View) {

//        Toast.makeText(this,"App working fine",Toast.LENGTH_LONG).show()
        if(num&&op){
            var input=textView.text.toString()
            var tv=textView.text.toString()
            var prefix=""
            try {
                if(textView.text.startsWith("-")){
                    prefix="-"
                    textView.text=tv.substring(1)
                }
                if(textView.text.contains("-")){
                    val split=textView.text.split("-")
                    var one=split[0]
                    val two=split[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    textView.text=removeZero((one.toDouble()-two.toDouble()).toString())
                    op=false
                }
                else if(textView.text.contains("+")){
                    val split=textView.text.split("+")
                    var one=split[0]
                    val two=split[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    textView.text=removeZero((one.toDouble()+two.toDouble()).toString())
                    op=false
                }
                else if(textView.text.contains("/")){
                    val split=textView.text.split("/")
                    var one=split[0]
                    val two=split[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    textView.text=removeZero((one.toDouble()/two.toDouble()).toString())
                    op=false
                }
                else if(textView.text.contains("*")){
                    val split=textView.text.split("*")
                    var one=split[0]
                    val two=split[1]
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    textView.text=removeZero((one.toDouble()*two.toDouble()).toString())
                    op=false
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }

    }

    fun onDigit(view: View) {
        textView.append((view as Button).text)
        num=true
    }
    fun onClear(view: View){
        textView.text=""
        num=false
        dot=false
        op=false


    }
    fun onOperator(view: View){
        if(num&&!addOperator(textView.text.toString())){
            textView.append((view as Button).text)
            num=false
            dot=false
            op=true
        }
    }
    private fun addOperator(value:String):Boolean {

            return if (textView.text.toString().startsWith("-")&&num) {
                false
            }
            else (textView.text.toString().contains("/") || textView.text.toString()
                    .contains("*") || textView.text.toString()
                    .contains("-") || textView.text.toString().contains("+")
            )
        }

    fun onDecimal(view:View){
        if(num && !dot){
           textView.append(".")
            dot=true
            num=false
        }
    }
}