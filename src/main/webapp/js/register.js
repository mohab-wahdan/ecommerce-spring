document.addEventListener("DOMContentLoaded", function() {
    const governorates = [
        "Cairo",
        "Alexandria",
        "Giza",
        "Port Said",
        "Suez",
        "Mansoura",
        "Tanta",
        "Aswan",
        "Asyut",
        "Ismailia",
        "Fayoum",
        "Minya",
        "Daqahliya",
        "Kafr El Sheikh",
        "Beni Suef",
        "Zagazig",
        "Qena",
        "Luxor",
        "Matruh",
        "New Valley (Wadi El Nile)",
        "Red Sea",
        "North Sinai",
        "South Sinai",
        "Sohag",
        "Qalyubia",
        "Sharqia"
    ];
    // Populate the city dropdown
    const citySelect = document.getElementById("city");
    governorates.forEach(city => {
        const option = document.createElement("option");
        option.value = city;
        option.text = city;
        citySelect.add(option);
    });
});


///////////////////////////////Handling Credit Limit Validation//////////////////////////////////////////////////////////
function CheckCreditLimit() {
    var creditValue = document.getElementById("creditLimit").value;
    if (creditValue < 0) {
        document.getElementById("crediterror").innerText = "Credit limit must be more than 0";
    } else if (creditValue > 1000000) {
        document.getElementById("crediterror").innerText = "Credit limit must not exceed 1,000,000";
    }else if (creditValue[0] === '0') {
        document.getElementById("crediterror").innerText = "Credit limit must not start with 0";
    } else {
        document.getElementById("crediterror").innerText = "";
    }
}

// ////////////////////////////Handling Phone Number Validation//////////////////////////////////////////////////////////
function CheckPhoneNumber() {
    var phoneNumber = document.getElementById("phoneNumber").value;
    const phoneRegex = /^01[0125][0-9]{8}$/;
    if (!phoneRegex.test(phoneNumber)) {
        document.getElementById("phoneerror").innerText = "Phone number is not valid";
        return;
    }else {
        // Create XMLHttpRequest for phone number validation
        var phoneNumReq = new XMLHttpRequest();
        phoneNumReq.onreadystatechange = function () {
            if (phoneNumReq.readyState === 4 && phoneNumReq.status === 200) {
                var response = phoneNumReq.responseText;

                // Check response to determine if phone number exists
                if (response === "exists") {
                    document.getElementById("phoneerror").innerText = "Phone number already exists";
                } else {
                    document.getElementById("phoneerror").innerText = "";
                }
            }
        };
        phoneNumReq.open("GET", "/customers/phonenumber/" + encodeURIComponent(phoneNumber), true);
        phoneNumReq.send();
    }


}

// ////////////////////////////Handling Email Validation//////////////////////////////////////////////////////////
function CheckEmail() {
    var email = document.getElementById("email").value;

    // Validate email format
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
        document.getElementById("emailerror").innerText = "Email is not valid";
        return;
    }else{
        // Create XMLHttpRequest for email validation
        var emailReq = new XMLHttpRequest();
        emailReq.onreadystatechange = function () {
            if (emailReq.readyState === 4 && emailReq.status === 200) {
                var response = emailReq.responseText;

                // Check response to determine if email exists
                if (response === "exists") {
                    document.getElementById("emailerror").innerText = "Email already exists";
                } else {
                    document.getElementById("emailerror").innerText = "";
                }
            }
        };
        // Send GET request to check email in the database
        emailReq.open("GET", "/customers/email/" + encodeURIComponent(email), true);
        emailReq.send();
    }


}


// ////////////////////////////Handling Username Validation//////////////////////////////////////////////////////////
function CheckUserName() {
    var userName = document.getElementById("userName").value;
    var usernameReq = new XMLHttpRequest();
    usernameReq.onreadystatechange = function() {
        if (usernameReq.readyState === 4 && usernameReq.status === 200) {
            var response = usernameReq.responseText;

            // Check response to determine if username exists
            if (response === "exists") {
                document.getElementById("usernameerror").innerText = "Username already exists";
            } else {
                document.getElementById("usernameerror").innerText = "";
            }
        }
    };
    usernameReq.open("GET", "/customers/username/" + encodeURIComponent(userName), true);
    usernameReq.send();



}



// ////////////////////////////Check Form Validation//////////////////////////////////////////////////////////
function checkCondition() {
    var registerBtn = document.getElementById("registerBtn");

    if (document.getElementById('usernameerror').textContent !=="") {
        registerBtn.disabled = true; // Enable the button if the condition is met
        return false;
    } else if(document.getElementById('crediterror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
        return false;
    }
    else if(document.getElementById('emailerror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
        return false;
    }
    else if(document.getElementById('phoneerror').textContent !==""){
        registerBtn.disabled = true; // Disable the button if the condition is not met
        return false;
    }else{
        registerBtn.disabled = false;
        return true;
    }
}
///////////////////////////////////////////////////////////////////////////////////////
let currentStep = 1;

function showStep(step) {
    document.querySelectorAll('.form-step').forEach((element) => {
        element.style.display = 'none';
    });
    document.getElementById('step-' + step).style.display = 'block';
}
const today = new Date().toISOString().split('T')[0];
document.getElementById('dateOfBirth').setAttribute('max', today);
function nextStep() {
    if (validateCurrentStep()) {
        currentStep++;
        if (currentStep > 4) currentStep = 4;
        showStep(currentStep);
    }
}

function prevStep() {
    currentStep--;
    if (currentStep < 1) currentStep = 1;
    showStep(currentStep);
}

function validateCurrentStep() {
    let valid = true;
    const stepElement = document.getElementById('step-' + currentStep);

    if (currentStep === 1) {
        const password = document.getElementById('password');
        if (password.value.length < 8) {
            document.getElementById('password').setCustomValidity("Password must be at least 8 characters");
            valid = false;
            password.reportValidity();
        } else {
            document.getElementById('password').setCustomValidity(""); // Reset the message if valid
        }
    }
    stepElement.querySelectorAll('input[required]').forEach((input) => {
        if (!input.checkValidity()) {
            valid = false;
            input.reportValidity();
        }
    });

    if (document.getElementById('usernameerror').textContent !== "" ||
        document.getElementById('crediterror').textContent !== "" ||
        document.getElementById('emailerror').textContent !== "" ||
        document.getElementById('phoneerror').textContent !== "") {
        valid = false;
    }

    return valid;
}

// Initialize
showStep(currentStep);
