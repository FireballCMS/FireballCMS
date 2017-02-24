val silhouetteVersion = "4.0.0"

name := "Fireball Central Management System api"
version := "1.0"
scalaVersion := "2.11.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    "com.mohiva" %% "play-silhouette" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-password-bcrypt" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-crypto-jca" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-persistence" % silhouetteVersion,
    "com.mohiva" %% "play-silhouette-testkit" % silhouetteVersion % "test"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers ++= Seq(
    "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
    Resolver.jcenterRepo,
    "Atlassian Releases" at "https://maven.atlassian.com/public/"
)
