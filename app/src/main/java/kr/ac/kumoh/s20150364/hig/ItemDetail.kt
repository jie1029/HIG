package kr.ac.kumoh.s20150364.hig

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_detail.*

class ItemDetail : AppCompatActivity() {

    lateinit var ins:String
    lateinit var title:String
    lateinit var site:String
    lateinit var location:String
    lateinit var tell:String
    lateinit var content:String
    lateinit var days:String
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        ins = intent.getStringExtra("ins")
        title = intent.getStringExtra("name")
        site = intent.getStringExtra("site")
        days = intent.getStringExtra("days")
        tell = intent.getStringExtra("tell")
        content = intent.getStringExtra("content")
        location = intent.getStringExtra("location")


        locationText.text = ins
        titleText.text = title
        dateText.text = days
        addressText.text = location
        numberText.text = tell
        siteText.text = site
        detailText.text = content



        siteText.setOnClickListener {
            if(site != "참고 사이트 없음"){
                var tent= Intent(Intent.ACTION_VIEW,Uri.parse(site))
                startActivity(tent)
            }
        }

    }
}
