name := "asgardcentralmgmtsystem"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    "org.scalafx" %% "scalafx" % "8.0.92-R10"
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  
