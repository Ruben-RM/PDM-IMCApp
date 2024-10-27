package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcResultActivity : AppCompatActivity() {

    private lateinit var tv_resultado_texto:TextView
    private lateinit var tv_resultado_numerico:TextView
    private lateinit var tv_resultado_texto2:TextView
    private lateinit var btn_recalcularIMC:AppCompatButton

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
        val IMC:Double? = intent.extras?.getDouble("EXTRA_IMC")

        tv_resultado_texto.text = "Hola wenas"
        tv_resultado_numerico.text = "$IMC"
        tv_resultado_texto2.text = "Hola wenas que tal esto es un texto largo de prueba blablablablabla blablablablablalbalbalba lablba"
    }
}