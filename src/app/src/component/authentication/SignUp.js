import React, { useState } from "react";
import styles from './Authentication.module.scss';
import axios from "axios";
import { useNavigate } from "react-router-dom";

function SignUp() {
  const navigate = useNavigate();
  const [firstname, setFirstName] = useState("");
  const [lastname, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [customertype, setCustomerType] = useState("buyer");
  // const [passwordConfirm, setPasswordConfirm] = useState("");

  // const [formData, setFormData] = useState(
  //   {
  //     firstname: "",
  //     password: "",
  //     // passwordConfirm: "",
  //     email: ""
  //   }
  // );

  const handleCustomerType = (event) => {
    setCustomerType(event.target.value);
  }

  const handleFirstNameChange = (event) => {
    setFirstName(event.target.value);
  };

  const handleLastNameChange = (event) => {
    setLastName(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  // const handlePasswordConfirmChange = (event) => {
  //   setPasswordConfirm(event.target.value);
  // }

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const isNotEmpty = (value) => {
    if (value === undefined && value === "")
      return false;
    return true;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // if (isNotEmpty(email) && isNotEmpty(firstname) && isNotEmpty(lastname))

    // if (password !== passwordConfirm) {
    //   alert("Passwords do not match");
    //   return;
    // }

    axios
      .post('http://localhost:8080/api/auth/signup', {
        username: email,
        password: password,
        role: customertype,
        firstName: firstname,
        lastName: lastname
      })
      .then((res) => {
        console.log(res.data);

        axios
        .post('http://localhost:8080/api/auth/signin', {
          username: email,
          password: password,
          role: customertype
        })
        .then((res) => {
          console.log(res.data);
          const accessToken = res.data.token;
          localStorage.setItem('access_token', accessToken);
          localStorage.setItem('user_id', res.data.id);
          localStorage.setItem('user_email', res.data.email);
          localStorage.setItem('user_role', customertype);
          if (customertype === 'buyer')
            navigate('/buyerdashboard');
          else
            navigate('/sellerdashboard');
        })
        .catch((error) => {
          console.log(error);
        })
      })
      .catch((error) => {
        console.log(error);
      })



    console.log(
      `Submitted firstname: ${firstname}, lastname: ${lastname}, customerType: ${customertype}, password: ${password}, email: ${email}`
    );
  };

  return (
    <div id="signup-tab-content" class="tabcontent" style={{ display: "block" }}>
      <form class="signup-form" action="" method="post" onSubmit={handleSubmit} >
        <input type="email" className={styles.input} id="user_email" autocomplete="off" placeholder="Email" value={email} onChange={handleEmailChange} />
        <input type="text" className={styles.input} id="first_name" autocomplete="off" placeholder="First Name" value={firstname} onChange={handleFirstNameChange} />
        <input type="text" className={styles.input} id="last_name" autocomplete="off" placeholder="Last Name" value={lastname} onChange={handleLastNameChange} />
        <input type="password" className={styles.input} id="user_pass" autocomplete="off" placeholder="Password" value={password} onChange={handlePasswordChange}/>
        <input type="radio" id="is_buyer" checked={customertype === "buyer"} value="buyer" onChange={handleCustomerType} />
        <label> Buyer </label>
        <input type="radio" id="is_seller" checked={customertype === "seller"} value="seller" onChange={handleCustomerType} />
        <label>Seller</label>
        {/* <input type="password" class="input" id="user_pass_confirm" autocomplete="off" placeholder="Confirm Password" value={passwordConfirm} onChange={handlePasswordConfirmChange} /> */}
        <input type="submit" className={styles.button} value="Sign Up" onClick={handleSubmit}/>
      </form>
    </div>
  );
}

export default SignUp;