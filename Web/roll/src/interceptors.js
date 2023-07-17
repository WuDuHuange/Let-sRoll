import axios from "axios";
import router from "./router";
//创建axios实例

const api = axios.create({
    baseURL: "http://localhost:8090",
    timeout: 10000,
})
//请求拦截器
api.interceptors.request.use(
    config => {
        //在发送请求之前设置token
        const token = localStorage.getItem("token");
        if (token) {
            config.headers.token = token;
        }
        return config;
    }
    
)
//响应拦截器，如果返回code是401，清除token并跳转到登录页
api.interceptors.response.use(
    response => {
        if (response.data.code === 401) {
            localStorage.removeItem("token");
            router.replace({
                path: "/LoginPage",
                query: {redirect: router.currentRoute.fullPath}
            })
        }
        return response;
    }
)
export default api;