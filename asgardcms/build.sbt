val controlsfxVersion = "8.40.12"
val enzoVersion = "0.3.6"
val medusaVersion = "7.6"
val afterBurnerVersion = "1.7.0"
val tilesVersion = "1.3.4"
val scalafxVersion = "8.0.92-R10"

name := "Fireball Central Management System gui"
version := "1.0"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "com.airhacks" % "afterburner.fx" % afterBurnerVersion,
    "eu.hansolo.enzo" % "Enzo" % enzoVersion,
    "eu.hansolo" % "Medusa" % medusaVersion,
    "eu.hansolo" % "tilesfx" % tilesVersion,
    "org.controlsfx" % "controlsfx" % controlsfxVersion,
    "org.scalafx" %% "scalafx" % scalafxVersion
)
