import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "winecellar"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    "mysql" % "mysql-connector-java" % "5.1.12",
    "com.typesafe" % "slick_2.10.0-RC3" % "0.11.2"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

}
