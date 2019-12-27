package kr.ac.kumoh.s20150364.hig

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_search.*
import org.json.JSONObject


class Search : AppCompatActivity() {

    lateinit var mQueue: RequestQueue

    lateinit var place :String
    lateinit var date :String
    lateinit var json:String
    data class Festival(var fesName: String,
                        var fesIns: String,
                        var fesLoca: String,
                        var fesDate: String,
                        var fesSite : String,
                        var fesTell : String,
                        var fesContent: String
                        )

    private var fesArray = ArrayList<Festival>()
    var mResult : JSONObject? =null

    lateinit var mAdapter: BaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)



        btnad.setOnClickListener {
            val intent= Intent(this,promotion::class.java)
            startActivity(intent)}

        btnmain.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)}

        btnGo.setOnClickListener{

            place = editPlace.text.toString()
            date = editDate.text.toString()
            requestInternet()
        }

        map_btn.setOnClickListener {
            val intent = Intent(this, Maps::class.java)
            startActivity(intent)
        }

        //fesArray.add(Festival("1", "1", "1", "1", "1"))

        mAdapter = FesAdapter(this)
        festival_list.adapter = mAdapter

        mQueue = Volley.newRequestQueue(this)



    }


    private fun requestInternet(){

        val url = "http://IP/filename

        val request = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                mResult = response
                drawList()

                Log.i("들어옴: ",response.toString())
            },
            Response.ErrorListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_LONG).show()
                Log.i("TEST: ",it.toString());

            })

        mQueue.add(request)
    }

    fun drawList(){

        if(mResult?.has("list")!!)
        {
            val items = mResult?.getJSONArray("list")?:return
            fesArray.clear()

            for(i in 0 until items.length()){
                val item = items[i] as JSONObject
                val name = item.getString("name")
                val ins = item.getString("organiser")
                val location = item.getString("location")
                val startDate = item.getString("start_date")
                val endDate = item.getString("end_date")
                var site = item.getString("homepage")
                var tell = item.getString("tell")
                var content = item.getString("content")

                if(site == "null")
                    site = "참고 사이트 없음"
                if(tell =="null")
                    tell = "등록된 전화번호 없음"
                if(content == "null")
                    content = "등록된 상세보기 없음"

                fesArray.add(Festival(name, ins, location, startDate + "~" + endDate, site,tell,content))

            }

            mAdapter.notifyDataSetChanged()
        }

    }

    private class FesHolder {
        lateinit var txName: TextView
        lateinit var txIns : TextView
        lateinit var txLoca : TextView
        lateinit var txDate : TextView
        lateinit var txSite : TextView
        lateinit var btn: Button
    }
    inner class FesAdapter (context: Context) : BaseAdapter(){
        private var inflater = LayoutInflater.from(context)

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val viewHolder: FesHolder
            val view: View

            if (p1 == null) {
                view = inflater.inflate(R.layout.search_list_item, p2, false)
                viewHolder = FesHolder()
                viewHolder.txName = view.findViewById(R.id.item)
                viewHolder.txIns = view.findViewById(R.id.institution)
                viewHolder.txLoca = view.findViewById(R.id.location)
                viewHolder.txDate = view.findViewById(R.id.date)
                viewHolder.txSite = view.findViewById(R.id.site)
                viewHolder.btn = view.findViewById(R.id.addBtn)
                view.tag = viewHolder
            }
            else{
                view = p1
                viewHolder = p1.tag as FesHolder
            }
            viewHolder.txName.text=getItem(p0).fesName
            viewHolder.txIns.text="주최기관 : " + getItem(p0).fesIns
            viewHolder.txLoca.text="지역 : " + getItem(p0).fesLoca
            viewHolder.txDate.text="날짜 : " + getItem(p0).fesDate
            viewHolder.txSite.text="참고사이트 : " + getItem(p0).fesSite
            viewHolder.btn.setOnClickListener {
                myData.add(MainActivity.myList(getItem(p0).fesName,getItem(p0).fesDate,getItem(p0).fesSite,
                    getItem(p0).fesTell,getItem(p0).fesLoca,getItem(p0).fesIns,getItem(p0).fesContent))
                viewHolder.btn.isEnabled = false

                Toast.makeText(this@Search,"추가되었습니다!",Toast.LENGTH_LONG).show()

            }
            return view
        }
        override fun getItem(p0: Int): Festival {
            return fesArray[p0]
        }
        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
        override fun getCount(): Int {
            return fesArray.size
        }
    }
}
