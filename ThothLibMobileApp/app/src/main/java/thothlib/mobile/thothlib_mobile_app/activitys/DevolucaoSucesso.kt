package thothlib.mobile.thothlib_mobile_app.activitys

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

import thothlib.mobile.thothlib_mobile_app.R

class DevolucaoSucesso : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devolucao_sucesso)
    }
}