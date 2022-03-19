package com.example.sample23self

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()
        fragmentTransaction.add(R.id.content, FragmentOne())
        fragmentTransaction.commit()*/

    }

    override fun onClick(v: View?) {

        var fr:Fragment? = null
        if(v?.id == R.id.button1) {
            fr = FragmentOne()
        }
        else if (v?.id == R.id.button2) {
            fr = FragmentTwo()
        }
        else if (v?.id == R.id.button3) {
            fr = FragmentThree()
        }

        val fm = supportFragmentManager
        val fragmentTransaction = fm.beginTransaction()

        fragmentTransaction.replace(R.id.content, fr!!)
        fragmentTransaction.commit()


    }


}