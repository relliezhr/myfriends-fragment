package com.example.friendsbenefit

import MyFriend
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.friendsbenefit.databinding.FriendsFragmentBinding

class MyFriendsFragment : Fragment() {

    private lateinit var myFriendList : MutableList<MyFriend>
    private lateinit var binding: FriendsFragmentBinding

    private fun simulationMyFriendsData() {
        myFriendList = ArrayList()
        myFriendList.add(MyFriend("Relliye", "Perempuan",
            "relliye@gmail.com", "082140333911", "Tuban"))
        myFriendList.add(MyFriend("Ujang", "Laki-laki",
            "ujang@gmail.com", "081213416171", "Malang"))
        myFriendList.add(MyFriend("Soepomo", "Laki-laki",
            "soepomo@gmail.com", "08941796577", "Malang"))
    }

    private fun showMyFriends(){
        binding.listMyFriends.layoutManager = LinearLayoutManager(activity)
        binding.listMyFriends.adapter = MyFriendAdapter(requireActivity(),
            myFriendList as ArrayList<MyFriend>
        )
    }


    companion object {
        fun newInstance(): MyFriendsFragment {
            return MyFriendsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FriendsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.fabAddFriend.setOnClickListener {
            (activity as MainActivity).tampilMyFriendsAddFragment()
        }
        simulationMyFriendsData()
        showMyFriends()
    }
}
