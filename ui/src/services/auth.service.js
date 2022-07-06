import axios from "axios";

const API_URL = "http://localhost:8080/api/";

class AuthService {


  addTodo(title, state, description) {
    return axios.post(API_URL + "save", {
      id: -1,
      title,
      state,
      description,
  });
}


}
export default new AuthService();
