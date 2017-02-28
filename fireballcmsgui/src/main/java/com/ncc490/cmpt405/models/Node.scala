package com.ncc490.cmpt405.models

import java.util.Date

import com.ncc490.cmpt405.models.geo.GeoPoint

case class Node(name: String, dateAdded: Date, loc: GeoPoint) {
    private val dataConsumers = Seq()
}
