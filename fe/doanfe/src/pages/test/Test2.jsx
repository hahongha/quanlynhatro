import React, { useState } from 'react';
import { Button, Box, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useDispatch, useSelector } from 'react-redux';
import { addImageRequest, addListImageRequest } from '../../redux/actions/imageAction';

const ImageManager = ({ initialImages = [], onImagesChange }) => {
  const [imageUrls, setImageUrls] = useState(initialImages);
  const [selectedFiles, setSelectedFiles] = useState([]);
  const dispatch = useDispatch();
  const imageData = useSelector((state) => state.file.all_files);
  const totalRecords = useSelector((state) => state.file.totalRecords);

  const handleFileChange = (event) => {
    const files = Array.from(event.target.files);
    setSelectedFiles(files);
  };

  const handleUpload = async () => {
    if (selectedFiles.length === 0) return;

    dispatch(addImageRequest(selectedFiles));
    // const newImageUrls = [...imageUrls, ...uploadedImages.map((img) => img)];

    // setImageUrls(newImageUrls);
    // console.log(newImageUrls);
    console.log(imageData);

    // onImagesChange(newImageUrls); // Cập nhật danh sách ảnh lên Parent Component
    setSelectedFiles([]);
  };

  const handleDelete = (index) => {
    const updatedImages = imageUrls.filter((_, i) => i !== index);
    setImageUrls(updatedImages);
    onImagesChange(updatedImages);
  };

  return (
    <Box>
      <input type="file" multiple accept="image/*" onChange={handleFileChange} />

      <Box sx={{ display: 'flex', gap: 2, mt: 2 }}>
        {imageUrls.map((src, index) => (
          <Box key={index} sx={{ position: 'relative', width: '100px' }}>
            <img src={src} alt={`Image ${index}`} width="100%" />
            <IconButton
              size="small"
              sx={{ position: 'absolute', top: 0, right: 0, background: 'rgba(0,0,0,0.5)', color: 'white' }}
              onClick={() => handleDelete(index)}
            >
              <DeleteIcon />
            </IconButton>
          </Box>
        ))}
      </Box>

      <Button variant="contained" color="primary" sx={{ mt: 2 }} onClick={handleUpload}>
        Thêm Ảnh
      </Button>
    </Box>
  );
};

export default ImageManager;
