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

package com.google.android.stardroid.units;

import com.google.android.stardroid.util.Geometry;
import com.google.android.stardroid.util.MathUtil;

/**
 * This class corresponds to an object's location in Euclidean space
 * when it is projected onto a unit sphere (with the Earth at the
 * center).
 *
 * @author Brent Bryan
 */
public class GeocentricCoordinates extends Vector3 {

  public GeocentricCoordinates(float x, float y, float z) {
    super(x, y, z);
  }

  /** Recomputes the x, y, and z variables in this class based on the specified
   * {@link RaDec}.
   */
  public void updateFromRaDec(RaDec raDec) {
    updateFromRaDec(raDec.getRa(), raDec.getDec());
  }


  /**
   * Updates these coordinates with the given ra and dec in degrees.
   */
  private void updateFromRaDec(float ra, float dec) {
    float raRadians = ra * Geometry.DEGREES_TO_RADIANS;
    float decRadians = dec * Geometry.DEGREES_TO_RADIANS;

    this.x = MathUtil.cos(raRadians) * MathUtil.cos(decRadians);
    this.y = MathUtil.sin(raRadians) * MathUtil.cos(decRadians);
    this.z = MathUtil.sin(decRadians);
  }

  /** Returns the RA in degrees */
  public float getRa() {
    // Assumes unit sphere.
    return Geometry.RADIANS_TO_DEGREES * MathUtil.atan2(y, x);
  }

  /** Returns the declination in degrees */
  public float getDec() {
    // Assumes unit sphere.
    return Geometry.RADIANS_TO_DEGREES * MathUtil.asin(z);
  }

  /**
   * Convert ra and dec to x,y,z where the point is place on the unit sphere.
   */
  public static GeocentricCoordinates getInstance(RaDec raDec) {
    return getInstance(raDec.getRa(), raDec.getDec());
  }

  public static GeocentricCoordinates getInstance(float ra, float dec) {
    GeocentricCoordinates coords = new GeocentricCoordinates(0.0f, 0.0f, 0.0f);
    coords.updateFromRaDec(ra, dec);
    return coords;
  }

  @Override
  public float[] toFloatArray() {
    return new float[] {x, y, z};
  }

  @Override
  public GeocentricCoordinates copy() {
    return new GeocentricCoordinates(x, y, z);
  }

  public static GeocentricCoordinates getInstanceFromVector3(Vector3 v) {
    return new GeocentricCoordinates(v.x, v.y, v.z);
  }
}