import { useState } from 'react';
import {
  Card,
  CardContent,
  Typography,
  Avatar,
  TextField,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  Grid2,
  Button,
  Box,
  IconButton,
  Chip
} from '@mui/material';
import { QrCode2, Edit, Download, CloseOutlined, Male, Female } from '@mui/icons-material';
import MainCard from 'components/MainCard';
function UserRenter() {
  const handleEdit = () => {
    setIsEditing(true);
  };
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

  const tenant = {
    id: '890da2fa7d784438a61647aaf9b93eb6',
    fullName: 'Phạm Thị Hà',
    gender: 'Female',
    status: 'active',
    phone: '0987654321',
    dob: '2025-03-28',
    identification: '123456789',
    placeOfOrigin: 'Hà Nội',
    address: '123 Đường ABC, Quận XYZ, TP. Hà Nội',
    familyPhone: '0123456789',
    isRegister: true,
    user: {
      imageUrl: 'https://example.com/profile.jpg',
      email: 'phamthiha@example.com',
      status: 'active',
      userName: 'phamthiha',
      userId: 'user_001',
      role: {
        id: 3,
        roleName: null,
        authorityDTOs: []
      }
    }
  };

  const [isEditing, setIsEditing] = useState(false);
  const [formData, setFormData] = useState({
    phone: tenant.phone,
    email: tenant.user.email,
    placeOfOrigin: tenant.placeOfOrigin,
    address: tenant.address,
    familyPhone: tenant.familyPhone
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    onClose();
  };

  const onClose = () => {
    setIsEditing(false);
  };

  return (
    <MainCard>
      <Card sx={{ mx: 'auto', mt: 0, p: 0, boxShadow: 3 }}>
        <CardContent>
          <Grid2 container spacing={2} alignItems="center">
            <Grid2>
              <Avatar src={tenant.user.imageUrl} sx={{ width: 80, height: 80 }} />
            </Grid2>
            <Grid2>
              <Typography variant="h5" fontWeight="bold">
                {tenant.fullName}
              </Typography>
              <Typography color="textSecondary">{tenant.user.userName}</Typography>
            </Grid2>
            <Grid2>
              {tenant.gender.toUpperCase === 'MALE' ? <Male sx={{ color: 'blue' }} /> : <Female sx={{ color: 'error.main' }} />}
            </Grid2>
          </Grid2>
          <Typography variant="body1">
            <b>Ngày sinh:</b> {tenant.dob}
          </Typography>
          <Typography variant="body1">
            <b>Email:</b> {tenant.user.email}
          </Typography>
          <Typography variant="body1">
            <b>Quê quán:</b> {tenant.placeOfOrigin}
          </Typography>
          <Typography variant="body1">
            <b>Địa chỉ:</b> {tenant.address}
          </Typography>
          <Typography variant="body1">
            <b>CMND/CCCD:</b> {tenant.identification}
          </Typography>
          <Typography variant="body1">
            <b>Số điện thoại:</b> {tenant.phone}
          </Typography>
          <Typography variant="body1">
            <b>Số điện thoại người thân:</b> {tenant.familyPhone}
          </Typography>

          <Typography variant="body1">
            <b>Trạng thái:</b>
            <Chip
              label={statusLabels[tenant.status.toUpperCase()] || 'Không xác định'}
              sx={{ backgroundColor: statusColors[tenant.status.toUpperCase()] || 'black', color: 'white', fontWeight: 'bold', mt: 1 }}
            />
          </Typography>

          <Grid2 container spacing={2} mt={2}>
            <Grid2>
              <Button variant="contained" startIcon={<Edit />} onClick={handleEdit}>
                Chỉnh sửa
              </Button>
            </Grid2>
          </Grid2>
        </CardContent>
      </Card>
      <Dialog open={isEditing}>
        <DialogTitle>
          <Box display="flex" justifyContent="space-between">
            <Typography variant="h3" color="primary">
              Chỉnh sửa thông tin cá nhân
            </Typography>
            <IconButton onClick={onClose}>
              <CloseOutlined />
            </IconButton>
          </Box>
        </DialogTitle>
        <DialogContent>
          <TextField fullWidth margin="dense" label="Số điện thoại" name="phone" value={formData.phone} onChange={handleChange} />
          <TextField fullWidth margin="dense" label="Email" name="email" value={formData.email} onChange={handleChange} />
          <TextField
            fullWidth
            margin="dense"
            label="Quê quán"
            name="placeOfOrigin"
            value={formData.placeOfOrigin}
            onChange={handleChange}
          />
          <TextField fullWidth margin="dense" label="Địa chỉ" name="address" value={formData.address} onChange={handleChange} />
          <TextField
            fullWidth
            margin="dense"
            label="Số điện thoại người thân"
            name="familyPhone"
            value={formData.familyPhone}
            onChange={handleChange}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={onClose} color="secondary">
            Hủy
          </Button>
          <Button onClick={handleSave} color="primary">
            Lưu
          </Button>
        </DialogActions>
      </Dialog>
    </MainCard>
  );
}

export default UserRenter;
