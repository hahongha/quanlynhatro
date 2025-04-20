import { useState } from 'react';
import ImageUploader from './ImageUploader';

function TestUpload() {
  const [imageUrls, setImageUrls] = useState([]);

  return (
    <div>
      <h2>Upload Image to Cloudinary</h2>
      <ImageUploader onUploadSuccess={(url) => setImageUrls([...imageUrls, url])} />

      <h3>Uploaded Images</h3>
      <div>
        {imageUrls.map((url, index) => (
          <img key={index} src={url} alt={`Uploaded ${index}`} width="100" />
        ))}
      </div>
    </div>
  );
}

export default TestUpload;
