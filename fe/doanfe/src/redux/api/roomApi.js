import axiosInstance from './axiosConfig';

export const searchRoom = async (data) => {
  const response = await axiosInstance.post(`/room/search`, data);
  return response;
};
export const getAllRoom = async () => {
  const response = await axiosInstance.get(`/room/getAll`);
  return response;
};
export const addRoom = async (data) => {
  const response = await axiosInstance.post(`/room`, data);
  return response;
};

export const deleteRoom = async (id) => {
  const response = await axiosInstance.delete(`/room/${id}`);
  return response;
};

export const updateRoom = async (data) => {
  const response = await axiosInstance.put(`/room`, data);
  return response;
};
