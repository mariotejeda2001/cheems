package mx.itson.cheems

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.itson.itson.persistence.Winner

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var gameOverCard = 0
    var gameWin = 0
    var cartasContador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // prueba de metodo getAll
        Winner().save(this,"pedro robles", "xX_Winner666_xX")
        Winner().getAll(this)

        Toast.makeText(this, getString(R.string.txt_welcome), Toast.LENGTH_SHORT).show()
        start()
        val btnUpdate = findViewById<View>(R.id.btnupdate) as Button
        btnUpdate.setOnClickListener(this)
    }
    fun start (){
        for (i in 1..12){
            val btnCard = findViewById<View>(resources.getIdentifier("card$i", "id", this.packageName)) as ImageButton
            btnCard.setOnClickListener(this)
            btnCard.setBackgroundResource(R.drawable.cheems_question)
            btnCard.isEnabled = true
        }
        gameOverCard = (1..12).random()
        gameWin = (1..12).random()
        if (gameWin == gameOverCard){
            gameOverCard = (1..12).random()
        }
        cartasContador = 0


        Log.d("valor de la carta", "la carta perdedora es ${gameOverCard.toString()}")
        Log.d("valro de carta", "start: esta es la carta ganadora$gameWin")
    }
    fun vibration (){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            //si la version del sistema operativo es igual o mayor a S entonces el vibrador se va a utilizar de esta manera
            val vibratorAdmin = applicationContext.getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            val vibrator = vibratorAdmin.defaultVibrator
            vibrator.vibrate(VibrationEffect.createOneShot(1500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else{
            val vibrator = applicationContext.getSystemService(VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(1500)
        }
    }
    fun flip (card: Int){

        if (card ==gameOverCard){
            vibration()
            Toast.makeText(this, getString(R.string.txt_loser), Toast.LENGTH_LONG).show()

            for(i in 1..12){
                val btnCard = findViewById<View>(
                    resources.getIdentifier("card$i", "id", this.packageName)
                ) as ImageButton
                if (i == card){
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                } else if (i==gameWin){
                    btnCard.setBackgroundResource(R.drawable.img)
                }else{
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                    cartasContador ++
                }
            }
        } else if (card == gameWin){
            vibration()
            Toast.makeText(this, getString(R.string.txt_win), Toast.LENGTH_SHORT).show()
            for(i in 1..12){
                val btnCard = findViewById<View>(resources.getIdentifier("card$i", "id", this.packageName)) as ImageButton
                if (i == card){
                    btnCard.setBackgroundResource(R.drawable.img)
                } else if (i == gameOverCard){
                    btnCard.setBackgroundResource(R.drawable.cheems_bad)
                }else{
                    btnCard.setBackgroundResource(R.drawable.cheems_ok)
                }
            }
        }else {
            val btnCard = findViewById<View>(
                resources.getIdentifier("card$card", "id", this.packageName)
            )
            btnCard.setBackgroundResource(R.drawable.cheems_ok)
            btnCard.isEnabled = false
            cartasContador ++
            if (cartasContador == 10){
                Toast.makeText(this, getString(R.string.txt_win), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.card1 -> {flip(1)}
            R.id.card2 -> {flip(2)}
            R.id.card3 -> {flip(3)}
            R.id.card4 -> {flip(4)}
            R.id.card5 -> {flip(5)}
            R.id.card6 -> {flip(6)}
            R.id.card7 -> {flip(7)}
            R.id.card8 -> {flip(8)}
            R.id.card9 -> {flip(9)}
            R.id.card10 -> {flip(10)}
            R.id.card11 -> {flip(11)}
            R.id.card12 -> {flip(12)}

            R.id.btnupdate -> start()
        }
    }
}