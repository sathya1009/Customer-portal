
<html>
<head>
    <title>Customer Complaint Form</title>
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
    <header-container>
        <header>
          <h1>Hello,  <span id="firstName"></span></h1>
        </header>
        <header>
          <button class="complaint-button" onclick="window.location.href='/viewcomplaint'">View Complaint</button>
          <button class="logout-button" onclick="window.location.href='./login'">Logout</button>
        </header>
      </header-container>
      
    <main>
    <img src="../images/laptop-iphone-coffee-notebook.jpg" alt="">
    <div class="complaint-form-text">
        <h2>Customer Complaint Form</h2>
        <form action="/complaintregister" method="get">
          <button type="submit">Submit Complaint</button>
      </form>
    </div>
    </main>
</body>
</html>