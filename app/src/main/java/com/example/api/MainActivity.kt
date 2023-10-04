package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var  edittext: EditText
    lateinit var  paisid: EditText
    lateinit var  boton: Button
    lateinit var  url : String
    lateinit var edad : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edittext = findViewById(R.id.editTextText)
        paisid = findViewById(R.id.textView4)
        edad = findViewById(R.id.textView2)
        url = "https://api.agify.io?name=&country_id="


        boton = findViewById(R.id.button)

        boton.setOnClickListener{

            if (paisid.text.toString() != ""){
                ingresaractiv()
            }
            else
                ingresaractivsid()

        }


    }

    fun ingresaractiv(){
        url = "https://api.agify.io?name=&country_id="
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val nombre = edittext.text.toString()
        val idcountry = paisid.text.toString()
        val simplearray = url.split("&")
        url = simplearray[0] + nombre +"&"+ simplearray[1] + idcountry

        val request = StringRequest(

            Request.Method.GET,url,
            { response ->
                val data = response.toString()
                Log.e("Objects",data)
                val json = JSONObject(data)
                val age = json.getInt("age")
                edad.text = age.toString()
            },
            { })
        queue.add(request)



    }

    fun ingresaractivsid(){

        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val nombre = edittext.text.toString()
        url = "https://api.agify.io?name=$nombre"

        val request = StringRequest(

            Request.Method.GET,url,
            { response ->
                val data = response.toString()
                Log.e("Objects",data)
                val json = JSONObject(data)
                val age = json.getInt("age")
                edad.text = age.toString()
            },
            { })
        queue.add(request)

    }

}