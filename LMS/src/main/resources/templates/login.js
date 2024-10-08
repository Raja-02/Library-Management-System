document.getElementById("login-btn").addEventListener("click", function(event) {
    event.preventDefault();  // Prevent the form from submitting the default way

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    // Determine the selected role (student, faculty, or admin)
    const role = document.querySelector('input[name="role"]:checked').value;

    let url = '';

    if (role === 'student') {
        url = "http://localhost:8080/auth/login/student";
    } else if (role === 'faculty') {
        url = "http://localhost:8080/auth/login/faculty";
    } else if (role === 'admin') {
        url = "http://localhost:8080/auth/login/admin";
    }

    // Send the request to the correct API endpoint based on the role
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            email: email,
            password: password
        })
    })
    .then(response => response.text())
    .then(result => {
        if (result === "Login successful") {
            alert("Login successful!");

            // Redirect to dashboard based on the role
            if (role === 'student') {
                window.location.href = "studentdashboard.html";
            } else if (role === 'faculty') {
                window.location.href = "facultydashboard.html";
            } else if (role === 'admin') {
                window.location.href = "admindashboard.html";
            }
        } else {
            alert("Invalid credentials. Please try again.");
        }
    })
    .catch(error => console.error("Error:", error));
});
