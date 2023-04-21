<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Register</title>
   <link rel="stylesheet" href="css/register.css">
</head>

<body>

   <h1>Register</h1>
   <h2 class="message" id="message"></h2>


   <form class="form1" id="register-form">

      <input type="text" id="firstname" name="firstname" placeholder="Enter your firstname" required>
      <input type="text" id="lastname" name="lastname" placeholder="Enter your lastname" required>
      <br><br>
      <input type="text" id="username" name="username" placeholder="Enter your username" required>
      <input type="password" id="password" name="password" placeholder="Enter your password" required>
      <br><br>
      <input type="date" id="date" name="date" placeholder="Enter your D.0.B" required>
      <input type="text" id="mobile" name="mobile" placeholder="Enter your phonenumber" required>
      <br><br>
      <input type="email" id="email" name="email" placeholder="Enter your email" required>
      <br><br>
      <input type="submit" value="Sign in">

      <div class="container">
         <p>Already have an account? <a href="/login">Login</a></p>
      </div>
   </form>

   <script>
      const form = document.getElementById('register-form');
      function validateForm(formData) {
         const { firstName, lastName, password } = formData;
         if (firstName.length < 3) {
            alert('Firstname should have at least three letters');
            return false;
         }
         if (lastName.length < 3) {
            alert('Lastname should have at least three letters');
            return false;
         }
         if (password.length < 3) {
            alert('Password should have at least three letters');
            return false;
         }
         return true;
      }


      form.addEventListener('submit', async (e) => {
         e.preventDefault(); // prevent form submission

         // get form data
         const formData = {
            firstName: document.getElementById('firstname').value,
            lastName: document.getElementById('lastname').value,
            password: document.getElementById('password').value,
            dob: document.getElementById('date').value,
            userName: document.getElementById('username').value,
            mobileNo: document.getElementById('mobile').value,
            email: document.getElementById('email').value
         };

         if (!validateForm(formData)) {
            return false; // stop form submission if validation fails
         } else




            try {
               const response = await fetch('/addNewUser', {
                  method: 'POST',
                  headers: {
                     'Content-Type': 'application/json'
                  },
                  body: JSON.stringify(formData)
               });

               const data = await response.json();



               if (response.ok && data.RESP_STATUS === 'SUCCESS') {
                  // user registered successfully, redirect to login page
                  window.location.href = './login';

               } else if (response.status === 400 && data.message === 'Mobile Number is already exist!') {
                  // mobile number already exists, show error message
                  alert(data.message);
                  // const message = document.getElementById('message');
                  // message.innerHTML = 'Mobile number already exists';

               }


               else {
                  // registration failed, show error message
                  alert('Registration failed in server. ' + data.RESP_STATUS);
               }


            } catch (error) {
               console.error('Registration failed do not hit server. ' + error);
            }
      });
   </script>

</body>

</html>