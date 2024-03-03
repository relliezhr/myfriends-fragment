package com.example.friendsbenefit

import MyFriend
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.friendsbenefit.databinding.AddFriendsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyFriendsAddFragment : Fragment() {

    private lateinit var binding: AddFriendsBinding

    companion object {
        fun newInstance(): MyFriendsAddFragment {
            return MyFriendsAddFragment()
        }
    }
    private var namaInput : String = ""
    private var emailInput : String = ""
    private var telpInput : String = ""
    private var alamatInput : String = ""
    private var genderInput : String = ""
    private var db: AppDatabase? = null
    private var myFriendDao: MyFriendDao? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AddFriendsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()
    }
    private fun initLocalDB() {
        db = AppDatabase.getAppDataBase(requireActivity())
        myFriendDao = db?.myFriendDao()
    }

    private fun initView() {
        binding.btnSave.setOnClickListener {
            (activity as MainActivity).tampilMyFriendsFragment()
            setDataSpinnerGener()
        }
    }
    private fun setDataSpinnerGener() {
        val adapter = ArrayAdapter.createFromResource(activity!!,
            R.array.gender_list,
            android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_drop
                down_item)
        spinnerGender.adapter = adapter
    }
    private fun validasiInput() {
        private val edtName: TextView = itemView.findViewById(R.id.edtName)

        namaInput = edtName.text.toString()
        emailInput = edtEmail.text.toString()
        telpInput = edtTelp.text.toString()
        alamatInput = edtAddress.text.toString()
        genderInput = spinnerGender.selectedItem.toString()
        when{
            namaInput.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
                genderInput.equals("Pilih kelamin") > tampilToast("Kelamin harus dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak boleh kosong"
                telpInput.isEmpty() -> edtTelp.error = "Telp tidak boleh kosong"
                alamatInput.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"

            else -> {
                val teman = MyFriend(nama = namaInput, kelamin =
                genderInput, email = emailInput, telp = telpInput, alamat =
                alamatInput)
                tambahDataTeman(teman)
            }
        }
    }
    private fun tambahDataTeman(teman: MyFriend) : Job {
        return GlobalScope.launch {
            myFriendDao?.tambahTeman(teman)
            (activity as MainActivity).tampilMyFriendsFragment()
        }
    }
    private fun tampilToast(message: String) {
        Toast.makeText(requireActivity(), message,
            Toast.LENGTH_SHORT).show()
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
