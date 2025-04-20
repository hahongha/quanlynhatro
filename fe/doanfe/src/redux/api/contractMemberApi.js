import axiosInstance from './axiosConfig';

export const searchContractMember = async (data) => {
  const response = await axiosInstance.post(`/contractMember/search`, data);
  return response;
};
export const getAllContractMember = async () => {
  const response = await axiosInstance.get(`/contractMember/getAll`);
  return response;
};
export const addContractMember = async (data) => {
  const response = await axiosInstance.post(`/contractMember`, data);
  return response;
};

export const deleteContractMember = async (id) => {
  const response = await axiosInstance.delete(`/contractMember/${id}`);
  return response;
};

export const updateContractMember = async (data) => {
  const response = await axiosInstance.put(`/contractMember`, data);
  return response;
};
