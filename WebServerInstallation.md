# Introduction #

Describes how to configure the web server for use with Groovy Runner via the SCGI interface. Currently only lighttpd is shown. Apache is similar.


# LightHTTPD #

Edit your lighttpd.conf file so that “mod\_scgi” is listed in the modules and uncommented. Something like:

```
server.modules  = (
   #"mod_rewrite",
   #"mod_redirect",
   #"mod_alias",
   "mod_access",
   "mod_scgi")
```

At the end of the configuration file add the following:

```
scgi.server    = ( ".groovy" => (
   (
      "host" => "127.0.0.1",
      "port" => 4444
   )
))
```

This assumes that Groovy Runner will be on the local machine on port 4444 and that you want to use the extension .groovy as groovy files. You can configure the port that Groovy Runner opens with the 'port' configuration. See GettingStarted.