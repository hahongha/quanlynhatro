import React, { useEffect, useState } from 'react';
import { AccountCircle, Phone, Email, LocationOn, Badge, Notes, Security, CalendarMonth } from '@mui/icons-material';

import { Container, Box, Typography, Avatar, Grid, Paper, Chip, CircularProgress } from '@mui/material';

import { useNavigate } from 'react-router-dom';

export default function UserInfoPage() {
  const navigate = useNavigate();
  const [userData, setUserData] = useState(null);
  const [roleData, setRoleData] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const profileString = localStorage.getItem('profile');
    if (!profileString) {
      navigate('/login');
    } else {
      try {
        const profile = JSON.parse(profileString);
        setUserData(profile);
        setRoleData(profile.role);
        setLoading(false);
      } catch (error) {
        console.error('Lỗi parse JSON:', error);
        navigate('/login');
      }
    }
  }, []);

  if (loading) {
    return (
      <Box display="flex" justifyContent="center" alignItems="center" minHeight="100vh">
        <CircularProgress />
      </Box>
    );
  }

  if (!userData) {
    return (
      <Typography variant="h6" color="error">
        Không thể tải dữ liệu người dùng.
      </Typography>
    );
  }

  return (
    <Container maxWidth="md" sx={{ py: 4 }}>
      <Paper elevation={3} sx={{ overflow: 'hidden', borderRadius: 4 }}>
        {/* Header */}
        <Box sx={{ bgcolor: 'primary.main', color: 'white', p: 4 }}>
          <Box display="flex" alignItems="center" gap={3}>
            <Avatar src={userData.imageUrl} alt={userData.fullName} sx={{ width: 96, height: 96, border: '3px solid white' }} />
            <Box>
              <Typography variant="h5" fontWeight="bold">
                {userData.fullName}
              </Typography>
              <Typography>
                {roleData?.roleName} - {roleData?.roleDes}
              </Typography>
            </Box>
          </Box>
        </Box>

        {/* Content */}
        <Box p={4}>
          <Grid container spacing={3}>
            <Grid item xs={12} md={6}>
              <InfoItem icon={<Badge />} label="User ID" value={userData.userId} />
              <InfoItem icon={<AccountCircle />} label="Tên đăng nhập" value={userData.userName} />
              <InfoItem
                icon={<Security />}
                label="Trạng thái"
                value={<Chip label={userData.status} color={userData.status === 'ACTIVE' ? 'success' : 'error'} size="small" />}
              />
              <InfoItem icon={<Email />} label="Email" value={userData.email} />
              <InfoItem icon={<Phone />} label="Số điện thoại" value={userData.phone} />
              <InfoItem icon={<Phone />} label="Điện thoại gia đình" value={userData.familyPhone} />
            </Grid>
            <Grid item xs={12} md={6}>
              <InfoItem icon={<LocationOn />} label="Địa chỉ" value={userData.address} />
              <InfoItem icon={<CalendarMonth />} label="Ngày sinh" value={userData.dob} />
              <InfoItem icon={<AccountCircle />} label="Giới tính" value={userData.gender === 'M' ? 'Nam' : 'Nữ'} />
              <InfoItem icon={<LocationOn />} label="Quê quán" value={userData.placeOfOrigin} />
              <InfoItem icon={<Notes />} label="Ghi chú" value={userData.note} />
            </Grid>
          </Grid>
        </Box>
      </Paper>
    </Container>
  );
}

function InfoItem({ icon, label, value }) {
  return (
    <Box display="flex" alignItems="center" gap={2} mb={2}>
      <Box color="primary.main">{icon}</Box>
      <Box>
        <Typography variant="caption" color="text.secondary">
          {label}
        </Typography>
        <Typography variant="body1">{value}</Typography>
      </Box>
    </Box>
  );
}
