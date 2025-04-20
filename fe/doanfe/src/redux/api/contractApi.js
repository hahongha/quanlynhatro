import axiosInstance from './axiosConfig';

export const searchContract = async (data) => {
  const response = await axiosInstance.post(`/contract/search`, data);
  return response;
};
export const getAllContract = async () => {
  const response = await axiosInstance.get(`/contract/getAll`);
  return response;
};
export const addContract = async (data) => {
  const response = await axiosInstance.post(`/contract`, data);
  return response;
};

export const deleteContract = async (id) => {
  const response = await axiosInstance.delete(`/contract/${id}`);
  return response;
};

export const updateContract = async (data) => {
  const response = await axiosInstance.put(`/contract`, data);
  return response;
};
