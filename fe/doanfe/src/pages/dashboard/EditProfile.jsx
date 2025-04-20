import React, { useEffect, useState } from 'react';
import { TextField, Button, Box, Typography, Avatar, Paper, Container } from '@mui/material';
import userAPI from '../../redux/api/use.api'; // bạn cần cập nhật đường dẫn đúng
import { useNavigate } from 'react-router';
import http from '../../redux/api/http';

export default function UserEditForm() {
  const [userData, setUserData] = useState(null);
  const [formData, setFormData] = useState({
    email: '',
    imageUrl: '',
    phone: '',
    familyPhone: '',
    userName: ''
  });
  const navigate = useNavigate();

  useEffect(() => {
    const profile = JSON.parse(localStorage.getItem('profile'));
    if (!profile) {
      navigate('/login');
    } else {
      setUserData(profile);
      setFormData({
        email: profile.email || '',
        imageUrl: profile.imageUrl || '',
        phone: profile.phone || '',
        familyPhone: profile.familyPhone || '',
        userName: profile.userName || ''
      });
    }
  }, [navigate]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    try {
      const res = await http.put(`/user/userProfile`, data); // tạo API này ở backend
      alert('Cập nhật thành công!');
      localStorage.setItem('profile', JSON.stringify(res.data));
      navigate('/dashboard/profile');
    } catch (error) {
      console.error(error);
      alert('Cập nhật thất bại!');
    }
  };

  if (!userData) return null;

  return (
    <Container maxWidth="sm" sx={{ py: 4 }}>
      <Paper elevation={3} sx={{ p: 4 }}>
        <Typography variant="h5" fontWeight="bold" mb={2}>
          Cập nhật thông tin cá nhân
        </Typography>

        <Box display="flex" justifyContent="center" mb={2}>
          <Avatar src={formData.imageUrl} sx={{ width: 96, height: 96 }} />
        </Box>

        <Box display="flex" flexDirection="column" gap={2}>
          <TextField name="userName" label="Tên đăng nhập" value={formData.userName} onChange={handleChange} fullWidth />
          <TextField name="email" label="Email" value={formData.email} onChange={handleChange} fullWidth />
          <TextField name="phone" label="Số điện thoại" value={formData.phone} onChange={handleChange} fullWidth />
          <TextField name="familyPhone" label="Điện thoại người thân" value={formData.familyPhone} onChange={handleChange} fullWidth />
          <TextField name="imageUrl" label="Link ảnh đại diện" value={formData.imageUrl} onChange={handleChange} fullWidth />

          <Button variant="contained" color="primary" onClick={handleSubmit}>
            Lưu thay đổi
          </Button>
        </Box>
      </Paper>
    </Container>
  );
}
