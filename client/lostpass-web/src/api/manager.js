import axios from "~/axios"

export function login(username, password) {
    return axios.post(
        "/api/user/login", 
        {
            username,
            password
        }
    )
}
