package com.example.scooter

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.text.TextWatcher

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PaymentDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_payment_details, container, false)

        // Get a reference to the expiration date EditText view
        val expirationDateEditText: EditText = view.findViewById(R.id.expirationDateEditText)

        // Add a TextWatcher to the expiration date EditText view
        expirationDateEditText.addTextChangedListener(object : TextWatcher {
            private var isFormatting = false
            private var slashInserted = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // If the user typed in two digits and a slash hasn't been inserted yet
                if (!isFormatting && !slashInserted && s?.length == 2 && count == 1) {
                    isFormatting = true
                    slashInserted = true
                    expirationDateEditText.setText(String.format("%s/", s))
                    expirationDateEditText.setSelection(expirationDateEditText.text.length)
                } else if (!isFormatting && slashInserted && s?.length == 2 && count == 0) {
                    isFormatting = true
                    slashInserted = false
                    expirationDateEditText.setText(s.subSequence(0, s.length - 1))
                    expirationDateEditText.setSelection(expirationDateEditText.text.length)
                } else {
                    slashInserted = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                isFormatting = false
            }
        })

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymentDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}