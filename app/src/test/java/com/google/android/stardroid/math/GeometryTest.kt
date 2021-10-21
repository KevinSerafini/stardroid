// Copyright 2008 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.android.stardroid.math

import com.google.android.stardroid.math.Geometry.calculateRotationMatrix
import com.google.android.stardroid.math.Geometry.getXYZ
import com.google.android.stardroid.math.Geometry.matrixMultiply
import com.google.android.stardroid.math.Geometry.matrixVectorMultiply
import com.google.android.stardroid.math.RaDec.Companion.fromGeocentricCoords
import com.google.common.truth.Truth.assertThat
import org.junit.Test

private const val TOL = 0.00001f

class GeometryTest {

    private val allTestValues = arrayOf(
        floatArrayOf(0f, 0f, 1f, 0f, 0f),
        floatArrayOf(90f, 0f, 0f, 1f, 0f),
        floatArrayOf(0f, 90f, 0f, 0f, 1f),
        floatArrayOf(180f, 0f, -1f, 0f, 0f),
        floatArrayOf(0f, -90f, 0f, 0f, -1f),
        floatArrayOf(270f, 0f, 0f, -1f, 0f)
    )

    @Test
    fun testSphericalToCartesians() {
        for (testValues in allTestValues) {
            val ra = testValues[0]
            val dec = testValues[1]
            val x = testValues[2]
            val y = testValues[3]
            val z = testValues[4]
            val (x1, y1, z1) = getXYZ(RaDec(ra, dec))
            assertThat(x1).isWithin(TOL).of(x)
            assertThat(y1).isWithin(TOL).of(y)
            assertThat(z1).isWithin(TOL).of(z)
        }
    }

    @Test
    fun testCartesiansToSphericals() {
        for (testValues in allTestValues) {
            val ra = testValues[0]
            val dec = testValues[1]
            val x = testValues[2]
            val y = testValues[3]
            val z = testValues[4]
            val (ra1, dec1) = fromGeocentricCoords(Vector3(x, y, z))
            assertThat(ra1).isWithin(TOL).of(ra)
            assertThat(dec1).isWithin(TOL).of(dec)
        }
    }

    @Test
    fun testCalculateRotationMatrix() {
        val noRotation = calculateRotationMatrix(0f, Vector3(1f, 2f, 3f))
        val identity = Matrix3x3(1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f)
        Matrix3x3Subject.assertThat(identity).isWithin(TOL).of(noRotation)
        val rotAboutZ = calculateRotationMatrix(90f, Vector3(0f, 0f, 1f))
        Matrix3x3Subject.assertThat(Matrix3x3(0f, 1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f)).isWithin(TOL)
            .of(rotAboutZ)
        val axis = Vector3(2f, -4f, 1f)
        axis.normalize()
        val rotA = calculateRotationMatrix(30f, axis)
        val rotB = calculateRotationMatrix(-30f, axis)
        val shouldBeIdentity = matrixMultiply(rotA, rotB)
        Matrix3x3Subject.assertThat(identity).isWithin(TOL).of(shouldBeIdentity)
        val axisPerpendicular = Vector3(4f, 2f, 0f)
        val rotatedAxisPerpendicular = matrixVectorMultiply(rotA, axisPerpendicular)

        // Should still be perpendicular
        assertThat(axis dot rotatedAxisPerpendicular).isWithin(TOL).of(0.0f)
        // And the angle between them should be 30 degrees
        axisPerpendicular.normalize()
        rotatedAxisPerpendicular.normalize()
        assertThat(axisPerpendicular dot rotatedAxisPerpendicular).isWithin(TOL)
            .of(
                Math.cos(30.0 * Geometry.DEGREES_TO_RADIANS).toFloat()
            )
    }
}