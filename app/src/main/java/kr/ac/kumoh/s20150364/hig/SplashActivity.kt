package kr.ac.kumoh.s20150364.hig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

class SplashActivity : AppCompatActivity() {

    internal lateinit var flowAnim: Animation
    internal lateinit var textView1: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        check = 1
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)

        flowAnim = AnimationUtils.loadAnimation(this, R.anim.flow)


        //UI
        textView1 = findViewById(R.id.textView1) as TextView


        flowAnim.setAnimationListener(FlowAnimationListener())


        //뷰에 애니메이션 설정정
        textView1.startAnimation(flowAnim)

        startLoading()
    }

    private fun startLoading() {
        val handler = Handler()
        handler.postDelayed({ finish() }, 4000)
    }

    private inner class FlowAnimationListener : Animation.AnimationListener {
        //애니메이션 종료되었을때
        override fun onAnimationEnd(animation: Animation) {


            //Toast.makeText(getApplicationContext(), "애니메이션 종료됨", Toast.LENGTH_SHORT).show();
        }

        override fun onAnimationRepeat(animation: Animation) {
            Log.d("ddd", "Animation Repeat")
        }

        //애니메이션 시작했을때
        override fun onAnimationStart(animation: Animation) {
            Log.d("ddd", "Animation Start")
        }
    }
}
