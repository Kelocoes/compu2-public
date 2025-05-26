import axios from 'axios';

const apiUrl = import.meta.env.VITE_API_URL || 'http://localhost:8080';
const instance = axios.create({
    baseURL: apiUrl,
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
    }
});

instance.interceptors.request.use(
    (config) => {
        console.log('Request:', config);
        const token = localStorage.getItem('token') || 'defaultToken';
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export { instance as axiosInstance };
