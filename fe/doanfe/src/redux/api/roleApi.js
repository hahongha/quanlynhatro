import axiosInstance from './axiosConfig';

export const searchRole = async (data) => {
  const response = await axiosInstance.post(`/role/search`, data);
  return response;
};
export const getAllRole = async () => {
  const response = await axiosInstance.get(`/role/getAll`);
  return response;
};
export const addRole = async (data) => {
  const response = await axiosInstance.post(`/role/`, data);
  return response;
};

export const deleteRole = async (id) => {
  const response = await axiosInstance.delete(`/role/${id}`);
  return response;
};

export const updateRole = async (data) => {
  const response = await axiosInstance.put(`/role/`, data);
  return response;
};
