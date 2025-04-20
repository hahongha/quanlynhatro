// import React, { useState } from 'react';
// import uploadToCloudinary from './uploadToCloudinary';

// const ImageUploader = ({ onUploadSuccess }) => {
//   const [selectedFile, setSelectedFile] = useState(null);
//   const [previewImage, setPreviewImage] = useState(null);

//   const handleFileChange = (event) => {
//     const file = event.target.files[0];
//     setSelectedFile(file);
//     setPreviewImage(URL.createObjectURL(file)); // Hiển thị ảnh trước khi upload
//   };

//   const handleUpload = async () => {
//     if (!selectedFile) return;

//     const imageUrl = await uploadToCloudinary(selectedFile);
//     if (imageUrl) {
//       onUploadSuccess(imageUrl);
//     }

//     // Reset sau khi upload
//     setSelectedFile(null);
//     setPreviewImage(null);
//   };

//   return (
//     <div>
//       <input type="file" accept="image/*" onChange={handleFileChange} />
//       <button onClick={handleUpload} disabled={!selectedFile}>
//         Upload
//       </button>

//       {/* Hiển thị ảnh trước khi upload */}
//       {previewImage && <img src={previewImage} alt="Preview" width="100" />}
//     </div>
//   );
// };

// export default ImageUploader;
// import axios from "axios";

// const uploadToCloudinary = async (file) => {
//   const formData = new FormData();
//   formData.append("file", file);
//   formData.append("upload_preset", "your_upload_preset"); // Thay bằng upload_preset của bạn
//   formData.append("cloud_name", "your_cloud_name"); // Thay bằng cloud_name của bạn

//   try {
//     const response = await axios.post(
//       `https://api.cloudinary.com/v1_1/your_cloud_name/image/upload`,
//       formData
//     );

//     return response.data.secure_url; // Trả về URL ảnh
//   } catch (error) {
//     console.error("Error uploading image:", error);
//     return null;
//   }
// };

// export default uploadToCloudinary;

// import React, { useState } from 'react';
// import ImageUploader from './ImageUploader';

// const App = () => {
//   const [imageUrls, setImageUrls] = useState([]);

//   return (
//     <div>
//       <h2>Upload Image to Cloudinary</h2>
//       <ImageUploader onUploadSuccess={(url) => setImageUrls([...imageUrls, url])} />

//       <h3>Uploaded Images</h3>
//       <div>
//         {imageUrls.map((url, index) => (
//           <img key={index} src={url} alt={`Uploaded ${index}`} width="100" />
//         ))}
//       </div>
//     </div>
//   );
// };

// export default App;
