package com.bow.quizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bow.quizapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var answers: HashMap<Question,String>
    private lateinit var Q1:Question;
    private lateinit var Q2:Question;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
        val view = binding.root;
        setContentView(view);

         Q1 = Question("which one of the following is not an android life cycle?","OnCreate()","OnInit()","OnDestroy()","OnPause()");
         Q2 = Question("What is the parent class of every object in Kotlin?","Parent","Object","Any","Class");
        answers=HashMap<Question,String>();
        answers.put(Q1,"OnInit()");
        answers.put(Q2,"Any");

        var Q1txt =binding.Q1;
        var Q1ch1=binding.Q1choice1;
        var Q1ch2=binding.Q1choice2;
        var Q1ch3=binding.Q1choice3;
        var Q1ch4=binding.Q1choice4;

        var Q2txt=binding.Q2;
        var Q2ch1=binding.Q2choice1;
        var Q2ch2=binding.Q2choice2;
        var Q2ch3=binding.Q2choice3;
        var Q2ch4=binding.Q2choice4;

        Q1txt.text=Q1.questionString();
        Q1ch1.text=Q1.choice1();
        Q1ch2.text=Q1.choice2();
        Q1ch3.text=Q1.choice3();
        Q1ch4.text=Q1.choice4();

        Q2txt.text=Q2.questionString();
        Q2ch1.text=Q2.choice1();
        Q2ch2.text=Q2.choice2();
        Q2ch3.text=Q2.choice3();
        Q2ch4.text=Q2.choice4();

    }

    fun onSubmit(view: View) {
        var count=0;
        var Q1ch=binding.Q1ch;
        var Q1ansId=Q1ch.checkedRadioButtonId;
        var Q1ans=findViewById<RadioButton>(Q1ansId).text.toString();

        var Q2ch=binding.Q2ch;
        var Q2ansId=Q2ch.checkedRadioButtonId;
        var Q2ans=findViewById<RadioButton>(Q2ansId).text.toString();

        if(answers.get(Q1).equals(Q1ans.trim())){
            count+=50;
        }
        if(answers.get(Q2).equals(Q2ans.trim())){
            count+=50;
        }
        val currentDate = getCurrentDateTime();
        showAlertWithScore(currentDate,count);

    }
    fun onReset(view:View){
        var Q1ch=binding.Q1ch;
        var Q2ch=binding.Q2ch;

        Q1ch.clearCheck();
        Q2ch.clearCheck();
    }
    fun getCurrentDateTime():String{
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(Date());
    }
    fun showAlertWithScore(dateTime:String, score:Int){
        AlertDialog.Builder(this)
            .setMessage("Congratulations! you submitted on $dateTime, you achieved $score " )
            .setPositiveButton("OK", null)
            .show();

    }

}