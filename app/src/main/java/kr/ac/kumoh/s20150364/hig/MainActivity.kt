package kr.ac.kumoh.s20150364.hig

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
var myData = ArrayList<MainActivity.myList>()

var check:Int = 0
class MainActivity : AppCompatActivity() {

    data class myList(var title:String,
                      var days:String,
                      var site: String,
                      var tell:String,
                      var location:String,
                      var ins: String,
                      var content: String
                    );
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(check == 0) {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }

        btnad.setOnClickListener {
            val intent= Intent(this,promotion::class.java)
            startActivity(intent)}

        btnSearch.setOnClickListener{
            val intent= Intent(this,Search::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
        }


        daysList.adapter = DaysAdapter(this,android.R.layout.simple_list_item_2,myData);

        daysList.setOnItemClickListener { _, _, i, _ ->
            var intent = Intent(this,ItemDetail::class.java)
            intent.putExtra("name", myData.get(i).title)
            intent.putExtra("site", myData.get(i).site)
            intent.putExtra("days", myData.get(i).days)
            intent.putExtra("ins", myData.get(i).ins)
            intent.putExtra("tell", myData.get(i).tell)
            intent.putExtra("location", myData.get(i).location)
            intent.putExtra("content", myData.get(i).content)

            startActivity(intent)
        }

    }


    private class DayViewHolder{
        lateinit var txTitle: TextView
        lateinit var txDays: TextView

    }

    inner class DaysAdapter(context: Context, resource: Int, objects: MutableList<myList>):
        ArrayAdapter<myList>(context, resource, objects) {
        private val mInflater = LayoutInflater.from(context)
        private val mLayout = resource
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

            val view: View
            val holder: DayViewHolder


            if (convertView == null) {
                view = mInflater.inflate(
                    mLayout, parent, false
                )
                holder = DayViewHolder()
                holder.txTitle = view.findViewById(android.R.id.text1)
                holder.txDays = view.findViewById(android.R.id.text2)


                view.tag = holder //이렇게 저장


            }

            else {
                view = convertView
                holder = view.tag as DayViewHolder
            }


            //크게보면 ㅅㅔ번째
            holder.txTitle.text = getItem(position)?.title
            holder.txDays.text = getItem(position)?.days


            return view
        }
    }
}
