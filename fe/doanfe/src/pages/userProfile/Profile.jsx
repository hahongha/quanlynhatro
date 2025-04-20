import React, { useState, useEffect } from 'react';
import { Card, CardContent, Typography, Avatar, Grid2, Divider } from '@mui/material';

const Profile = () => {
  const person = {
    id: 1,
    full_name: 'Nguyễn Văn A',
    dob: '1990-05-20',
    address: '123 Đường ABC, Quận 1, TP.HCM',
    phone: '0909123456',
    family_phone: '0987654321',
    identification: '123456789',
    place_of_origin: 'Hà Nội',
    status: 'active',
    image_url: 'https://example.com/avatar.jpg',
    role_type: 'staff',
    salary: 15000000.0,
    hire_date: '2020-06-15',
    is_register: null,
    user_id: null,
    create_at: '2024-03-26 08:00:00'
  };

  return (
    <Card sx={{ maxWidth: 600, margin: 'auto', padding: 2 }}>
      <CardContent>
        <Grid2 container spacing={2} alignItems="center">
          {person.image_url && (
            <Grid2 item>
              <Avatar src={person.image_url} alt={person.full_name} sx={{ width: 80, height: 80 }} />
            </Grid2>
          )}
          <Grid2 item>
            {person.full_name && (
              <Typography variant="h5" gutterBottom>
                {person.full_name}
              </Typography>
            )}
            {person.role_type && (
              <Typography variant="body1" color="textSecondary">
                {person.role_type === 'staff' ? 'Nhân viên' : 'Khách thuê'}
              </Typography>
            )}
          </Grid2>
        </Grid2>
        <Divider sx={{ my: 2 }} />
        {person.dob && (
          <Typography>
            <strong>Ngày sinh:</strong> {person.dob}
          </Typography>
        )}
        {person.address && (
          <Typography>
            <strong>Địa chỉ:</strong> {person.address}
          </Typography>
        )}
        {person.phone && (
          <Typography>
            <strong>SĐT:</strong> {person.phone}
          </Typography>
        )}
        {person.family_phone && (
          <Typography>
            <strong>SĐT gia đình:</strong> {person.family_phone}
          </Typography>
        )}
        {person.identification && (
          <Typography>
            <strong>CMND/CCCD:</strong> {person.identification}
          </Typography>
        )}
        {person.place_of_origin && (
          <Typography>
            <strong>Nguyên quán:</strong> {person.place_of_origin}
          </Typography>
        )}
        {person.status && (
          <Typography>
            <strong>Trạng thái:</strong> {person.status === 'active' ? 'Hoạt động' : 'Không hoạt động'}
          </Typography>
        )}
        {person.role_type === 'staff' && person.salary && (
          <Typography>
            <strong>Lương:</strong> {person.salary.toLocaleString() + ' VND'}
          </Typography>
        )}
        {person.role_type === 'staff' && person.hire_date && (
          <Typography>
            <strong>Ngày nhận việc:</strong> {person.hire_date}
          </Typography>
        )}
        {person.role_type === 'renter' && person.is_register !== null && (
          <Typography>
            <strong>Đăng ký:</strong> {person.is_register ? 'Có' : 'Không'}
          </Typography>
        )}
        {person.create_at && (
          <Typography variant="body2" color="textSecondary" sx={{ mt: 2 }}>
            Ngày tạo hồ sơ: {person.create_at}
          </Typography>
        )}
      </CardContent>
    </Card>
  );
};

export default Profile;
