<%
ui.decorateWith("appui", "standardEmrPage")
ui.includeCss("nmrsappointment", "style.css")
ui.includeCss("nmrsappointment", "bootstrap.min.css")
ui.includeCss("nmrsappointment", "fontawesome.min.css")
ui.includeJavascript("nmrsappointment", "fontawesome.min.js")
ui.includeJavascript("nmrsappointment", "getCurrentAppt.js")
%>


${ ui.includeFragment("nmrsappointment", "landingPage") }
