import React, { useState } from "react";
import styles from './Authentication.module.scss';
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("buyer");

  const handleRole = (event) => {
    setRole(event.target.value);
  }

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .post('http://localhost:8080/api/auth/signin', {
        username: email,
        password: password,
        role: role
      })
      .then((res) => {
        console.log(res.data);
        const accessToken = res.data.token;
        localStorage.setItem('access_token', accessToken);
        localStorage.setItem('user_id', res.data.id);
        localStorage.setItem('user_email', res.data.email);
        localStorage.setItem('user_role', role);
        if (role === 'buyer')
          navigate('/buyerdashboard');
        else
          navigate('/sellerdashboard');
      })
      .catch((error) => {
        console.log(error);
      })
    }

  return (
    <div id="login-tab-content" class="tabcontent">
      <form class="login-form" action="" method="post" onSubmit={handleSubmit}>
        <input type="text" className={styles.input} id="user_login" autocomplete="off" placeholder="Email" value={email} onChange={handleEmailChange}/>
        <input type="password" className={styles.input} id="user_pass" autocomplete="off" placeholder="Password" value={password} onChange={handlePasswordChange}/>
        <input type="radio" id="is_buyer" checked={role === "buyer"} value="buyer" onChange={handleRole} />
        <label> Buyer </label>
        <input type="radio" id="is_seller" checked={role === "seller"} value="seller" onChange={handleRole} />
        <label>Seller</label>
        <input type="submit" className={styles.button} value="Login" onClick={handleSubmit} />
      </form>
    </div>
  );
}

export default Login;