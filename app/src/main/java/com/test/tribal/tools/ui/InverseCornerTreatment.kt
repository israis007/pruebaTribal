package com.test.tribal.tools.ui

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath

class InverseCornerTreatment(private val direction: DIRECTION) : CornerTreatment() {

    override fun getCornerPath(
        shapePath: ShapePath,
        angle: Float,
        interpolation: Float,
        radius: Float
    ) {
        val interpolatedRadius = radius * interpolation
        shapePath.reset(0f, - radius * interpolation, if (direction == DIRECTION.LEFT) 270f else 270f,if (direction == DIRECTION.LEFT) 270 - angle else 270 - angle)
        shapePath.addArc(
            if (direction == DIRECTION.LEFT) 0f else -2 * interpolatedRadius,
            -2 * interpolatedRadius,
            if (direction == DIRECTION.LEFT) 2 * interpolatedRadius else 0f,
            0f,
            if (direction == DIRECTION.LEFT) 180f else 180f,
            if (direction == DIRECTION.LEFT) - angle else -angle)
    }

    enum class DIRECTION {
        RIGHT,
        LEFT
    }
}