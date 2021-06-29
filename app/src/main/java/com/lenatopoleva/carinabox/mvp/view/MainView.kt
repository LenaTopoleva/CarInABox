package com.lenatopoleva.carinabox.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainView: MvpView {
    fun getWindowDimensions()
    fun createRightTopFirstAnimation()
    fun createRightTopSecondAnimation()
    fun createRightTopThirdAnimation()
    fun createLeftBottomFirstAnimation()
    fun createLeftBottomSecondAnimation()
    fun createLeftBottomThirdAnimation()
    fun createLeftBottomFourthAnimation()
    fun createLeftBottomFithAnimation()
}