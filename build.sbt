import AssemblyKeys._

name := "scalding"

version := "0.3.3"

organization := "com.twitter"

scalaVersion := "2.8.1"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"

libraryDependencies += "cascading" % "cascading-core" % "2.0.0-wip-236"

libraryDependencies += "cascading" % "cascading-local" % "2.0.0-wip-236"

libraryDependencies += "cascading" % "cascading-hadoop" % "2.0.0-wip-236"

libraryDependencies += "cascading.kryo" % "cascading.kryo" % "0.2.1"

libraryDependencies += "com.twitter" % "meat-locker" % "0.1.6"

libraryDependencies += "commons-lang" % "commons-lang" % "2.4"

libraryDependencies += "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"

parallelExecution in Test := false

seq(assemblySettings: _*)

// Janino includes a broken signature, and is not needed:
excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  cp filter {_.data.getName == "janino-2.5.16.jar"}
}

// Publishing options:

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT")) 
    Some("snapshots" at nexus + "content/repositories/snapshots") 
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>http://twitter.com/scalding</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      <distribution>repo</distribution>
      <comments>A business-friendly OSS license</comments>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:twitter/scalding.git</url>
    <connection>scm:git:git@github.com:twitter/scalding.git</connection>
  </scm>
  <developers>
    <developer>
      <id>oscar</id>
      <name>Oscar Boykin</name>
      <url>http://twitter.com/posco</url>
    </developer>
    <developer>
      <id>argyris</id>
      <name>Argyris Zymnis</name>
      <url>http://twitter.com/argyris</url>
    </developer>
  </developers>)
