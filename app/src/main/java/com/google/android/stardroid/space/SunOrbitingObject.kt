package com.google.android.stardroid.space

import com.google.android.stardroid.provider.ephemeris.Planet
import com.google.android.stardroid.units.HeliocentricCoordinates
import com.google.android.stardroid.units.HeliocentricCoordinates.Companion.heliocentricCoordinatesFromOrbitalElements
import com.google.android.stardroid.units.RaDec
import java.util.*

/**
 * An object that orbits the sun.
 */
open class SunOrbitingObject(private val planet : Planet) : SolarSystemObject() {
    override fun getRaDec(date: Date): RaDec {
        val earthCoords = getEarthHeliocentricCoordinates(date)
        var myCoords = getMyHeliocentricCoordinates(date)
        myCoords.subtract(earthCoords)
        val equ = HeliocentricCoordinates.convertToEquatorialCoordinates(myCoords)
        return RaDec.calculateRaDecDist(equ)
    }

    protected open fun getMyHeliocentricCoordinates(date: Date) =
        heliocentricCoordinatesFromOrbitalElements(planet.getOrbitalElements(date))

    protected fun getEarthHeliocentricCoordinates(date: Date) =
        heliocentricCoordinatesFromOrbitalElements(Planet.Sun.getOrbitalElements(date))
}