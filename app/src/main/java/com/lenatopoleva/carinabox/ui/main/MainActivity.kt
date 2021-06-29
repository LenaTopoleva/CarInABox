package com.lenatopoleva.carinabox.ui.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.View.ROTATION
import android.view.animation.*
import com.lenatopoleva.carinabox.R
import com.lenatopoleva.carinabox.mvp.presenter.MainPresenter
import com.lenatopoleva.carinabox.mvp.view.MainView
import com.lenatopoleva.carinabox.ui.utils.WindowDimensionsCalculator
import com.lenatopoleva.carinabox.ui.utils.createObjectAnimatorWithPath
import kotlinx.android.synthetic.main.activity_main.*
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter


class MainActivity : MvpAppCompatActivity(), MainView{

    val presenter: MainPresenter by moxyPresenter { MainPresenter() }

    var windowWidth = 0F
    var windowHeight = 0F

    private fun onAnimationEndCreateAnimation(animationCount: Int) = object: Animator.AnimatorListener{
        override fun onAnimationStart(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            presenter.createAnimationNumber(animationCount)
        }
        override fun onAnimationCancel(animation: Animator?) {}
        override fun onAnimationRepeat(animation: Animator?) {}
    }

    private val clickListener = View.OnClickListener {
        presenter.carViewOnClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        carView.setOnClickListener(clickListener)
    }

    override fun createLeftBottomFithAnimation() {
        val animationTranslation = createObjectAnimatorWithPath(carView,0f, carView.y, 300 )
        animationTranslation.interpolator = LinearInterpolator()
        animationTranslation.start()
    }

    override fun createLeftBottomFourthAnimation() {
        val animationTranslation = createObjectAnimatorWithPath(carView,carView.x - 50f,
                windowHeight - carView.height, 450 )
        animationTranslation.interpolator = AccelerateDecelerateInterpolator()
        val animationRotation = ObjectAnimator.ofFloat(carView, ROTATION, -270f, -360f)
        animationRotation.interpolator = LinearInterpolator()
        val set = AnimatorSet()
        set.playSequentially(animationTranslation, animationRotation)
        set.duration = 700
        set.addListener(onAnimationEndCreateAnimation(8))
        set.start()
    }

    override fun createLeftBottomThirdAnimation() {
        val animationTranslation = createObjectAnimatorWithPath(carView,carView.x + carView.width,
                carView.y + 300f, null )
        val animationRotation3 = ObjectAnimator.ofFloat(carView, ROTATION, -180f, -270f)
        val set = AnimatorSet()
        set.playTogether(animationRotation3, animationTranslation)
        set.duration = 500
        set.interpolator = AccelerateDecelerateInterpolator()
        set.addListener(onAnimationEndCreateAnimation(7))
        set.start()

    }

    override fun createLeftBottomSecondAnimation() {
        val animation = createObjectAnimatorWithPath(carView,0f - carView.width, carView.y,
                500 )
        animation.interpolator = DecelerateInterpolator()

        animation.addListener(onAnimationEndCreateAnimation(6))
        animation.start()    }

    override fun createLeftBottomFirstAnimation() {
        val animationTranslation1 = createObjectAnimatorWithPath(carView,carView.x - 150f,
                carView.y + 100f, null )

        val animationRotation1 = ObjectAnimator.ofFloat(carView, ROTATION, -90f, -180f)
        val set = AnimatorSet()
        set.playTogether(animationRotation1, animationTranslation1)
        set.duration = 400
        set.addListener(onAnimationEndCreateAnimation(5))
        set.start()
    }

    override fun createRightTopThirdAnimation() {
        val animation = createObjectAnimatorWithPath(carView, carView.x, 0f - carView.height,
                700 )
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }

    override fun createRightTopSecondAnimation() {
        val animation2 = ObjectAnimator.ofFloat(carView, ROTATION, 0F, -90F)
        animation2.duration = 300
        animation2.addListener(onAnimationEndCreateAnimation(3))
        animation2.start()
    }

    override fun createRightTopFirstAnimation() {
        //Устаавливаем точку вращения
        carView.pivotX = carView.width.toFloat();
        carView.pivotY = carView.height.toFloat();

        val animator = createObjectAnimatorWithPath(carView, (windowWidth - carView.layoutParams.width),
                carView.y, 500)
        animator.interpolator = DecelerateInterpolator()
        animator.addListener(onAnimationEndCreateAnimation(2))
        animator.start()
    }

    override fun getWindowDimensions() {
        val windowDimensions = WindowDimensionsCalculator().getWindowDimensions(this)
        windowWidth = windowDimensions[0]
        windowHeight = windowDimensions[1]
    }
}
