package ir.avang.vpn

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isConnected = false
    private var pulseAnimator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvStatus = findViewById<TextView>(R.id.tvStatus)
        val btnMagic = findViewById<Button>(R.id.btnMagic)
        val btnSubManager = findViewById<Button>(R.id.btnSubManager)
        val btnQrScan = findViewById<Button>(R.id.btnQrScan)
        val btnPower = findViewById<Button>(R.id.btnPower)

        btnMagic.setOnClickListener {
            tvStatus.text = "Updating & pinging servers..."
            tvStatus.setTextColor(Color.parseColor("#BA55D3"))
            btnMagic.postDelayed({
                tvStatus.text = "Best server selected (ping: 54ms)"
                Toast.makeText(this, "Ready to connect!", Toast.LENGTH_SHORT).show()
            }, 2000)
        }

        btnSubManager.setOnClickListener {
            Toast.makeText(this, "Subscription list opened", Toast.LENGTH_SHORT).show()
        }

        btnQrScan.setOnClickListener {
            Toast.makeText(this, "Camera activated for QR scan", Toast.LENGTH_SHORT).show()
        }

        btnPower.setOnClickListener {
            isConnected = !isConnected
            if (isConnected) {
                tvStatus.text = getString(R.string.status_connected)
                tvStatus.setTextColor(Color.parseColor("#00FF7F"))
                btnPower.text = getString(R.string.btn_disconnect)
                btnPower.setBackgroundColor(Color.parseColor("#BA55D3"))
                startPulse(tvStatus)
            } else {
                tvStatus.text = getString(R.string.status_disconnected)
                tvStatus.setTextColor(Color.parseColor("#FF4C4C"))
                btnPower.text = getString(R.string.btn_connect)
                btnPower.setBackgroundColor(Color.parseColor("#4B0082"))
                stopPulse(tvStatus)
            }
        }
    }

    private fun startPulse(view: TextView) {
        stopPulse(view)
        pulseAnimator = ValueAnimator.ofObject(
            ArgbEvaluator(),
            Color.parseColor("#1A1A1A"),
            Color.parseColor("#3B096C")
        ).apply {
            duration = 1000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            addUpdateListener { animator ->
                view.setBackgroundColor(animator.animatedValue as Int)
            }
            start()
        }
    }

    private fun stopPulse(view: TextView) {
        pulseAnimator?.cancel()
        pulseAnimator = null
        view.setBackgroundColor(Color.parseColor("#1A1A1A"))
    }

    override fun onDestroy() {
        pulseAnimator?.cancel()
        super.onDestroy()
    }
}