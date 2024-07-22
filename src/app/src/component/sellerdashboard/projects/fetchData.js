import axios from "axios";

const fetchUsers = async () => {
  let response = await axios.get("https://jsonplaceholder.typicode.com/users");

  return response.data;
};

export default fetchUsers;
