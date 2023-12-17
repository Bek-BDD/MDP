package com.bow.foodiepal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class ContactFragment: Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_contact, container, false)

            val callButton: Button = view.findViewById(R.id.buttonCall)
            callButton.setOnClickListener {
                makePhoneCall("2025903963") // Replace with your phone number
            }

            val emailButton: Button = view.findViewById(R.id.buttonEmail)
            emailButton.setOnClickListener {
                sendEmail("bereket.dentamo@miu.edu")
            }

            return view
        }

        private fun makePhoneCall(number: String) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            startActivity(intent)
        }

        private fun sendEmail(email: String) {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:")
                putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                putExtra(Intent.EXTRA_SUBJECT, "Inquiry from FoodiePal App")
            }
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
            }
        }
    }

