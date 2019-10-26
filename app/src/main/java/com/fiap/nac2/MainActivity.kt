package com.fiap.nac2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.R.attr.password




class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance();

       btEntrar.setOnClickListener {
           entrar()
       }

        btNovo.setOnClickListener {
            criarConta()
        }
    }

    private fun usuarioEstaConectado () : Boolean{
        val currentUser = mAuth.currentUser
        return currentUser !=null
    }

    private fun criarConta() {
        mAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    abrirSite()
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        this,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

    private fun abrirSite(){
        val intent = Intent(this, Realtime::class.java)
        startActivity(intent)
        finish()
    }

    private fun entrar(){
        mAuth.signInWithEmailAndPassword(etEmail.text.toString(), etSenha.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    abrirSite()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this,
                        task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                // ...
            }
    }

}
