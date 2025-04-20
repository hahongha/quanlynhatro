import React, { useState } from 'react';
import upload from './upload';

const ImageUploader = ({ onUploadSuccess }) => {
  const [selectedFile, setSelectedFile] = useState(null);
  const [previewImage, setPreviewImage] = useState(null);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    setSelectedFile(file);
    setPreviewImage(URL.createObjectURL(file)); // Hiển thị ảnh trước khi upload
  };

  const handleUpload = async () => {
    if (!selectedFile) return;

    const imageUrl = await upload(selectedFile);
    if (imageUrl) {
      onUploadSuccess(imageUrl);
    }

    // Reset sau khi upload
    setSelectedFile(null);
    setPreviewImage(null);
  };

  return (
    <div>
      <input type="file" accept="image/*" onChange={handleFileChange} />
      <button onClick={handleUpload} disabled={!selectedFile}>
        Upload
      </button>

      {/* Hiển thị ảnh trước khi upload */}
      {previewImage && <img src={previewImage} alt="Preview" width="100" />}
    </div>
  );
};

export default ImageUploader;
