<%
ui.decorateWith("appui", "standardEmrPage")
ui.includeCss("nmrsappointment", "style.css")
ui.includeCss("nmrsappointment", "bootstrap.min.css")
ui.includeCss("nmrsappointment", "fontawesome.min.css")
ui.includeJavascript("nmrsappointment", "fontawesome.min.js")
ui.includeJavascript("nmrsappointment", "appointment-scripts.js")
%>


${ ui.includeFragment("nmrsappointment", "downloadPage") }
