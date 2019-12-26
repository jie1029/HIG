package kr.ac.kumoh.s20150364.hig

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class Join : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        btnad.setOnClickListener {
            val intent= Intent(this,promotion::class.java)
            startActivity(intent)}

        btnmain.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)}
    }
}
