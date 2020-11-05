// Copyright 2010 Google Inc.
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

import junit.framework.TestCase;
import static com.google.common.truth.Truth.assertThat;


public class GeocentricCoordinatesTest extends TestCase {
  public void testEquals() {
    Vector3 one = new GeocentricCoordinates(1, 2, 3);
    Vector3 two = new GeocentricCoordinates(2, 4, 6);
    one.scale(2);
    assertThat(one).isEqualTo(two);;
    assertThat(one).isNotSameInstanceAs(two);
    assertThat(one.hashCode()).isEqualTo(two.hashCode());
  }
}
