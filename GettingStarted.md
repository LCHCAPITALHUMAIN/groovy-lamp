# Getting started #

  1. Download the zip file from the downloads section
  1. Unzip it
  1. Enable the SCGI handler for .groovy files in your web server (see WebServerInstallation)
  1. Run Groovy Runner. (`java -jar GroovyLamp.jar -port=4444`, change port to be the same as in the SCGI configuration)
  1. Copy the test.groovy file to your web server's doc root
  1. Restart your web server
  1. Navigate to /test.groovy in your browser!