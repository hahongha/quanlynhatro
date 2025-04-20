import axiosInstance from './axiosConfig';

export const searchRenter = async (data) => {
  const response = await axiosInstance.post(`/renter/search`, data);
  return response;
};
export const getAllRenter = async () => {
  const response = await axiosInstance.get(`/renter/getAll`);
  return response;
};
export const addRenter = async (data) => {
  const response = await axiosInstance.post(`/renter`, data);
  return response;
};

export const deleteRenter = async (id) => {
  const response = await axiosInstance.delete(`/renter/${id}`);
  return response;
};

export const updateRenter = async (data) => {
  const response = await axiosInstance.put(`/renter`, data);
  return response;
};
