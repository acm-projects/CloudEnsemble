package edu.utdallas.pages.controllers;

public class StaticContent {

    public static String generateEmailPage(String code) {
        return "<html>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Open+Sans\" />\n" +
                "<head>\n" +
                "    <style>\n" +
                "    body {\n" +
                "        background-color: rgb(253,253,253);\n" +
                "        font-family: \"Open Sans\";\n" +
                "    }\n" +
                "\n" +
                "    #image {\n" +
                "        width:200px;\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "        margin: 0 auto;\n" +
                "        margin-top:50px;\n" +
                "        display: block;\n" +
                "    }\n" +
                "\n" +
                "    #box {\n" +
                "        margin:37%;\n" +
                "        margin-top:2%;\n" +
                "        margin-bottom:80px;\n" +
                "        background-color: rgb(245,245,245);\n" +
                "        padding:28px;\n" +
                "        padding-top:25px;\n" +
                "        padding-bottom:25px;\n" +
                "        border-style:solid;\n" +
                "        border-color:rgb(180,180,180);\n" +
                "        border-radius:6px 6px;\n" +
                "        border-width:1px;\n" +
                "    }\n" +
                "\n" +
                "    #title {\n" +
                "        font-weight:bold;\n" +
                "        margin:0px;\n" +
                "        margin-top:7px;\n" +
                "        font-size: 26px;\n" +
                "        color: black;\n" +
                "    }\n" +
                "\n" +
                "    #desc {\n" +
                "        margin:0px;\n" +
                "        margin-top:7px;\n" +
                "        margin-bottom:4px;\n" +
                "        font-size: 14px;\n" +
                "        color: rgb(40,40,40);\n" +
                "    }\n" +
                "\n" +
                "    .label {\n" +
                "        margin:0px;\n" +
                "        margin-top:20px;\n" +
                "        margin-bottom:3px;\n" +
                "        font-size: 15px;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .field {\n" +
                "        width:100%;\n" +
                "        height:30px;\n" +
                "    }\n" +
                "\n" +
                "    #button {\n" +
                "\t    box-shadow:inset 0px -3px 7px 0px #29bbff;\n" +
                "\t    background:linear-gradient(to bottom, #2dabf9 5%, #0688fa 100%);\n" +
                "\t    background-color:#2dabf9;\n" +
                "\t    border-radius:3px;\n" +
                "\t    border:1px solid #0b0e07;\n" +
                "\t    display:inline-block;\n" +
                "\t    cursor:pointer;\n" +
                "\t    color:#ffffff;\n" +
                "\t    font-family:Arial;\n" +
                "\t    font-size:15px;\n" +
                "\t    text-decoration:none;\n" +
                "\t    text-shadow:0px 1px 0px #263666;\n" +
                "\t    width:100%;\n" +
                "\t    height:30px;\n" +
                "    }\n" +
                "    #button:hover {\n" +
                "\t    background:linear-gradient(to bottom, #0688fa 5%, #2dabf9 100%);\n" +
                "\t    background-color:#0688fa;\n" +
                "    }\n" +
                "    #button:active {\n" +
                "\t    position:relative;\n" +
                "\t    top:1px;\n" +
                "    }\n" +
                "\n" +
                "    hr {\n" +
                "        border: 0; height: 1px;\n" +
                "        background-image: -webkit-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.3), rgba(0,0,0,0));\n" +
                "        width:100%;\n" +
                "    }\n" +
                "    </style>\n" +
                "<body>\n" +
                "<div><img id=\"image\" src=\"cloud_ensemble_white_bg.jpg\" alt=\"Cloud Ensemble\"></div>\n" +
                "<div id=\"box\">\n" +
                "    <div id=\"title\">Reset Password</div>\n" +
                "    <div id=\"desc\">\n" +
                "        Please provide a new password for your account.\n" +
                "        Password must be between 5 and 40 characters.\n" +
                "    </div>\n" +
                "    <form action=\"verify-reset\" method=\"post\">\n" +
                "        <div class=\"label\">Password</div>\n" +
                "        <input class=\"field\" type=\"password\" name=\"new_password\"/><br><br>\n" +
                "        <input type=\"hidden\" name=\"token\" value=\"" + code + "\">\n" +
                "        <input id=\"button\" type=\"submit\" value=\"Submit\"/>\n" +
                "    </form>\n" +
                "</div>\n" +
                "<hr>\n" +
                "</body>\n" +
                "</html>";
    }

