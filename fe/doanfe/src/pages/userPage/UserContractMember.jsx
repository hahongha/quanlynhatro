import React, { useState } from 'react';
import {
  Card,
  CardContent,
  Typography,
  Avatar,
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
  Popover
} from '@mui/material';

const statusLabels = {
  ACTIVE: 'Đang hoạt động',
  PENDING: 'Chờ xác nhận',
  TERMINATED: 'Đã kết thúc',
  SUSPENDED: 'Bị đình chỉ',
  EVICTED: 'Bị trục xuất',
  'WAITING LIST': 'Danh sách chờ'
};

const statusColors = {
  ACTIVE: 'green',
  PENDING: 'orange',
  TERMINATED: 'gray',
  SUSPENDED: 'blue',
  EVICTED: 'red',
  'WAITING LIST': 'purple'
};

function UserMemberContract() {
  const tenant = {
    id: 1,
    fullName: 'Nguyễn Văn A',
    gender: 'MALE',
    status: 'ACTIVE',
    phone: '0987654321',
    dob: '1995-05-12',
    placeOfOrigin: 'Hà Nội',
    address: '123 Đường ABC, Quận XYZ, TP. Hà Nội',
    rentalRelationship: 'Chủ hợp đồng',
    imageUrl: 'https://example.com/avatar1.jpg'
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
      address: 'Số 56, Đường Lê Hồng Phong, Hải Phòng',
      rentalRelationship: 'Bạn cùng phòng',
      imageUrl: 'https://example.com/avatar2.jpg'
    },
    {
      id: 3,
      fullName: 'Lê Văn C',
      gender: 'MALE',
      status: 'PENDING',
      phone: '0945678912',
      dob: '1998-11-10',
      placeOfOrigin: 'Đà Nẵng',
      address: 'Số 78, Đường Trần Phú, Đà Nẵng',
      rentalRelationship: 'Bạn cùng phòng',
      imageUrl: 'https://example.com/avatar3.jpg'
    },
    {
      id: 4,
      fullName: 'Hoàng Thị D',
      gender: 'FEMALE',
      status: 'TERMINATED',
      phone: '0934567890',
      dob: '1999-03-15',
      placeOfOrigin: 'Hồ Chí Minh',
      address: 'Số 90, Đường Lý Tự Trọng, TP. HCM',
      rentalRelationship: 'Bạn cùng phòng',
      imageUrl: 'https://example.com/avatar4.jpg'
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

  const filteredRoommates = roommates.filter((roommate) => roommate.fullName.toLowerCase().includes(searchTerm.toLowerCase()));

  return (
    <Card sx={{ maxWidth: 500, mx: 'auto', mt: 4, p: 2, boxShadow: 3 }}>
      <CardContent>
        <Typography variant="h6">Người thuê cùng phòng</Typography>
        <TextField
          fullWidth
          margin="dense"
          label="Tìm kiếm theo tên"
          variant="outlined"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <List sx={{ maxHeight: 200, overflowY: 'auto', border: '1px solid #ddd', borderRadius: 2 }}>
          {filteredRoommates.map((roommate, index) => (
            <ListItem button key={index} onClick={() => handleSelectRoommate(roommate)}>
              <ListItemAvatar>
                <Avatar src={roommate.imageUrl} />
              </ListItemAvatar>
              <ListItemText primary={roommate.fullName} secondary={`Mối quan hệ: ${roommate.rentalRelationship}`} />
            </ListItem>
          ))}
        </List>
      </CardContent>

      {/* Dialog hiển thị thông tin chi tiết */}
      <Dialog open={dialogOpen} onClose={handleClose}>
        <DialogTitle>Thông tin chi tiết</DialogTitle>
        <DialogContent>
          {selectedRoommate && (
            <>
              <Typography variant="body1">
                <b>Họ và tên:</b> {selectedRoommate.fullName}
              </Typography>
              <Typography variant="body1">
                <b>Giới tính:</b> {selectedRoommate.gender}
              </Typography>
              <Typography variant="body1">
                <b>Ngày sinh:</b> {selectedRoommate.dob}
              </Typography>
              <Typography variant="body1">
                <b>Quê quán:</b> {selectedRoommate.placeOfOrigin}
              </Typography>
              <Typography variant="body1">
                <b>Địa chỉ:</b> {selectedRoommate.address}
              </Typography>
              <Typography variant="body1">
                <b>Mối quan hệ thuê trọ:</b> {selectedRoommate.rentalRelationship}
              </Typography>
              <Chip
                label={statusLabels[selectedRoommate.status.toUpperCase()] || 'Không xác định'}
                sx={{
                  backgroundColor: statusColors[selectedRoommate.status.toUpperCase()] || 'black',
                  color: 'white',
                  fontWeight: 'bold',
                  mt: 1
                }}
              />
            </>
          )}
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Đóng
          </Button>
        </DialogActions>
      </Dialog>
    </Card>
  );
}

export default UserMemberContract;
