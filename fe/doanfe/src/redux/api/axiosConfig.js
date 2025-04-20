// axiosConfig.js
import axios from 'axios';

const API_PATH = import.meta.env.VITE_REACT_APP_URL_BACKEND;

const axiosInstance = axios.create({
  baseURL: API_PATH,
  timeout: 10000,
});

axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('access_token');

    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
