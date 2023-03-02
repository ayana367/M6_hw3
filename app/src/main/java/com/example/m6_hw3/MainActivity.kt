package com.example.m6_hw3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.example.m6_hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private var list: ArrayList<String> = arrayListOf()
    private var replaceWords = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveText()
        initAdapter()
        hash()
    }

    private fun saveText() {
        binding.ivSend.setOnClickListener {
            searchHash()
            binding.etText.text.clear()
        }
    }

    private fun searchHash() {
        val messages = binding.etText.text.split(" ")
        for (message in messages) {
            if (message.startsWith("#")) {
                val newWord = message.replace(Regex("[-+.^:;?!#,]"), "")
                list.add(newWord)
            }
        }
    }

    private fun initAdapter() {
        adapter = Adapter(list, this::clickListener)
        binding.rvHashtag.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags: String) {
        binding.etText.setText(binding.etText.text.toString().replace(replaceWords, ""))
        binding.etText.setText("${binding.etText.text}#$hashTags ")
        binding.etText.setSelection(binding.etText.length())
    }

    private fun hash() {
        binding.etText.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.rvHashtag.isVisible = true
            }
        }
        binding.etText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {}
            override fun onTextChanged(text: CharSequence?, a1: Int, a2: Int, a3: Int) {
                val messages = text?.split(" ")
                if (messages != null) {
                    for (word in messages) {
                        replaceWords = word
                        binding.rvHashtag.isVisible = word.startsWith("#")
                    }
                }
            }

            override fun afterTextChanged(a0: Editable?) {
            }
        })
    }
}