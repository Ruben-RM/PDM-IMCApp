package com.example.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    var isMaleSelected:Boolean = true
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    var altura:Double = 120.0
    private lateinit var tvWeight:TextView
    private lateinit var btn_subWeight:FloatingActionButton
    private lateinit var btn_addWeight:FloatingActionButton
    var weight:Int = 60
    private lateinit var tvAge:TextView
    private lateinit var btn_subAge:FloatingActionButton
    private lateinit var btn_addAge:FloatingActionButton
    var age:Int = 20
    private lateinit var btn_calcular:AppCompatButton

    /*
    // Para debug en logcat:
    private val TAG: String = "Test"
    Log.d(TAG, age.toString());
    */

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents()
    {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btn_subWeight = findViewById(R.id.btn_subWeight)
        btn_addWeight = findViewById(R.id.btn_addWeight)
        tvAge = findViewById(R.id.tvAge)
        btn_subAge = findViewById(R.id.btn_subAge)
        btn_addAge = findViewById(R.id.btn_addAge)
        btn_calcular = findViewById(R.id.btn_calcularIMC)
    }

    private fun initListeners()
    {
        viewMale.setOnClickListener()
        {
            isMaleSelected = true
            setGenderColor()
        }

        viewFemale.setOnClickListener()
        {
            isMaleSelected = false
            setGenderColor()
        }

        rsHeight.addOnChangeListener{ _, value, _ ->
            altura = value.toDouble()
            tvHeight.text = DecimalFormat("#.##").format(value) + " cm"
        }

        btn_subWeight.setOnClickListener()
        {
            if(weight > 1)
                weight -= 1
            setWeight()
        }

        btn_addWeight.setOnClickListener()
        {
            if(weight < 1000)
                weight += 1
            setWeight()
        }

        btn_subAge.setOnClickListener()
        {
            if(age > 1)
                age -= 1
            setAge()
        }

        btn_addAge.setOnClickListener()
        {
            if(age < 120)
                age += 1
            setAge()
        }

        btn_calcular.setOnClickListener()
        {
            navigate2result(calculateIMC())
        }
    }

    private fun initUI()
    {
        setGenderColor()
        setWeight()
        setAge()
    }

    private fun setGenderColor()
    {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleSelected))
    }

    private fun setWeight()
    {
        tvWeight.text = weight.toString()
    }

    private fun setAge()
    {
        tvAge.text = age.toString()
    }

    private fun getBackgroundColor(isComponentSelected:Boolean): Int {
        val colorReference = if(isComponentSelected)
        {
            R.color.bg_comp_sel
        }
        else
        {
            R.color.bg_comp
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun calculateIMC():Double
    {
        val altura_metros = altura/100.0
        val peso = weight.toDouble()
        val IMC = peso/Math.pow(altura_metros, 2.0)

        return IMC
    }

    private fun navigate2result(IMC:Double)
    {
        val intentIRA = Intent(this, ImcResultActivity::class.java)

        intentIRA.putExtra("EXTRA_IMC", IMC)

        startActivity(intentIRA)
    }
}