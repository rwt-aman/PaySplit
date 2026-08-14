import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json',
  },
});

// Seed the in-memory token from localStorage so it survives page refreshes.
let authToken = localStorage.getItem('token') || null;

// This function gets called from outside (e.g. after login) to set the token
// that every future request should carry.
export const setAuthToken = (token) => {
  authToken = token;
  if (token) {
    localStorage.setItem('token', token);
  } else {
    localStorage.removeItem('token');
  }
};

// Runs before every single request automatically -- attaches the JWT
// so we never have to manually add it in every component.
api.interceptors.request.use((config) => {
  if (authToken) {
    config.headers.Authorization = `Bearer ${authToken}`;
  }
  return config;
});

export default api;