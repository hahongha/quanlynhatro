import axiosInstance from './axiosConfig';

export const searchUser = async (data) => {
  const response = await axiosInstance.post(`/user/search`, data);
  return response;
};
export const getAllUser = async () => {
  const response = await axiosInstance.get(`/user/all`);
  return response;
};
export const addUser = async (data) => {
  const response = await axiosInstance.post(`/user`, data);
  return response;
};

export const deleteUser = async (id) => {
  const response = await axiosInstance.delete(`/user/${id}`);
  return response;
};

export const updateUser = async (data) => {
  const response = await axiosInstance.put(`/user`, data);
  return response;
};
