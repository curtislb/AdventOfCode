package com.curtislb.adventofcode.year2021.day19.beacon

import com.curtislb.adventofcode.common.geometry.Point3D

/**
 * A 3D spatial orientation representing the direction a [Scanner] is facing and the direction it
 * considers up from that facing.
 */
enum class ScannerOrientation {
    /**
     * Facing positive x, considering positive y as "up".
     *
     * This is the default orientation assumed for all [Scanner]s.
     */
    X_Y_Z,

    /**
     * Facing positive x, considering positive z as "up".
     */
    X_Z_NY,

    /**
     * Facing positive x, considering negative y as "up".
     */
    X_NY_NZ,

    /**
     * Facing positive x, considering negative z as "up".
     */
    X_NZ_Y,

    /**
     * Facing positive y, considering positive x as "up".
     */
    Y_X_NZ,

    /**
     * Facing positive y, considering positive z as "up".
     */
    Y_Z_X,

    /**
     * Facing positive y, considering negative x as "up".
     */
    Y_NX_Z,

    /**
     * Facing positive y, considering negative z as "up".
     */
    Y_NZ_NX,

    /**
     * Facing positive z, considering positive x as "up".
     */
    Z_X_Y,

    /**
     * Facing positive z, considering positive y as "up".
     */
    Z_Y_NX,

    /**
     * Facing positive z, considering negative x as "up".
     */
    Z_NX_NY,

    /**
     * Facing positive z, considering negative y as "up".
     */
    Z_NY_X,

    /**
     * Facing negative x, considering positive y as "up".
     */
    NX_Y_NZ,

    /**
     * Facing negative x, considering positive z as "up".
     */
    NX_Z_Y,

    /**
     * Facing negative x, considering negative y as "up".
     */
    NX_NY_Z,

    /**
     * Facing negative x, considering negative z as "up".
     */
    NX_NZ_NY,

    /**
     * Facing negative y, considering positive x as "up".
     */
    NY_X_Z,

    /**
     * Facing negative y, considering positive z as "up".
     */
    NY_Z_NX,

    /**
     * Facing negative y, considering negative x as "up".
     */
    NY_NX_NZ,

    /**
     * Facing negative y, considering negative z as "up".
     */
    NY_NZ_X,

    /**
     * Facing negative z, considering positive x as "up".
     */
    NZ_X_NY,

    /**
     * Facing negative z, considering positive y as "up".
     */
    NZ_Y_X,

    /**
     * Facing negative z, considering negative x as "up".
     */
    NZ_NX_Y,

    /**
     * Facing negative z, considering negative y as "up".
     */
    NZ_NY_NX;

    /**
     * Returns the result of transforming the given [point] to this orientation.
     */
    fun transform(point: Point3D): Point3D = when (this) {
        X_Y_Z -> point
        X_Z_NY -> Point3D(point.x, point.z, -point.y)
        X_NY_NZ -> Point3D(point.x, -point.y, -point.z)
        X_NZ_Y -> Point3D(point.x, -point.z, point.y)
        Y_X_NZ -> Point3D(point.y, point.x, -point.z)
        Y_Z_X -> Point3D(point.y, point.z, point.x)
        Y_NX_Z -> Point3D(point.y, -point.x, point.z)
        Y_NZ_NX -> Point3D(point.y, -point.z, -point.x)
        Z_X_Y -> Point3D(point.z, point.x, point.y)
        Z_Y_NX -> Point3D(point.z, point.y, -point.x)
        Z_NX_NY -> Point3D(point.z, -point.x, -point.y)
        Z_NY_X -> Point3D(point.z, -point.y, point.x)
        NX_Y_NZ -> Point3D(-point.x, point.y, -point.z)
        NX_Z_Y -> Point3D(-point.x, point.z, point.y)
        NX_NY_Z -> Point3D(-point.x, -point.y, point.z)
        NX_NZ_NY -> Point3D(-point.x, -point.z, -point.y)
        NY_X_Z -> Point3D(-point.y, point.x, point.z)
        NY_Z_NX -> Point3D(-point.y, point.z, -point.x)
        NY_NX_NZ -> Point3D(-point.y, -point.x, -point.z)
        NY_NZ_X -> Point3D(-point.y, -point.z, point.x)
        NZ_X_NY -> Point3D(-point.z, point.x, -point.y)
        NZ_Y_X -> Point3D(-point.z, point.y, point.x)
        NZ_NX_Y -> Point3D(-point.z, -point.x, point.y)
        NZ_NY_NX -> Point3D(-point.z, -point.y, -point.x)
    }
}
