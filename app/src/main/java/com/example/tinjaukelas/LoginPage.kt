package com.example.tinjaukelas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.tinjaukelas.databinding.LoginpageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var binding: LoginpageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInput(email, password)) {
                performLogin(email, password)
            }
        }
    }

    private fun validateInput(email: String, password: String): Boolean {
        return when {
            email.isEmpty() -> {
                binding.tilEmail.error = "Email tidak boleh kosong"
                false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = "Format email tidak valid"
                false
            }
            password.isEmpty() -> {
                binding.tilPassword.error = "Password tidak boleh kosong"
                false
            }
            password.length < 6 -> {
                binding.tilPassword.error = "Password minimal 6 karakter"
                false
            }
            else -> {
                binding.tilEmail.error = null
                binding.tilPassword.error = null
                true
            }
        }
    }

    private fun performLogin(email: String, password: String) {
        if (email == "admin@gmail.com" && password == "123456") {
            Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
        }
    }
}