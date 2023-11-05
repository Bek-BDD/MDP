package com.bow.androidtable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.bow.androidtable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        var view =binding.root;
        setContentView(view);
    }

    fun onAddBtn(view: View) {

        if(binding.version.text.isBlank()){
            Toast.makeText(this,"Enter a valid Version", Toast.LENGTH_LONG).show();
        }else if(binding.code.text.isBlank()){
            Toast.makeText(this,"Enter a valid Code",Toast.LENGTH_LONG).show();
        }
        else{
            var version= binding.version.text;
            var code = binding.code.text;


            val tableRow= TableRow(this);
            val layoutPrams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.layoutParams = layoutPrams;
            var versionText = TextView(this);
            versionText.setTextColor(setColor(0xFF0B6671));
            versionText.textSize = 20F;
            versionText.text=version;
            tableRow.addView(versionText,0);

            var codeText = TextView(this);
            codeText.setTextColor(setColor(0xFF0B6671));
            codeText.textSize = 20F;
            codeText.text=code;
            tableRow.addView(codeText,1);

            binding.parentTable.addView(tableRow);
            binding.version.text.clear();
            binding.code.text.clear();
        }
    }
    fun  setColor (color: Long): Int{
        return color.toInt();
    }


}