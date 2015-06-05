Love Groovy but hate java web servers? Wish you could use the Groovy language to write scripts in the same way as you do for PHP and other 'drop in the file and hit refresh' languages? Now you can!

Provides a simple Java proxy to connect to the web-server (Apache, Lighttpd) via the Simple CGI (SCGI) standard. The proxy handles requests for '.groovy' files and uses the Groovy script engine to run them.

Basic HTTP features are supported:

  * Cookies (get and set)
  * GET query string
  * POST data (currently only application/x-www-form-urlencoded)

Please read GettingStarted