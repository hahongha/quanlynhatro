import React, { useState } from 'react';
import { Box, Button, Paper, Typography } from '@mui/material';
function SecurityContent({ userName }) {
  return (
    <Paper sx={{ p: 3 }}>
      <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
        Quản lý tài khoản
      </Typography>

      <Typography variant="body2" sx={{ mb: 3 }}>
        Quản lý tài khoản và mật khẩu của bạn
      </Typography>

      <Box>
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
              Tên tài khoản
            </Typography>
            <Typography variant="body1">{userName}</Typography>
          </Box>
          <Button color="secondary" sx={{ textTransform: 'none' }}>
            Chỉnh sửa
          </Button>
        </Box>
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
      {/* <ChangePasswordDialog /> */}
    </Paper>
  );
}
export default SecurityContent;
