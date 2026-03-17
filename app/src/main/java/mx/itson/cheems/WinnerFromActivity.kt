package mx.itson.cheems

import android.content.Intent
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.itson.itson.persistence.Winner
class WinnerFromActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_winner_from)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnSave = findViewById<View>(R.id.btn_save) as Button
        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View) {
    when(v.id){
        R.id.btn_save ->{
            val name = findViewById<EditText>(R.id.txt_name).text.toString()
            val nickname = findViewById<EditText>(R.id.txt_nickname).text.toString()
            Winner().save(this, name, nickname)
            v.isEnabled = false

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

    }
    }
}