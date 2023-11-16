package com.exercise.tbchomeworknine

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.tbchomeworknine.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Adapter.CategoryClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ItemViewModel
    private lateinit var titlesAdapter: Adapter
    private lateinit var outputAdapter: OutputAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        prepareRecyclerView()
        prepareRecyclerViewOutput()

        viewModel.choices.observe(this) { choices ->
            titlesAdapter.submitList(choices)
        }

        viewModel.outputs.observe(this){ output->
            outputAdapter.submitList(output)
        }

        viewModel.filterOutputsByCategory(TypeOfItem.ALL)
    }

    private fun prepareRecyclerView(){
        viewModel.getChoices()
        titlesAdapter = Adapter(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = titlesAdapter
        }
    }

    private fun prepareRecyclerViewOutput(){
        outputAdapter = OutputAdapter()
        binding.recyclerViewOutput.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = outputAdapter
        }
    }

    override fun onCategoryClicked(type: TypeOfItem) {
        viewModel.filterOutputsByCategory(type)
    }

}