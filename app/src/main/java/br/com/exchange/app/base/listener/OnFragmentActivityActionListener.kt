package br.com.exchange.app.base.listener

import androidx.fragment.app.Fragment

interface OnFragmentActivityActionListener {

    fun openFragment(fragment: Fragment, dismissBack: Boolean = false)

    fun closeCurrentFragment()

    fun setToolbarVisibility(isVisible: Boolean)

    fun setToolbarTitle(titleResource: Int)

    fun backButtonEnable(isEnable: Boolean)

    fun toggleHomeButton(isVisible: Boolean)

    fun toogleToolbarLogo(isVisible: Boolean) {
    }

    fun toogleToolbarTitle(isVisible: Boolean, title: String) {
    }

    fun toogleNavigationBar(isLocked: Boolean) {
    }

    fun backHomeButton(isVisible: Boolean){}
}