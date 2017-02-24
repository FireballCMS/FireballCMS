val playVersion = "2.5.12"
val scalafxVersion = "8.0.92-R10"
val silhouetteVersion = "4.0.0"
val controlsfxVersion = "8.40.12"
val enzoVersion = "0.3.6"
val medusaVersion = "7.6"
val afterBurnerVersion = "1.7.0"

name := "Fireball Central Management System"
version := "1.0"
scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

// Format: libraryDependencies += groupID % artifactID % revision
libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    "com.mohiva" %% "play-silhouette" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-password-bcrypt" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-crypto-jca" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-persistence" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-testkit" % silhouetteVersion % "test",
    "org.scalafx" %% "scalafx" % scalafxVersion,

    "eu.hansolo.enzo" % "Enzo" % enzoVersion,
    "com.airhacks" % "afterburner.fx" % afterBurnerVersion,
    "eu.hansolo" % "Medusa" % medusaVersion,
    "org.controlsfx" % "controlsfx" % controlsfxVersion
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers ++= Seq(
    "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    Resolver.jcenterRepo,
    "Atlassian Releases" at "https://maven.atlassian.com/public/"
)
