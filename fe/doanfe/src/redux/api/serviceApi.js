import axiosInstance from './axiosConfig';

export const searchService = async (data) => {
  const response = await axiosInstance.post(`/service/search`, data);
  return response;
};
export const getAllService = async () => {
  const response = await axiosInstance.get(`/service/getAll`);
  return response;
};
export const addService = async (data) => {
  const response = await axiosInstance.post(`/service`, data);
  return response;
};

export const deleteService = async (id) => {
  const response = await axiosInstance.delete(`/service/${id}`);
  return response;
};

export const updateService = async (data) => {
  const response = await axiosInstance.put(`/service`, data);
  return response;
};
