package hu.petrik.akasztofa

import android.graphics.drawable.Drawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import java.util.ArrayList
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var buttonMinusz : Button
    lateinit var buttonPlusz : Button
    lateinit var buttonTipp : Button
    lateinit var textViewBetu: TextView
    lateinit var textViewSzo: TextView
    lateinit var imageViewAkasztofa: ImageView
    lateinit var random : Random
    lateinit var szo : CharArray
    lateinit var szoBetuk : CharArray
    lateinit var gameEndDialog : AlertDialog.Builder

    val szoArray = arrayOf("game", "cpu", "memory", "pear", "team", "android", "color", "speaker", "controller", "monitor", "phone", "cable")
    val abc : ArrayList<Char> = ArrayList()
    var i = 0
    var hiba = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        buttonPlusz.setOnClickListener() {
            if (i == abc.size-1){
                i = 0
                textViewBetu.text = abc[i].toString()
            }
            else {
                i++
                textViewBetu.text = abc[i].toString()
            }
        }

        buttonMinusz.setOnClickListener() {
            if (i == 0){
                i = abc.size-1
                textViewBetu.text = abc[i].toString()
            }
            else {
                i--
                textViewBetu.text = abc[i].toString()
            }
        }

        buttonTipp.setOnClickListener() {
            val betu = textViewBetu.text.first().lowercaseChar()
            if (szoBetuk.contentEquals(szo)) {
                gameEndDialog.setTitle("Helyes megfejtés!")
                gameEndDialog.show()
            }
            else if (szo.contains(betu)) {
                textViewSzo.text = ""
                var index : ArrayList<Int> = ArrayList()
                for (i in szo.indices) {
                    if (szo[i] == betu) {
                        index.add(i)
                    }
                }
                for (j in index.indices) {
                    szoBetuk[index[j]] = betu
                }
                for (k in szo.indices) {
                    textViewSzo.append(szoBetuk[k].toString())
                }
            } else {
                hiba++
                when(hiba) {
                    1 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa01)
                    2 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa02)
                    3 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa03)
                    4 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa04)
                    5 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa05)
                    6 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa06)
                    7 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa07)
                    8 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa08)
                    9 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa09)
                    10 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa10)
                    11 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa11)
                    12 -> imageViewAkasztofa.setImageResource(R.drawable.akasztofa12)
                    13 -> {
                        imageViewAkasztofa.setImageResource(R.drawable.akasztofa13)
                        gameEndDialog.setTitle("Nem sikerült kitalálni!")
                        gameEndDialog.show()
                    }
                }
            }
        }
    }

    fun init() {
        buttonMinusz = findViewById(R.id.buttonMinusz)
        buttonPlusz = findViewById(R.id.buttonPlusz)
        buttonTipp = findViewById(R.id.buttonTipp)
        textViewBetu = findViewById(R.id.textViewBetu)
        textViewSzo = findViewById(R.id.textViewSzo)
        imageViewAkasztofa = findViewById(R.id.imageViewAkasztofa)
        random = Random
        var c : Char = 'A'
        while (c <= 'Z') {
            abc.add(c)
            c++
        }
        textViewBetu.text = abc[0].toString()
        szo = szoArray[random.nextInt(12)].toCharArray()
        szoBetuk = CharArray(szo.size)
        for (i in szo.indices) {
            textViewSzo.append("_")
            szoBetuk[i] = '_'
        }
        gameEnd()
        /*for (j in szo.indices) {
            textViewSzo.append(szo[j].toString())
        }*/
    }

    fun gameEnd() {
        gameEndDialog = AlertDialog.Builder(this)
            .setMessage("Szeretnél még egyet játszani?")
            .setCancelable(false)
            .setPositiveButton("Igen") { _, _ ->
                reset()
            }.setNegativeButton("Nem") { _, _ ->
                finishAffinity()
            }
    }

    fun reset() {
        textViewSzo.text = ""
        textViewBetu.text = abc[0].toString()
        szo = szoArray[random.nextInt(12)].toCharArray()
        szoBetuk = CharArray(szo.size)
        for (i in szo.indices) {
            textViewSzo.append("_")
            szoBetuk[i] = '_'
        }
        hiba = 0
        i = 0
        imageViewAkasztofa.setImageResource(R.drawable.akasztofa00)
    }
}