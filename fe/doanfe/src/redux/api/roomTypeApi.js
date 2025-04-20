import axiosInstance from './axiosConfig';

export const searchRoomType = async (data) => {
  const response = await axiosInstance.post(`/roomType/search`, data);
  return response;
};
export const getAllRoomType = async () => {
  const response = await axiosInstance.get(`/roomType/getAll`);
  return response;
};
export const addRoomType = async (data) => {
  const response = await axiosInstance.post(`/roomType`, data);
  return response;
};

export const deleteRoomType = async (id) => {
  const response = await axiosInstance.delete(`/roomType/${id}`);
  return response;
};

export const updateRoomType = async (data) => {
  const response = await axiosInstance.put(`/roomType`, data);
  return response;
};
