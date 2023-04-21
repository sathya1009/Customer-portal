<!DOCTYPE html>
<html>

<head>
	<title>View Complaints</title>
	<link rel="stylesheet" href="css/viewcomplaint.css">
</head>

<body>
	<h1>View Complaints</h1>
	<div class="complaints-container" id="complaints-container">
	</div>

	<!--  the edit complaint page container -->
	<div style="position: fixed;" class="edit-complaint-container body-color" id="edit-complaint-container">
		<div class="edit-complaint">
			<textarea id="edit-complaint-textarea"></textarea>
			<button id="update-complaint-btn">Update</button>
			<button id="close-edit-complaint-btn">Close</button>
		</div>
	</div>
	<script>

		// Get the email address of the logged-in user from localStorage
		const email = window.localStorage.getItem('email');

		// function expandComplaint(complaintDiv) {
		// 	// Toggle the display of the complaint text
		// 	const complaintText = complaintDiv.querySelector(".complaint-text");
		// 	complaintText.style.display = complaintText.style.display === "none" ? "block" : "none";
		// }

		function editComplaint(email, complaint, userId) {
			const editComplaintContainer = document.getElementById('edit-complaint-container');
			const editComplaintTextarea = document.getElementById('edit-complaint-textarea');
			const updateComplaintBtn = document.getElementById('update-complaint-btn');
			const closeEditComplaintBtn = document.getElementById('close-edit-complaint-btn');

			function showEditComplaintPage(complaint) {
				// Show the edit complaint page container
				editComplaintContainer.style.display = 'block';
				// Set the complaint text in the text area
				editComplaintTextarea.value = complaint;

				updateComplaintBtn.addEventListener('click', () => {
					const requestBody = {
						email: email,
						complaints: editComplaintTextarea.value

					};
					// Send a PUT request to update the complaint in the server
					fetch('/updateComplaint/' + userId, {
						method: 'PUT',
						headers: {
							'Content-Type': 'application/json'
						},
						body: JSON.stringify(requestBody)
					})
						.then(response => {
							if (!response.ok) {
								throw new Error('Failed to update complaint');
							}

							// Fetch the updated list of complaints from the server
							return fetch('/getusercomplaints')
								.then(response => response.json())
								.then(data => {


									//alert('Complaint updated successfully');
									// Hide the edit complaint page container
									hideEditComplaintPage();
									// Get the complaints container element
									const complaintsContainer = document.getElementById('complaints-container');



									// Clear the complaints container
									complaintsContainer.innerHTML = '';

									// Filter the complaints based on the email address of the logged-in user
									const userComplaints = data.filter(complaint => complaint.email === email);

									// Iterate over the list of user complaints and create a div for each complaint
									userComplaints.forEach(complaint => {
										const complaintDiv = document.createElement('div');
										complaintDiv.classList.add('complaint');

										// Create a h2 element with the email as its text content
										const emailHeading = document.createElement('h2');
										emailHeading.textContent = complaint.email;

										// Create a p element with the complaint as its text content
										const complaintPara = document.createElement('p');
										complaintPara.classList.add('complaint-text');
										complaintPara.textContent = complaint.complaints;

										// Set the user ID as an attribute on the complaint div
										complaintDiv.setAttribute('data-userId', complaint.userId.toString());

										//Get the mail id
										complaintDiv.setAttribute('email', complaint.email);

										// Create a div for the action buttons
										const actionDiv = document.createElement('div');
										actionDiv.classList.add('action-buttons');

										// Create the edit button
										const editButton = document.createElement('button');
										editButton.textContent = "Edit";
										editButton.addEventListener("click", () => editComplaint(complaint.email, complaint.complaints, complaint.userId));
										actionDiv.appendChild(editButton);

										// Create the delete button
										const deleteButton = document.createElement('button');
										deleteButton.textContent = "Delete";
										deleteButton.addEventListener("click", () => deleteComplaint(complaintDiv));
										actionDiv.appendChild(deleteButton);

										// Append the email heading, complaint paragraph, and action buttons div to the complaint div
										complaintDiv.appendChild(emailHeading);
										complaintDiv.appendChild(complaintPara);
										complaintDiv.appendChild(actionDiv);

										// Append the complaint div to the complaints container
										complaintsContainer.appendChild(complaintDiv);

										// Add the userId to the complaint div as a data attribute
										complaintDiv.dataset.userId = complaint.userId;
									});
								})
								.catch(error => console.error('Error fetching complaints:', error));
						})
						.catch(error => {
							console.error('Error updating complaint:', error);
							alert('Failed to update complaint server issue');
						});
				});


				// Handle the close button click event
				closeEditComplaintBtn.addEventListener('click', () => {
					// Hide the edit complaint page container
					hideEditComplaintPage();
				});


			}

			// Show the edit complaint page container with the complaint text
			showEditComplaintPage(complaint);


			function hideEditComplaintPage() {
				// Hide the edit complaint page container
				editComplaintContainer.style.display = 'none';
			}
			closeEditComplaintBtn.addEventListener('click', () => {
				hideEditComplaintPage();
				closeEditComplaintBtn.removeEventListener('click', () => { });
			});
			;
		}

		// function deleteComplaint(complaintDiv) {
		// 	// Remove the complaint div from the container
		// 	complaintDiv.parentNode.removeChild(complaintDiv);
		// 	// TODO: Send a request to delete the complaint from the server
		// }

		function deleteComplaint(complaintDiv) {
			// Remove the complaint div from the container
			complaintDiv.parentNode.removeChild(complaintDiv);
			const id = (complaintDiv.getAttribute('data-userId'));

			// Show an alert with the userId of the complaint owner
			alert(`Complaint deleted. UserId: ${userId}`);

			// TODO: Send a request to delete the complaint from the server





			// fetch('/deleteComplaint/152', {
			//     method: 'DELETE'
			// })
			// .then(response => {
			//     if (response.ok) {
			//         alert('User complaint with ID 152 was deleted.');
			//     } else if (response.status === 404) {
			//         alert('User complaint with ID 152 was not found.');
			//     } else {
			//         throw new Error('Failed to delete user complaint with ID 152');
			//     }
			// })
			// .catch(error => {
			//     console.error('Error deleting user complaint:', error);
			//     alert('Failed to delete user complaint with ID 152');
			// });
			fetch('/deleteComplaint/' + id, {
				method: 'DELETE'
			})
				.then(response => {
					if (!response.ok) {
						throw new Error('Failed to delete complaint');
					}
					alert('Complaint deleted successfully');
				})
				.catch(error => {
					console.error('Error deleting complaint:', error);
					alert('Failed to delete complaint server issue');
				});
		}

		fetch('/getusercomplaints	')
			.then(response => response.json())
			.then(data => {
				// Get the complaints container element
				const complaintsContainer = document.getElementById('complaints-container');


				// Filter the complaints based on the email address of the logged-in user
				const userComplaints = data.filter(complaint => complaint.email === email);

				if (userComplaints.length === 0) {
					//If there are no complaints, replace the h1 header with a message
					const noComplaints = document.createElement('h1');
					noComplaints.textContent = 'No complaints yet';
					complaintsContainer.replaceChildren(noComplaints);


				} else

					// Iterate over the list of user complaints and create a div for each complaint
					userComplaints.forEach(complaint => {
						const complaintDiv = document.createElement('div');
						complaintDiv.classList.add('complaint');

						// Create a h2 element with the email as its text content
						const emailHeading = document.createElement('h2');
						emailHeading.textContent = complaint.email;


						// Create a p element with the complaint as its text content
						const complaintPara = document.createElement('p');
						complaintPara.classList.add('complaint-text');
						complaintPara.textContent = complaint.complaints;


						// Set the user ID as an attribute on the complaint div
						complaintDiv.setAttribute('data-userId', complaint.userId.toString());


						complaintDiv.setAttribute('userId', complaint.userId.toString());

						//Get the mail id
						complaintDiv.setAttribute('email', complaint.email);

						// Create a div for the action buttons
						const actionDiv = document.createElement('div');
						actionDiv.classList.add('action-buttons');

						// Create the edit button
						const editButton = document.createElement('button');
						editButton.textContent = "Edit";
						editButton.addEventListener("click", () => editComplaint(complaint.email, complaint.complaints, complaint.userId, complaintDiv));
						actionDiv.appendChild(editButton);


						// Create the delete button
						const deleteButton = document.createElement('button');
						deleteButton.textContent = "Delete";
						deleteButton.addEventListener("click", () => deleteComplaint(complaintDiv));
						actionDiv.appendChild(deleteButton);


						// Append the email heading, complaint paragraph, and action buttons div to the complaint div
						complaintDiv.appendChild(emailHeading);
						complaintDiv.appendChild(complaintPara);
						complaintDiv.appendChild(actionDiv);
						// Append the complaint div to the complaints container
						complaintsContainer.appendChild(complaintDiv);

						// Add the userId to the complaint div as a data attribute
						complaintDiv.dataset.userId = complaint.userId;
					});
			})
			.catch(error => console.error('Error fetching complaints:', error));
	</script>

</body>

</html>