    public static String generateMessagePage(String title, String message) {
        return "<html>\n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"//fonts.googleapis.com/css?family=Open+Sans\" />\n" +
                "<head>\n" +
                "    <style>\n" +
                "    body {\n" +
                "        background-color: rgb(253,253,253);\n" +
                "        font-family: \"Open Sans\";\n" +
                "    }\n" +
                "\n" +
                "    #image {\n" +
                "        width:200px;\n" +
                "        display: flex;\n" +
                "        justify-content: center;\n" +
                "        margin: 0 auto;\n" +
                "        margin-top:50px;\n" +
                "        display: block;\n" +
                "    }\n" +
                "\n" +
                "    #box {\n" +
                "        text-align:center;\n" +
                "        height:150px;\n" +
                "        margin:39%;\n" +
                "        margin-top:2%;\n" +
                "        margin-bottom:80px;\n" +
                "        background-color: rgb(245,245,245);\n" +
                "        padding:28px;\n" +
                "        padding-top:25px;\n" +
                "        padding-bottom:25px;\n" +
                "        border-style:solid;\n" +
                "        border-color:rgb(180,180,180);\n" +
                "        border-radius:6px 6px;\n" +
                "        border-width:1px;\n" +
                "    }\n" +
                "\n" +
                "    #title {\n" +
                "        font-weight:bold;\n" +
                "        margin:0px;\n" +
                "        margin-top:7px;\n" +
                "        font-size: 18px;\n" +
                "        color: black;\n" +
                "    }\n" +
                "\n" +
                "    #desc {\n" +
                "        margin:0px;\n" +
                "        margin-top:7px;\n" +
                "        margin-bottom:4px;\n" +
                "        font-size: 14px;\n" +
                "        color: rgb(40,40,40);\n" +
                "    }\n" +
                "\n" +
                "    .label {\n" +
                "        margin:0px;\n" +
                "        margin-top:20px;\n" +
                "        margin-bottom:3px;\n" +
                "        font-size: 15px;\n" +
                "        font-weight: bold;\n" +
                "    }\n" +
                "\n" +
                "    .field {\n" +
                "        width:100%;\n" +
                "        height:30px;\n" +
                "    }\n" +
                "\n" +
                "    #button {\n" +
                "\t    box-shadow:inset 0px -3px 7px 0px #29bbff;\n" +
                "\t    background:linear-gradient(to bottom, #2dabf9 5%, #0688fa 100%);\n" +
                "\t    background-color:#2dabf9;\n" +
                "\t    border-radius:3px;\n" +
                "\t    border:1px solid #0b0e07;\n" +
                "\t    display:inline-block;\n" +
                "\t    cursor:pointer;\n" +
                "\t    color:#ffffff;\n" +
                "\t    font-family:Arial;\n" +
                "\t    font-size:15px;\n" +
                "\t    text-decoration:none;\n" +
                "\t    text-shadow:0px 1px 0px #263666;\n" +
                "\t    width:100%;\n" +
                "\t    height:30px;\n" +
                "    }\n" +
                "    #button:hover {\n" +
                "\t    background:linear-gradient(to bottom, #0688fa 5%, #2dabf9 100%);\n" +
                "\t    background-color:#0688fa;\n" +
                "    }\n" +
                "    #button:active {\n" +
                "\t    position:relative;\n" +
                "\t    top:1px;\n" +
                "    }\n" +
                "\n" +
                "    hr {\n" +
                "        border: 0; height: 1px;\n" +
                "        background-image: -webkit-linear-gradient(left, rgba(0,0,0,0), rgba(0,0,0,0.3), rgba(0,0,0,0));\n" +
                "        width:100%;\n" +
                "    }\n" +
                "    </style>\n" +
                "<body>\n" +
                "<div><img id=\"image\" src=\"cloud_ensemble_white_bg.jpg\" alt=\"Cloud Ensemble\"></div>\n" +
                "<div id=\"box\">\n" +
                "    <div id=\"title\">"+ title +"</div>\n" +
                "    <div id=\"desc\">\n" +
                "        "+ message +"\n" +
                "    </div>\n" +
                "</div>\n" +
                "<hr>\n" +
                "</body>\n" +
                "</html>";
    }
}
