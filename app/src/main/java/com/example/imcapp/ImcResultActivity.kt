package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imcapp.ImcCalculatorActivity.Companion.IMC_KEY

class ImcResultActivity : AppCompatActivity() {

    private lateinit var tv_resultado_texto:TextView
    private lateinit var tv_resultado_numerico:TextView
    private lateinit var tv_resultado_texto2:TextView
    private lateinit var btn_recalcularIMC:AppCompatButton

    /*
    // Para debug en logcat:
    // Luego en Logcat, filtrar por Test
    //private val TAG: String = "Test"
    //Log.d(TAG, age.toString());
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_result)

        initComponents()
        initListeners()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents()
    {
        tv_resultado_texto = findViewById(R.id.tv_resultado_texto)
        tv_resultado_numerico = findViewById(R.id.tv_resultado_numerico)
        tv_resultado_texto2 = findViewById(R.id.tv_resultado_texto2)
        btn_recalcularIMC = findViewById(R.id.btn_recalcularIMC)
    }

    private fun initListeners()
    {
        btn_recalcularIMC.setOnClickListener()
        {
            navigate2main()
        }
    }

    private fun navigate2main()
    {
        val intentICA = Intent(this, ImcCalculatorActivity::class.java)

        startActivity(intentICA)
    }

    private fun initUI()
    {
        setResultados()
    }

    private fun setResultados()
    {
        val calculoIMC:Double = intent.extras?.getDouble(IMC_KEY)?:-1.0

        when
        {
            (calculoIMC > 0.0 && calculoIMC < 18.5) ->
            {
                tv_resultado_texto.text = getString(R.string.peso_insuficiente)
                tv_resultado_texto.setTextColor(ContextCompat.getColor(this, R.color.inferior_red))
                tv_resultado_texto2.text = getString(R.string.text_insuficiente)
            }
            (calculoIMC >= 18.5 && calculoIMC < 25.0) ->
            {
                tv_resultado_texto.text = getString(R.string.peso_normal)
                tv_resultado_texto.setTextColor(ContextCompat.getColor(this, R.color.green))
                tv_resultado_texto2.text = getString(R.string.text_normal)
            }
            (calculoIMC >= 25.0 && calculoIMC < 30) ->
            {
                tv_resultado_texto.text = getString(R.string.demasiado_peso)
                tv_resultado_texto.setTextColor(ContextCompat.getColor(this, R.color.inferior_red))
                tv_resultado_texto2.text = getString(R.string.text_pesoalto)
            }
            (calculoIMC >= 30) ->
            {
                tv_resultado_texto.text = getString(R.string.sobrepeso)
                tv_resultado_texto.setTextColor(ContextCompat.getColor(this, R.color.red))
                tv_resultado_texto2.text = getString(R.string.text_obesidad)
            }
            else ->
            {
                tv_resultado_texto.text = "¿¿??"
                tv_resultado_texto.setTextColor(ContextCompat.getColor(this, R.color.red))
                tv_resultado_texto2.text = getString(R.string.text_error_IMC)
            }
        }
        tv_resultado_numerico.text = DecimalFormat("##.##").format(calculoIMC)
    }
}