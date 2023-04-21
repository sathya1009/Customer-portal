<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Complaints</title>
    <link rel="stylesheet" href="css/complaintregister.css">
</head>
<body>
    <div class="card">
        <div class="header">Please enter your complaints</div>
        <div class="content" id="complaint-form">
            <textarea id="complaint" placeholder="Write your complaints here..."></textarea>
            <input type="hidden" id="username" name="username" value="John Doe">
            <input type="hidden" id="email" name="email" value=" ">
            <button class="submit-btn" onclick="submitComplaint()">Submit</button>
        </div>
        <div class="content" id="success-message" style="display:none;">
            <p>Your complaint has been submitted successfully!</p>
            <button class="submit-btn" onclick="resetForm()">Submit another complaint</button>
        </div>
    </div>

    <script>
        function submitComplaint() {
            // Get form data
            const complaint = document.getElementById('complaint').value;
            const username = document.getElementById('username').value;
            const email =  window.localStorage.getItem('email');
            document.getElementById('email').value = email;

                // Check if complaint is empty
         if (complaint.trim() === '') {
        alert('Please enter your complaint.');
        return; // Stop the function from continuing
    }

            // Send POST request to API
            fetch('/uerscomplaint', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    userName: username,
                    email: email,
                    complaints: complaint
                })
            })
            .then(response => response.json())
            .then(data => {
                if (data.RESP_STATUS === 'SUCCESS') {
                    // Show success message and hide complaint form
                    document.getElementById('complaint-form').style.display = 'none';
                    document.getElementById('success-message').style.display = 'block';
                } else {
                   
                    // Stay on complaint page
                   alert('Failed to submit complaint. Please try again.');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('An error occurred. Please try again later.');
            });
        }

        function resetForm() {
            // Clear form fields and show complaint form
            document.getElementById('complaint').value = '';
            document.getElementById('complaint-form').style.display = 'block';
            document.getElementById('success-message').style.display = 'none';
        }
    </script>
</body>
</html>
