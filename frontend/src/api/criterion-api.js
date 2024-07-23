import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/criterion';

export const getCriterionTypes = () => {
  return axios.get(`${API_BASE_URL}/type`);
};

export const getCriterionConditionTypes = () => {
  return axios.get(`${API_BASE_URL}/condition-type`);
};
