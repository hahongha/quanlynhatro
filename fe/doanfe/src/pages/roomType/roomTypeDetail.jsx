import { CloseOutlined } from '@ant-design/icons';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import CloseIcon from '@mui/icons-material/Close';
import { Button, Chip, Dialog, DialogActions, DialogContent, DialogTitle, IconButton, Stack, TextField, Typography } from '@mui/material';
import { useState } from 'react';
function RoomDialog({ open, onClose, roomData, onSave }) {
  const initialRoom = {
    id: roomData.id !== null ? roomData.id : null,
    name: roomData.name !== null ? roomData.name : '',
    size: roomData.size !== null ? roomData.size : 0,
    furniture: roomData.furniture !== null ? roomData.furniture : '',
    cost: roomData.cost !== null ? roomData.cost : 0
  };
  const [room, setRoom] = useState(initialRoom);
  const [newFurniture, setNewFurniture] = useState('');
  const furnitureList = room.furniture ? room.furniture.split(', ') : [];
  const handleChange = (e) => {
    setRoom({ ...room, [e.target.name]: e.target.value });
  };

  const handleAddFurniture = () => {
    if (newFurniture.trim() && !furnitureList.includes(newFurniture.trim())) {
      const updatedFurniture = [...furnitureList, newFurniture.trim()].join(', ');
      setRoom({ ...room, furniture: updatedFurniture });
      setNewFurniture('');
    }
  };

  const handleDeleteFurniture = (item) => {
    const updatedFurniture = furnitureList.filter((f) => f !== item).join(', ');
    setRoom({ ...room, furniture: updatedFurniture });
  };

  return (
    <Dialog
      open={open}
      onClose={(event, reason) => {
        if (reason !== 'backdropClick') {
          onClose();
        }
      }}
      fullWidth
    >
      <DialogTitle sx={{ display: 'flex', justifyContent: 'space-between' }}>
        <Typography variant="h3">Thông tin loại phòng</Typography>
        <IconButton onClick={onClose}>
          <CloseOutlined />
        </IconButton>
      </DialogTitle>
      <DialogContent>
        <Stack spacing={2} sx={{ mt: 1 }}>
          <TextField label="Tên phòng" name="name" value={room.name} onChange={handleChange} fullWidth />
          <TextField label="Diện tích (m²)" name="size" type="number" value={room.size} onChange={handleChange} fullWidth />
          <TextField label="Giá phòng (VNĐ)" name="cost" type="number" value={room.cost} onChange={handleChange} fullWidth />

          {/* Hiển thị nội thất dưới dạng Chip */}
          <Stack direction="row" spacing={1} flexWrap="wrap">
            {furnitureList.map((item, index) => (
              <Chip key={index} label={item} onDelete={() => handleDeleteFurniture(item)} color="primary" deleteIcon={<CloseIcon />} />
            ))}
          </Stack>

          {/* Thêm nội thất từ TextField */}
          <Stack direction="row" spacing={1}>
            <TextField
              label="Thêm nội thất"
              value={newFurniture}
              onChange={(e) => setNewFurniture(e.target.value)}
              fullWidth
              onKeyDown={(e) => e.key === 'Enter' && handleAddFurniture()}
            />
            <IconButton color="primary" onClick={handleAddFurniture}>
              <AddCircleIcon />
            </IconButton>
          </Stack>
        </Stack>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} variant="outlined">
          Hủy
        </Button>
        <Button
          onClick={() => {
            onSave(room);
          }}
          variant="contained"
          color="primary"
        >
          Lưu
        </Button>
      </DialogActions>
    </Dialog>
  );
}
export default RoomDialog;
