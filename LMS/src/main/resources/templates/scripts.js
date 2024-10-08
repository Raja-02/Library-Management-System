document.getElementById("signup-btn").addEventListener("click", function(event) {
    event.preventDefault();  // Prevent the form from submitting the default way

    const fullName = document.getElementById("full-name").value;
    const email = document.getElementById("signup-email").value;
    const password = document.getElementById("signup-password").value;

    // Determine the selected role (student, faculty, or admin)
    const role = document.querySelector('input[name="role"]:checked').value;

    let url = '';
    let body = {
        fullName: fullName,
        email: email,
        password: password
    };

    if (role === 'student') {
        url = "http://localhost:8080/auth/signup/student";
        const rollNumber = document.getElementById("roll-number").value;
        body.rollNumber = rollNumber;  // Include roll number only for students
    } else if (role === 'faculty') {
        url = "http://localhost:8080/auth/signup/faculty";
        const branch = document.getElementById("branch").value;
        body.branch = branch;  // Include branch only for faculty
    } else if (role === 'admin') {
        url = "http://localhost:8080/auth/signup/admin";
        // No additional fields for admin, only fullName, email, and password
    }

    // Send the request to the correct API endpoint based on the role
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(body)
    })
    .then(response => response.text())
    .then(result => {
        if (result.includes("successfully")) {
            alert("Registration successful!");
            // Redirect to login or dashboard based on the role
            if (role === 'student') {
                window.location.href = "login.html";
            } else if (role === 'faculty') {
                window.location.href = "login.html";
            }
        } else {
            alert(result);
        }
    })
    .catch(error => console.error("Error:", error));
});
