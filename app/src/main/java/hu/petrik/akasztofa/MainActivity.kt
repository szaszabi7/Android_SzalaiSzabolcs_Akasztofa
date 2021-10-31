package hu.petrik.akasztofa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

    val szoArray = arrayOf("game", "cpu", "memory", "pear", "team", "android", "color", "speaker", "controller", "monitor", "phone", "cable")
    val abc : ArrayList<Char> = ArrayList()


    var i = 0
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
                ++i
                textViewBetu.text = abc[i].toString()
            }
        }

        buttonMinusz.setOnClickListener() {
            if (i == 0){
                i = abc.size-1
                textViewBetu.text = abc[i].toString()
            }
            else {
                --i
                textViewBetu.text = abc[i].toString()
            }
        }

        buttonTipp.setOnClickListener() {
            val betu = textViewBetu.text.first().lowercaseChar()
            if (szo.contains(betu)) {
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
        /*for (j in szo.indices) {
            textViewSzo.append(szo[j].toString())
        }*/
    }
}