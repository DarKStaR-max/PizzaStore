import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8081',
});

export const menuApi = axios.create({
  baseURL: 'http://localhost:8082',
});

export const adminApi = axios.create({
  baseURL: 'http://localhost:8083',
});

const retryAxios = async (axiosInstance, maxRetries = 3, delay = 1000) => {
  for (let i = 0; i < maxRetries; i++) {
    try {
      return await axiosInstance;
    } catch (error) {
      if (i === maxRetries - 1) throw error;
      await new Promise(resolve => setTimeout(resolve, delay));
    }
  }
};

export const login = (email, password) => api.post('/api/auth/login', { email, password });
export const register = (name, email, password) => api.post('/api/auth/register', { name, email, password });
export const getCustomer = (id) => api.get(`/api/customers/${id}`);
export const getMenuItems = () => retryAxios(menuApi.get('/api/menu-items'));

// Admin API calls
export const adminLogin = (username, password) => adminApi.post('/api/admin/login', { username, password });
export const createMenuItem = (item) => adminApi.post('/api/menu-items', item);
export const updateMenuItem = (id, item) => adminApi.put(`/api/menu-items/${id}`, item);
export const deleteMenuItem = (id) => adminApi.delete(`/api/menu-items/${id}`);

export default api;