<html>
    <head>
        <title>Login to Ojek Si Mang Pred</title>
        <link rel="stylesheet" type="text/css" href="../css/default_style.css">
        <link rel="stylesheet" type="text/css" href="../css/login.css">
    </head>
    <body>
        <%! String s1 = ""; %>
            <% s1  = (String) request.getAttribute("errorMessage");%>
            <% if(s1.equals("UserName atau password ada yang salah")){ %>
            <p  style="color : red">UserName or password wrong</p>
        <% } %>
        <div class="frame">
            <div class="login_header">
                <div class="horizontal_line"></div>
                <h1>LOGIN</h1>
                <div class="horizontal_line"></div>
            </div>
            <p id="error_credential" style="text-align: center;"></p>
            <form name="login" method="POST" action="../IDServices/Login">
                <div class="login_container">
                    <div class="form_name">
                        <div class="login_field">
                            Username
                        </div>
                        <div class="login_field">
                            Password
                        </div>
                    </div>
                    <div class="form_field">
                        <div class="login_form">
                           <input type="text" name="username"><br/>
                        </div>
                        <div class="login_form">
                            <input type="Password" name="password"><br/>
                        </div>
                    </div>
                    <div class="form_button">
                        <a class="no_account" href="sign_up.html">Don't have an account?</a>
                        <input type="submit" class="button green login" value="GO!">
                    </div>
                </div>
            </form>
        </div>
        <script>
            function validate()
            {
                var username = document.login.user_name.value;
                var password = document.login.user_password.value;
                if (username==null || username=="")
                {
                    window.alert("Username can't be blank");
                    return false;
                }
                else if (password==null || password=="")
                {
                    window.alert("Password can't be blank");
                    return false;
                }
            }
        </script>
        
    </body>
</html>
