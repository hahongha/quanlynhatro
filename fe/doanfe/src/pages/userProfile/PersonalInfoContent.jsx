import React, { useEffect, useState } from 'react';
import { Box, Button, Paper, Typography, Avatar, Chip, Stack, IconButton } from '@mui/material';
import EditableFields from '../../components/EditableFields';
import { useDispatch, useSelector } from 'react-redux';
import { getUserInfoRequest } from '../../redux/actions/authActions';
function PersonalInfoContent() {
  const [files, setFiles] = useState(false);
  const userInfo = useSelector((state) => state.auth.userInfo);
  const [person, setPerson] = useState(userInfo);
  const [loading, setLoading] = useState(true);
  const dispatch = useDispatch();

  useEffect(() => {
    if (userInfo) {
      setPerson(userInfo);
    }
  }, [userInfo]);

  useEffect(() => {
    dispatch(getUserInfoRequest());
  }, [dispatch]);

  const updateImage = (event) => {
    const file = event.target.files[0];
    if (file) {
    }
  };
  const statusLabels = {
    ONGOING: 'Đang thuê',
    PENDING: 'Chờ xác nhận',
    CHECKING_OUT: 'Đang trả phòng',
    CHECKED_OUT: 'Đã trả phòng'
  };

  const statusColors = {
    ONGOING: 'green',
    PENDING: 'yellow',
    CHECKING_OUT: 'blue',
    CHECKED_OUT: 'gray'
  };

  const handleSave = () => {
    setPerson(person);
    console.log(person);
  };

  return (
    <></>
    // <Paper sx={{ p: 3 }}>
    //   <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 2 }}>
    //     <Stack>
    //       <Typography variant="h3" component="h1">
    //         Thông tin cá nhân
    //       </Typography>
    //       {/* <Typography variant="h1" component="span" sx={{ fontStyle: 'italic', color: 'gray' }}>
    //         {person.user.role.roleDes}
    //       </Typography> */}
    //     </Stack>
    //     <Stack>
    //       <input type="file" accept="image/*" id="upload-avatar" style={{ display: 'none' }} onChange={updateImage} />
    //       <label htmlFor="upload-avatar">
    //         <Avatar alt="User" src={person.user.imageUrl} sx={{ width: 60, height: 60 }} />
    //       </label>
    //     </Stack>
    //   </Box>

    //   <Box>
    //     {/* Name field */}
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
    //           Họ và tên
    //         </Typography>
    //         <Typography variant="body1">{person.fullName}</Typography>
    //       </Box>
    //     </Box>

    //     {/* Display name field */}
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
    //           Trạng thái
    //         </Typography>
    //         <Chip
    //           label={statusLabels[person.status.toUpperCase()] || 'Không xác định'}
    //           sx={{ backgroundColor: statusColors[person.status.toUpperCase()] || 'black', color: 'white', fontWeight: 'bold', mt: 1 }}
    //         />
    //       </Box>
    //     </Box>
    //     {/* Nationality field */}
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         {person.isRegister === true ? (
    //           <Typography variant="body1" component="div" sx={{ fontWeight: 'bold', color: 'green' }}>
    //             Đã đăng lí thường trú
    //           </Typography>
    //         ) : (
    //           <Typography variant="body1" component="div" sx={{ fontWeight: 'bold', color: 'red' }}>
    //             Vui lòng liên hệ nhân viên/ chủ trọ để đăng kí thường trú hoặc ra công an phường/xã hiện tại đăng kí và gửi xác nhận cho
    //             nhân viên/chủ trọ
    //           </Typography>
    //         )}
    //       </Box>
    //     </Box>

    //     {/* Birthday field */}
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
    //           Ngày sinh
    //         </Typography>
    //         <Typography variant="body1">{person.dob}</Typography>
    //       </Box>
    //     </Box>

    //     {/* Nationality field */}
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
    //           Quê quán
    //         </Typography>
    //         <Typography variant="body1">{person.placeOfOrigin}</Typography>
    //       </Box>
    //     </Box>
    //     <Box
    //       sx={{
    //         display: 'flex',
    //         justifyContent: 'space-between',
    //         py: 2,
    //         borderBottom: '1px solid #e7e7e7'
    //       }}
    //     >
    //       <Box>
    //         <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
    //           Địa chỉ thường trú
    //         </Typography>
    //         <Typography variant="body1">{person.address}</Typography>
    //       </Box>
    //     </Box>
    //     {/* mobile phone */}
    //     <EditableFields phone={person.phone} person={person} field={'phone'} label={'Số điện thoại'} />
    //     {/* fammily phone */}
    //     <EditableFields
    //       phone={person.familyPhone}
    //       person={person}
    //       field={'familyPhone'}
    //       label={'Số điện thoại liên hệ trong trường hợp khẩn cấp'}
    //     />
    //     {/* email phone */}

    //     <EditableFields phone={person.user.email} person={person} field={'user.email'} label={'Email'} />
    //   </Box>
    // </Paper>
  );
}
export default PersonalInfoContent;
