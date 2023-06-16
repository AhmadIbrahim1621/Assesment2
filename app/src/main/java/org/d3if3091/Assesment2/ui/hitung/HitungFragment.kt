package org.d3if3091.Assesment2.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3091.Assesment2.R
import org.d3if3091.Assesment2.databinding.FragmentHitungBinding
import org.d3if3091.Assesment2.db.HexaDb
import org.d3if3091.Assesment2.model.HasilHexa

class HitungFragment: Fragment(){
    private lateinit var binding: FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = HexaDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCalculate.setOnClickListener { hitungHexa() }
        binding.shareButton.setOnClickListener { shareData() }

        viewModel.getHasilHexa().observe(requireActivity(), { showResult(it) })
        viewModel.scheduleUpdater(requireActivity().application)
    }

    private fun shareData() {
        val message = getString(
            R.string.bagikan_template,
            binding.editTextSideLength.text,
            binding.textViewResult.text
        )
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun hitungHexa() {
        val jumlah = binding.editTextSideLength.text.toString()
        if (TextUtils.isEmpty(jumlah)) {
            Toast.makeText(context, R.string.jumlah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungHexa(
            jumlah.toDouble()
        )
    }

    private fun showResult(result: HasilHexa?) {
        if (result == null) return

        binding.textViewResult.text = getString(R.string.total_x, result.total)

        binding.buttonGroup.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}




