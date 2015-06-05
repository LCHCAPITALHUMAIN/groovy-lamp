# Template Language #

Files are inteperated using the standard [Simple Template Engine](http://groovy.codehaus.org/Groovy+Templates) syntax:

```
<%
def title = "Hello!"
%>
<html>

<h1>$title</h1>

<%
out.println "Bye!"
%>

</html>
```


# API #

The following global variables are defined when your script executes:

  * out   : Print Writer. I.e. out.println
  * request : Object that holds details about the HTTP request
    * request.env : Hash Map of environment variables
    * request.get :  Hash Map of GET query string vars
    * request.post : Hash Map of POST data
    * request.cookies : Hash Map of cookies
  * response: Object that holds details about your response
    * response.setCookie(key, value)  Set a cookie