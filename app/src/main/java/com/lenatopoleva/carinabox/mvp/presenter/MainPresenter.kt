package com.lenatopoleva.carinabox.mvp.presenter

import com.lenatopoleva.carinabox.mvp.view.MainView
import moxy.MvpPresenter

class MainPresenter: MvpPresenter<MainView>() {

    companion object {
        const val rightTopPosition = "RIGHT_TOP"
        const val leftBottomPosition = "LEFT_BOTTOM"
    }

    var carPosition = ""

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.getWindowDimensions()
    }

    fun carViewOnClick() {
        if (carPosition != rightTopPosition) createAnimationNumber(1)
        else createAnimationNumber(4)
    }

    fun createAnimationNumber(animationCount: Int) {
        when (animationCount) {
            1 -> viewState.createRightTopFirstAnimation()
            2 -> viewState.createRightTopSecondAnimation()
            3 -> {
                viewState.createRightTopThirdAnimation()
                carPosition = rightTopPosition
            }
            4 -> viewState.createLeftBottomFirstAnimation()
            5 -> viewState.createLeftBottomSecondAnimation()
            6 -> viewState.createLeftBottomThirdAnimation()
            7 -> viewState.createLeftBottomFourthAnimation()
            8 -> {
                viewState.createLeftBottomFithAnimation()
                carPosition = leftBottomPosition
            }
        }
    }
}