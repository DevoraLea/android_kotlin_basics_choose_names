package com.example.namesideias

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.namesideias.databinding.FragmentLetterBinding


class LetterFragment : Fragment() {

private var _binding:FragmentLetterBinding? = null
private val binding get() = _binding!!

var isLinearLayout = true

companion object{
    var genreNames:GenreNames = GenreNames.BOYS
}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLetterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chooseLayout()
        binding.switchNames.isChecked = false
        binding.txtGenre.text= "NAMES OF: BOYS"
        binding.switchNames.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked){
                genreNames = GenreNames.BOYS
                binding.txtGenre.text= "NAMES OF: BOYS"
                binding.switchNames.setBackgroundColor(Color.BLUE)
        }
            else{
                genreNames = GenreNames.GIRLS
                binding.txtGenre.text= "NAMES OF: GIRLS"
                binding.switchNames.setBackgroundColor(Color.MAGENTA)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_item,menu)
        setIcon(menu.findItem(R.id.action_layout))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_layout -> {
            isLinearLayout = !isLinearLayout
            chooseLayout()
            setIcon(item)
                true
        }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun chooseLayout(){
        if(isLinearLayout){
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            binding.recyclerView.layoutManager = GridLayoutManager(context,3)
        }
        binding.recyclerView.adapter = LetterAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }
    fun setIcon(item: MenuItem){
        when(isLinearLayout){
            true -> item.setIcon(R.drawable.ic_list)
            false -> item.setIcon(R.drawable.ic_grid)
        }
    }
}