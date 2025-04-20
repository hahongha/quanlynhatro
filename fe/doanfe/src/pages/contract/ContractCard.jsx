import React, { useState } from 'react';
import { Card, CardContent, CardHeader, Avatar, Button, Typography, Box, Tabs, Tab, CardActions } from '@mui/material';
function ContractCard({ contract, onEdit }) {
  const [tabIndex, setTabIndex] = useState(0);
  return (
    <Card sx={{ maxWidth: 400, m: 2, borderLeft: 4, borderColor: 'blue' }}>
      <CardHeader
        avatar={<Avatar src={contract.renter.user.imageUrl} alt={contract.renter.fullName} />}
        title={contract.renter.fullName}
        subheader={contract.renter.user.email}
      />
      <Tabs value={tabIndex} onChange={(e, newIndex) => setTabIndex(newIndex)}>
        <Tab label="Người thuê" />
        <Tab label="Phòng" />
        <Tab label="Hợp đồng" />
        <Tab label="Hình ảnh" />
      </Tabs>
      <CardContent sx={{ minHeight: 200 }}>
        {tabIndex === 0 && (
          <>
            <Typography variant="body2">
              📞 <strong>Điện thoại:</strong> {contract.renter.phone}
            </Typography>
            <Typography variant="body2">
              🏠 <strong>Địa chỉ:</strong> {contract.renter.address}
            </Typography>
            <Typography variant="body2">
              🆔 <strong>CMND:</strong> {contract.renter.identification}
            </Typography>
          </>
        )}
        {tabIndex === 1 && (
          <>
            <Typography variant="body2">
              🏠 <strong>Phòng:</strong> {contract.room.roomNumber} - {contract.room.room_Type.name}
            </Typography>
            <Typography variant="body2">
              📜 <strong>Mô tả:</strong> {contract.room.description}
            </Typography>
          </>
        )}
        {tabIndex === 2 && (
          <>
            <Typography variant="body2">
              📆 <strong>Ngày bắt đầu:</strong> {contract.startDate}
            </Typography>
            <Typography variant="body2">
              ⏳ <strong>Ngày hết hạn:</strong> {contract.endDate}
            </Typography>
            <Typography variant="body2">
              💰 <strong>Giá thuê:</strong> {contract.rentalPrice.toLocaleString()} VND
            </Typography>
            <Typography variant="body2">
              💵 <strong>Đặt cọc:</strong> {contract.deposit.toLocaleString()} VND
            </Typography>
          </>
        )}
        {tabIndex === 3 && (
          <Box sx={{ display: 'flex', gap: 1, mt: 1 }}>
            {contract.room.images.map((img, index) => (
              <img key={index} src={img} alt="Room" width={100} height={150} style={{ borderRadius: 8 }} />
            ))}
          </Box>
        )}
      </CardContent>
      <CardActions>
        <Button variant="contained" color="primary" sx={{ mt: 2 }} onClick={() => onEdit(contract)}>
          Chỉnh sửa
        </Button>
      </CardActions>
    </Card>
  );
}

export default ContractCard;
