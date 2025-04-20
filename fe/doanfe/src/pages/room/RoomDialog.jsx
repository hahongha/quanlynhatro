import {
  Card,
  CardMedia,
  Typography,
  Box,
  Dialog,
  DialogTitle,
  DialogContent,
  TextField,
  Button,
  Select,
  MenuItem,
  IconButton,
  Grid2
} from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { useState } from 'react';
import { CloseOutlined } from '@ant-design/icons';
function RoomDialog({ open, handleClose, room, handleSave }) {
  const initRoom = {
    id: room.id ? room.id : '',
    roomNumber: room.roomNumber ? room.roomNumber : '',
    status: room.status ? room.status : 'AVAILABLE',
    isActive: room.isActive ? room.isActive : false,
    description: room.description ? room.description : '',
    number: room.number ? room.number : 0,
    electricIndex: room.electricIndex ? room.electricIndex : 0,
    waterIndex: room.waterIndex ? room.waterIndex : 0,
    idRenter: room.idRenter ? room.idRenter : '',
    room_Type: {
      id: room.room_Type.id ? room.room_Type.id : 0
    },
    images: [room.images ? room.images : '']
  };

  const [selectedRoom, setSelectedRoom] = useState(initRoom);
  const handleAddImage = () => {
    setSelectedRoom((prev) => ({ ...prev, images: [...prev.images, ''] }));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSelectedRoom((prev) => ({ ...prev, [name]: value }));
  };
  const handleImageChange = (e, index) => {
    const newImages = [...selectedRoom.images];
    newImages[index] = e.target.value;
    setSelectedRoom((prev) => ({ ...prev, images: newImages }));
  };
  const handleRemoveImage = (index) => {
    const newImages = selectedRoom.images.filter((_, i) => i !== index);
    setSelectedRoom((prev) => ({ ...prev, images: newImages }));
  };

  const handleUpload = (url) => {
    setRoom({ ...room, imageUrl: url });
  };

  const statusOptions = [
    { value: 'AVAILABLE', label: 'Trống', color: 'success' }, // Xanh lá
    { value: 'OCCUPIED', label: 'Có người ở', color: 'primary' }, // Xanh dương
    { value: 'MAINTENANCE', label: 'Đang sửa chữa', color: 'warning' }, // Cam
    { value: 'CLEANING', label: 'Đang vệ sinh', color: 'info' }, // Xanh nhạt
    { value: 'RESERVED', label: 'Đặt trước', color: 'secondary' }, // Xám
    { value: 'INACTIVE', label: 'Tạm ngừng cho thuê', color: 'error' }, // Đỏ
    { value: 'PENDING', label: 'Chờ thanh lý hợp đồng', color: 'warning' }, // Cam
    { value: 'INSPECTION', label: 'Đang kiểm tra kỹ thuật', color: 'info' } // Xanh nhạt
  ];

  return (
    <Dialog
      open={open}
      onClose={(event, reason) => {
        if (reason !== 'backdropClick') {
          handleClose();
        }
      }}
      fullWidth
    >
      <DialogTitle sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography variant="h3">Thông tin phòng</Typography>
        <IconButton onClick={handleClose}>
          <CloseOutlined />
        </IconButton>
      </DialogTitle>
      <DialogContent>
        <TextField margin="dense" label="Số phòng" name="roomNumber" fullWidth value={selectedRoom.roomNumber} onChange={handleChange} />
        <TextField margin="dense" label="Mô tả" name="description" fullWidth value={selectedRoom.description} onChange={handleChange} />
        <Select label="Trạng thái" fullWidth value={selectedRoom.status} onChange={handleChange} margin="dense">
          {statusOptions.map((option) => (
            <MenuItem key={option.value} value={option.value}>
              {option.label}
            </MenuItem>
          ))}
        </Select>
        <TextField
          margin="dense"
          label="Chỉ số điện"
          name="electricIndex"
          fullWidth
          type="number"
          value={selectedRoom.electricIndex}
          onChange={handleChange}
        />
        <TextField
          margin="dense"
          label="Chỉ số nước"
          name="waterIndex"
          fullWidth
          type="number"
          value={selectedRoom.waterIndex}
          onChange={handleChange}
        />
        <Box mt={2} spacing={8} sx={{ display: 'flex', justifyContent: 'flex-start' }}>
          {selectedRoom.images.map((img, index) => (
            <Box key={index} display="flex" alignItems="center" mt={1} padding={2}>
              <Grid2 size={4} key={index}>
                <Card>
                  <CardMedia component="img" height="100" image={img} alt={`Ảnh ${index + 1}`} />
                </Card>
                <TextField fullWidth value={img} onChange={(e) => handleImageChange(e, index)} label={`Ảnh ${index + 1}`} />
                <IconButton onClick={() => handleRemoveImage(index)} color="error">
                  <DeleteIcon />
                </IconButton>
              </Grid2>
            </Box>
          ))}
        </Box>
        <Button onClick={handleAddImage} variant="contained" sx={{ mt: 2 }}>
          Thêm ảnh
        </Button>
        <Box mt={2} display="flex" justifyContent="space-between">
          <Button onClick={handleClose} color="secondary">
            Hủy
          </Button>
          <Button
            onClick={() => {
              handleSave(selectedRoom);
            }}
            color="primary"
            variant="contained"
          >
            Lưu
          </Button>
        </Box>
      </DialogContent>
    </Dialog>
  );
}

export default RoomDialog;
