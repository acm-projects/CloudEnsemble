<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
<link rel="stylesheet" type="text/css" href="/reset_password.css" />
<head>
<body>
                <div><img id="image" src="cloud_ensemble_white_bg.jpg" alt="Cloud Ensemble"></div>
                <div id="box">
                    <div id="title">Reset Password</div>
                    <div id="desc">
                        Please provide a new password for your account.
                        Password must be between 5 and 40 characters.
                    </div>
                    <form action="verify-reset" method="post">
                        <div class="label">Password</div>
                        <input class="field" type="password" name="new_password"/><br><br>
                        <input type="hidden" name="token" value="${code}">
                       <input id="button" type="submit" value="Submit"/>
                   </form>
                </div>
                <hr>
</body>
</html>