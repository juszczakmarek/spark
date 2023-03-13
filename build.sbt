name := "spark"
version := "1.1.0"
scalaVersion := "2.12.13"

initialize := {
  initialize.value // run the previous initialization
  val requiredJdk = Set(VersionNumber("11"), VersionNumber("17"))
  val currentJdk = VersionNumber(sys.props("java.specification.version"))
  assert(requiredJdk.contains(currentJdk), s"Unsupported JDK: java.specification.version $currentJdk not in" +
    s" $requiredJdk")
}

val SparkVersion = "3.3.1"

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.4",
  "org.apache.spark" %% "spark-sql" % SparkVersion,
  "com.google.cloud.bigdataoss" % "gcs-connector" % "hadoop2-2.2.2",
  "com.google.cloud.spark" %% "spark-bigquery-with-dependencies" % "0.21.1",
  "joda-time" % "joda-time" % "2.10.10",
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "com.holdenkarau" %% "spark-testing-base" % s"${SparkVersion}_1.3.0" % Test,
  "com.github.pathikrit" %% "better-files" % "3.9.1" % Test
)

dependencyOverrides ++= Seq(
  "org.apache.hadoop" % "hadoop-client" % "2.10.1"
)

javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
