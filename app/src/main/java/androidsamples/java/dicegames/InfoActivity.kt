package androidsamples.java.dicegames

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        val btn_info = findViewById<Button>(R.id.info_back)
        btn_info.setOnClickListener { finish() }
    }
}