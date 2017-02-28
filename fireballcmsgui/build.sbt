val controlsfxVersion = "8.40.12"
val enzoVersion = "0.3.6"
val medusaVersion = "7.6"
val afterBurnerVersion = "1.7.0"
val tilesVersion = "1.3.5"
val scalafxVersion = "8.0.92-R10"


lazy val root = (project in file("."))
  .settings(
      name := "asgardcms",
      version := "1.0-SNAPSHOT",
      organization := "com.ncc490.cmpt405",
      autoScalaLibrary := false,
      publishMavenStyle := true,
      crossPaths := false,

      // disable using the project's base directory as a source directory
      sourcesInBase := false,
      libraryDependencies ++= Seq(
          "com.airhacks" % "afterburner.fx" % afterBurnerVersion,
          "eu.hansolo.enzo" % "Enzo" % enzoVersion,
          "eu.hansolo" % "Medusa" % medusaVersion,
          "eu.hansolo" % "tilesfx" % tilesVersion,
          "org.controlsfx" % "controlsfx" % controlsfxVersion,
          "org.scalafx" %% "scalafx" % scalafxVersion
      )
  )

javacOptions ++= Seq("-source", "1.8")

resolvers ++= Seq(
    Resolver.jcenterRepo,
    "Atlassian Releases" at "https://maven.atlassian.com/public/",
    "JBoss" at "https://repository.jboss.org/"
)
