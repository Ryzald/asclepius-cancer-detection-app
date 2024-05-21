package com.dicoding.asclepius.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding


class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imageUri = intent.getStringExtra(EXTRA_URI)
        imageUri?.let {
            val uri = Uri.parse(imageUri)
            showImage(uri)
        }

        // TODO: Menampilkan hasil gambar, prediksi, dan confidence score.
        val resultImage = intent.getStringExtra(EXTRA_RESULT)
        resultImage?.let {
            showResult(it)

        }

        btnAction()


    }


    private fun showImage(uri: Uri) {
        Log.d("Image URI", "showImage: $uri")
        binding.resultImage.setImageURI(uri)
    }

    private fun showResult(result: String) {
        Log.d("Image Result", "result: $result")
        val parts = result.split(":")
        val category = parts[0]
        val precentage = parts[1]
        val resultCategory = getString(R.string.category) +" "+ category
        val resultPrecentage = getString(R.string.presentase) + precentage
        binding.category.text = resultCategory
        binding.precentage.text = resultPrecentage

        if (resultCategory.contains("Non")) {
            binding.explanation.text = getString(R.string.nonCancer)

        } else {
            binding.explanation.text = getString(R.string.cancer)

        }
    }

    private fun btnAction() {
        binding.explanation.text = getString(R.string.cancer)
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.kompas.id/search?q=kanker%20kulit")
        )
        binding.btnNews.setOnClickListener { startActivity(intent) }
    }

    companion object {
        const val EXTRA_URI = "extra_URI"
        const val EXTRA_RESULT = "extra_result"

    }
}