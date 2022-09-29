package com.zeoharlem.append.practise

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment


class ChatListFragment : Fragment(R.layout.fragment_chat_list) {

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        //setHasOptionsMenu(true)
//        return inflater.inflate(R.layout.fragment_chat_list, container, false)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.chat_list_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_settings -> {
                        val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
                        val navController = navHostFragment.navController
                        val action = ChatListFragmentDirections.actionChatListToSettings()
                        navController.navigate(action)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

//    @Deprecated("Deprecated in Java", ReplaceWith("inflater.inflate(R.menu.chat_list_menu, menu)"))
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//
//        inflater.inflate(R.menu.chat_list_menu, menu)
//    }
//
//    @Deprecated("change")
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//
//        when (item.itemId) {
//            R.id.action_settings -> {
//                val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_frag) as NavHostFragment
//                val navController = navHostFragment.navController
//                val action = ChatListFragmentDirections.actionChatListToSettings()
//                navController.navigate(action)
//                return true
//            }
//        }
//
//        return super.onOptionsItemSelected(item)
//    }
}