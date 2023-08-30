import axios from "axios"

const service = axios.create({
    baseURL: "/api"
})

// 请求拦截器
service.interceptors.request.use(function (config) {
    // 处理响应数据
    return config;
}, {
    function(error) {
        // 处理错误
        return Promise.reject(error);
    }
})


// 响应拦截器
service.interceptors.response.use(function (response) {
    // 处理响应数据
    return response;
}, {
    function(error) {
        // 处理错误
        return Promise.reject(error);
    }
})




export default service