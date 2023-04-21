<html> //login
<head>
   <title>Login</title>
   <link rel="stylesheet" href="css/login.css">
</head>
<body>

   <h1>Login</h1>

   <div id="error-message">
   <h2>${errorMessage}</h2>
</div>

   <form class="form1" method="post" action="/userlogin"onsubmit="setEmail()">
    
      <input type="text" id="email" name="email" placeholder="Enter your email" required>
      <br><br>
      <input type="password" id="password" name="password" placeholder="Enter your password" required>
      <br><br>
      <input type="submit" value="Sign in">

      <div class="container">
        <a href="#">Forgot password?</a>
        <p>Don't have an account? <a href="/register">Register</a></p>
        
     </div>
   </form>

   
   <script>
      function setEmail() {
         // Get the email input field value
         const email = document.getElementById('email').value;
         alert('The email value is ' + email);
         // console.log('The email value is ' + email);
        // Save the email value to localStorage
        window.localStorage.setItem('email', email);
      }
   </script>

</body>
</html>