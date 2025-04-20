import axiosInstance from './axiosConfig';

export const searchAuthority = async (data) => {
  const response = await axiosInstance.post(`/authority/search`, data);
  return response;
};
export const getAllAuthority = async () => {
  const response = await axiosInstance.get(`/authority/getAll`);
  return response;
};
export const addAuthority = async (data) => {
  const response = await axiosInstance.post(`/authority/`, data);
  return response;
};

export const deleteAuthority = async (id) => {
  const response = await axiosInstance.delete(`/authority/${id}`);
  return response;
};

export const updateAuthority = async (data) => {
  const response = await axiosInstance.put(`/authority/`, data);
  return response;
};
