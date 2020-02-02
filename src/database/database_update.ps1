$libDirectory     = "lib"
New-Item $libDirectory -type directory -force | Out-Null
foreach ($file in get-ChildItem $libDirectory) {
  $classpath += ";$($libDirectory)/$($file.Name)"
}
java -Denv=localhost -cp "$classpath" liquibase.integration.commandline.Main --changeLogFile=changelog\changelog-master.xml --defaultsFile=config\localhost.properties status update
