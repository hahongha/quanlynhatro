import axios from 'axios';

const upload = async (file) => {
  const formData = new FormData();
  formData.append('file', file);
  formData.append('upload_preset', 'uploadPublic'); // Thay bằng upload_preset của bạn
  formData.append('cloud_name', 'dlyprrqnn'); // Thay bằng cloud_name của bạn

  try {
    const response = await axios.post(`https://api.cloudinary.com/v1_1/dlyprrqnn/image/upload`, formData);

    return response.data.secure_url; // Trả về URL ảnh
  } catch (error) {
    console.error('Error uploading image:', error);
    return null;
  }
};

export default upload;
