import React, { useState } from 'react';
import {
  Card,
  CardContent,
  Typography,
  Avatar,
  Grid2,
  Button,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Chip,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Divider,
  Box,
  CardMedia
} from '@mui/material';
import Slider from 'react-slick';
import ImageSlider from '../../components/ImageSlider';

const statusLabels = {
  AVAILABLE: 'Phòng trống',
  OCCUPIED: 'Đã có người thuê',
  RESERVED: 'Đã đặt trước',
  UNDER_MAINTENANCE: 'Đang bảo trì',
  OUT_OF_SERVICE: 'Ngừng hoạt động'
};

const statusColors = {
  AVAILABLE: 'green',
  OCCUPIED: 'red',
  RESERVED: 'orange',
  UNDER_MAINTENANCE: 'blue',
  OUT_OF_SERVICE: 'gray'
};

function UserRoom() {
  const room = {
    id: 1,
    roomNumber: 'A101',
    status: 'ACTIVE',
    isActive: true,
    description: 'Phòng rộng rãi, có ban công',
    number: 2,
    electricIndex: 1200,
    waterIndex: 450,
    idRenter: 'renter_01',
    nameRender: 'Nguyễn Văn A',
    room_Type: {
      id: 1,
      name: 'Phòng đơn',
      size: 25,
      furniture: 'Giường, tủ, bàn làm việc, điều hòa',
      description: 'Phòng đầy đủ tiện nghi',
      cost: 5000000
    },
    images: [
      'https://i.imgur.com/01iECtb.jpeg0',
      'https://i.imgur.com/01iECtb.jpeg',
      'https://i.imgur.com/01iECtb.jpeg',
      'https://i.imgur.com/PQ6EFZG.jpeg'
    ]
  };

  const roommates = [
    {
      id: 2,
      fullName: 'Trần Thị B',
      gender: 'FEMALE',
      status: 'ACTIVE',
      phone: '0912345678',
      dob: '1997-08-22',
      placeOfOrigin: 'Hải Phòng',
      address: '56 Lê Hồng Phong, Hải Phòng',
      familyPhone: '0978123456',
      isRegister: true,
      identification: '987654321',
      rentalRelationship: 'Bạn cùng phòng',
      imageUrl: 'https://example.com/avatar1.jpg'
    },
    {
      id: 3,
      fullName: 'Lê Văn C',
      gender: 'MALE',
      status: 'ACTIVE',
      phone: '0923456789',
      dob: '1995-05-15',
      placeOfOrigin: 'Hà Nội',
      address: '23 Nguyễn Trãi, Hà Nội',
      familyPhone: '0987654321',
      isRegister: true,
      identification: '123456789',
      rentalRelationship: 'Bạn cùng phòng',
      imageUrl: 'https://example.com/avatar2.jpg'
    }
  ];

  const [searchTerm, setSearchTerm] = useState('');
  const [dialogOpen, setDialogOpen] = useState(false);
  const [selectedRoommate, setSelectedRoommate] = useState(null);

  const handleClick = (roommate) => {
    setSelectedRoommate(roommate);
    setDialogOpen(true);
  };

  const handleClose = () => {
    setDialogOpen(false);
    setSelectedRoommate(null);
  };

  const sliderSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1
  };

  return (
    <Grid2 container spacing={2} sx={{ maxWidth: 800, mx: 'auto', mt: 4 }}>
      <Grid2 item xs={12} md={6}>
        <Card sx={{ p: 2, boxShadow: 3 }}>
          <CardContent>
            <Typography variant="h6">Thông tin phòng</Typography>
            <Typography variant="body1">
              <b>Số phòng:</b> {room.roomNumber}
            </Typography>

            <Typography variant="body1">
              <b>Mô tả:</b> {room.description}
            </Typography>
            <Typography variant="body1">
              <b>Chỉ số điện:</b> {room.electricIndex}
            </Typography>
            <Typography variant="body1">
              <b>Chỉ số nước:</b> {room.waterIndex}
            </Typography>
            <Typography variant="body1">
              <b>Loại phòng:</b> {room.room_Type.name}
            </Typography>
            <Typography variant="body1">
              <b>Diện tích:</b> {room.room_Type.size} m²
            </Typography>
            <Typography variant="body1">
              <b>Nội thất:</b> {room.room_Type.furniture}
            </Typography>
            <Typography variant="body1">
              <b>Giá thuê:</b> {room.room_Type.cost} VNĐ
            </Typography>
            <Chip
              label={statusLabels[room.status.toUpperCase()] || 'Không xác định'}
              sx={{
                backgroundColor: statusColors[room.status.toUpperCase()] || 'black',
                color: 'white',
                fontWeight: 'bold',
                mt: 1
              }}
            />
            <Box maxWidth="100px" maxHeight="100px">
              <ImageSlider images={room.images} />
            </Box>
          </CardContent>
        </Card>
      </Grid2>
    </Grid2>
  );
}

export default UserRoom;
