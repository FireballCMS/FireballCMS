package models

import java.util.Date

import models.geo.GeoPoint

case class Node(name: String, dateAdded: Date, loc: GeoPoint) {
    private val dataConsumers = Seq()
}
