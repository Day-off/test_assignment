import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/filters';

export const getFilters = (page, size) => {
  return axios.get(API_BASE_URL, { params: { page, size } });
};

export const saveFilter = (filterRequest) => {
  return axios.post(`${API_BASE_URL}/save`, filterRequest);
};

export const updateFilter = (filterRequest) => {
  return axios.put(`${API_BASE_URL}/update`, filterRequest);
};

export const deleteFilter = (id) => {
  return axios.delete(`${API_BASE_URL}/delete/${id}`);
};
