package com.example.sample32binding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sample32binding.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val jsonStr = assets.open("data.json").reader().readText()

        val jsonArray = JSONArray(jsonStr)

        for(i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            binding.textView.append("\n----------------\n")

            val id = jsonObject.getString("id")
            val language = jsonObject.getString("language")

            binding.textView.append("""
                $id
            """.trimIndent())

            binding.textView.append("""
                $language
            """.trimIndent())
        }
    }
}