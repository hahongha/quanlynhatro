import React, { useState } from 'react';
import { Box, Button, Paper, Typography } from '@mui/material';
import EditableFields from '../../components/EditableFields';
function SecurityContent() {
  const userData = {
    imageUrl: 'https://res.cloudinary.com/dlyprrqnn/image/upload/v1743004321/TEST/wsyqk6znjakexow6a5pc.jpg',
    email: 'phamha03122003@gmail.com',
    status: 'ACTIVE',
    userName: 'ROOT',
    userId: '99e39f175ca14259b91996d7bf152587',
    role: {
      id: 1,
      roleName: 'ROOT',
      authorityDTOs: []
    }
  };

  return (
    <Paper sx={{ p: 3 }}>
      <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
        Quản lý tài khoản
      </Typography>

      <Typography variant="body2" sx={{ mb: 3 }}>
        Quản lý tài khoản và mật khẩu của bạn
      </Typography>

      <Box>
        {/* Name field */}

        <EditableFields person={userData} phone={userData.userName} field={'userName'} label={'Tên tài khoản'} />

        {/* Password field */}
        <Box
          sx={{
            display: 'flex',
            justifyContent: 'space-between',
            py: 2,
            borderBottom: '1px solid #e7e7e7'
          }}
        >
          <Box>
            <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
              Mật khẩu
            </Typography>
            <Typography variant="body1">********</Typography>
            <Typography variant="caption" color="text.secondary">
              Mật khẩu của bạn phải có ít nhất 8 ký tự và bao gồm chữ và số.
            </Typography>
          </Box>
          <Button color="secondary" sx={{ textTransform: 'none' }}>
            Chỉnh sửa
          </Button>
        </Box>
      </Box>
    </Paper>
  );
}
export default SecurityContent;
