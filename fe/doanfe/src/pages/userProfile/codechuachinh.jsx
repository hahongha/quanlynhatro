// import React, { useState } from 'react';
// import {
//   AppBar,
//   Box,
//   Button,
//   Container,
//   List,
//   ListItem,
//   ListItemIcon,
//   ListItemText,
//   Paper,
//   Toolbar,
//   Typography,
//   Avatar,
//   Chip,
//   IconButton,
//   Grid,
//   TextField,
//   FormControl,
//   InputLabel,
//   Select,
//   MenuItem,
//   Switch,
//   FormControlLabel,
//   Card,
//   CardContent
// } from '@mui/material';
// import PersonIcon from '@mui/icons-material/Person';
// import LockIcon from '@mui/icons-material/Lock';
// import PeopleIcon from '@mui/icons-material/People';
// import SettingsIcon from '@mui/icons-material/Settings';
// import PaymentIcon from '@mui/icons-material/Payment';
// import SecurityIcon from '@mui/icons-material/Security';
// import HelpIcon from '@mui/icons-material/Help';
// import AddIcon from '@mui/icons-material/Add';
// import CreditCardIcon from '@mui/icons-material/CreditCard';
// import { ThemeProvider, createTheme } from '@mui/material/styles';

// // Create a custom theme with Booking.com colors
// const theme = createTheme({
//   palette: {
//     primary: {
//       main: '#003580' // Booking.com blue
//     },
//     secondary: {
//       main: '#0071c2' // Booking.com lighter blue
//     },
//     success: {
//       main: '#008009' // Green for verified badge
//     }
//   },
//   typography: {
//     fontFamily: 'Arial, sans-serif'
//   }
// });

// function UserProfile() {
//   // State to track which section is currently selected
//   const [activeSection, setActiveSection] = useState('personalInfo');

//   const handleSectionChange = (section) => {
//     setActiveSection(section);
//   };

//   // Content for Personal Information section
//   const PersonalInfoContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', mb: 2 }}>
//         <Typography variant="h5" component="h1">
//           Thông tin cá nhân
//         </Typography>
//         <Avatar alt="User" src="/api/placeholder/60/60" sx={{ width: 60, height: 60 }} />
//       </Box>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Cập nhật thông tin của bạn và tìm hiểu các thông tin này được sử dụng ra sao.
//       </Typography>

//       <Box>
//         {/* Name field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Tên
//             </Typography>
//             <Typography variant="body1">Cua Xanh</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>

//         {/* Display name field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Tên hiển thị
//             </Typography>
//             <Typography variant="body1">Chọn tên hiển thị</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>

//         {/* Email field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Địa chỉ email
//             </Typography>
//             <Box sx={{ display: 'flex', alignItems: 'center' }}>
//               <Typography variant="body1" sx={{ mr: 1 }}>
//                 phamha03122003@gmail.com
//               </Typography>
//               <Chip label="Xác thực" size="small" color="success" sx={{ fontSize: '0.7rem', height: 24 }} />
//             </Box>
//             <Typography variant="caption" color="text.secondary">
//               Đây là địa chỉ email bạn dùng để đăng nhập. Chúng tôi cũng sẽ gửi các xác nhận đặt chỗ tới địa chỉ này.
//             </Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>

//         {/* Phone field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Số điện thoại
//             </Typography>
//             <Typography variant="body1">Thêm số điện thoại của bạn</Typography>
//             <Typography variant="caption" color="text.secondary">
//               Chỗ nghỉ hoặc địa điểm tham quan bạn đặt sẽ liên lạc với bạn qua số này nếu cần.
//             </Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>

//         {/* Birthday field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Ngày sinh
//             </Typography>
//             <Typography variant="body1">Nhập ngày sinh của bạn</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>

//         {/* Nationality field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Quốc tịch
//             </Typography>
//             <Typography variant="body1">Chọn vùng/quốc gia của bạn</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Chỉnh sửa
//           </Button>
//         </Box>
//       </Box>
//     </Paper>
//   );

//   // Content for Security Settings section
//   const SecurityContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
//         Cài đặt bảo mật
//       </Typography>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Quản lý mật khẩu và cài đặt bảo mật của tài khoản bạn.
//       </Typography>

//       <Box>
//         {/* Password field */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Mật khẩu
//             </Typography>
//             <Typography variant="body1">********</Typography>
//             <Typography variant="caption" color="text.secondary">
//               Mật khẩu của bạn phải có ít nhất 8 ký tự và bao gồm chữ và số.
//             </Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Thay đổi
//           </Button>
//         </Box>

//         {/* Two-factor authentication */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Xác thực hai yếu tố
//             </Typography>
//             <Typography variant="body1">Chưa kích hoạt</Typography>
//             <Typography variant="caption" color="text.secondary">
//               Thêm một lớp bảo mật cho tài khoản của bạn bằng cách kích hoạt xác thực hai yếu tố.
//             </Typography>
//           </Box>
//           <Button variant="outlined" color="secondary" sx={{ textTransform: 'none' }}>
//             Kích hoạt
//           </Button>
//         </Box>

//         {/* Login devices */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Thiết bị đăng nhập
//             </Typography>
//             <Typography variant="body1">3 thiết bị được kết nối</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Quản lý
//           </Button>
//         </Box>
//       </Box>
//     </Paper>
//   );

//   // Content for Travel Companions section
//   const CompanionsContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
//         Người đi cùng
//       </Typography>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Thêm thông tin về những người thường đi du lịch cùng bạn để đặt phòng nhanh hơn.
//       </Typography>

//       <Box sx={{ mb: 3 }}>
//         <Button variant="outlined" color="secondary" startIcon={<AddIcon />} sx={{ textTransform: 'none' }}>
//           Thêm người đi cùng
//         </Button>
//       </Box>

//       <Typography variant="body2" color="text.secondary">
//         Bạn chưa thêm bất kỳ người đi cùng nào. Thêm thông tin về gia đình và bạn bè để đơn giản hóa việc đặt phòng cho nhóm.
//       </Typography>
//     </Paper>
//   );

//   // Content for General Settings section
//   const GeneralSettingsContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
//         Cài đặt chung
//       </Typography>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Quản lý tùy chọn ngôn ngữ, tiền tệ và thông báo.
//       </Typography>

//       <Box>
//         {/* Language settings */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Ngôn ngữ
//             </Typography>
//             <Typography variant="body1">Tiếng Việt</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Thay đổi
//           </Button>
//         </Box>

//         {/* Currency settings */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Tiền tệ
//             </Typography>
//             <Typography variant="body1">VND - Việt Nam Đồng</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Thay đổi
//           </Button>
//         </Box>

//         {/* Notification settings */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Thông báo
//             </Typography>
//             <Typography variant="body1">Email, SMS</Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Quản lý
//           </Button>
//         </Box>
//       </Box>
//     </Paper>
//   );

//   // Content for Payment Methods section
//   const PaymentMethodsContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
//         Phương thức thanh toán
//       </Typography>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Thêm và quản lý các phương thức thanh toán của bạn.
//       </Typography>

//       <Box sx={{ mb: 3 }}>
//         <Button variant="outlined" color="secondary" startIcon={<CreditCardIcon />} sx={{ textTransform: 'none' }}>
//           Thêm thẻ thanh toán
//         </Button>
//       </Box>

//       <Typography variant="body2" color="text.secondary" sx={{ mb: 4 }}>
//         Bạn chưa lưu phương thức thanh toán nào. Thêm phương thức thanh toán để đặt phòng nhanh hơn.
//       </Typography>

//       <Typography variant="h6" sx={{ mb: 2 }}>
//         Các phương thức thanh toán được chấp nhận
//       </Typography>

//       <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 2 }}>
//         <img src="/api/placeholder/50/30" alt="Visa" />
//         <img src="/api/placeholder/50/30" alt="MasterCard" />
//         <img src="/api/placeholder/50/30" alt="American Express" />
//         <img src="/api/placeholder/50/30" alt="JCB" />
//       </Box>
//     </Paper>
//   );

//   // Content for Privacy Management section
//   const PrivacyContent = () => (
//     <Paper sx={{ p: 3 }}>
//       <Typography variant="h5" component="h1" sx={{ mb: 3 }}>
//         Quản lý quyền riêng tư và dữ liệu
//       </Typography>

//       <Typography variant="body2" sx={{ mb: 3 }}>
//         Kiểm soát dữ liệu cá nhân và các tùy chọn quyền riêng tư của bạn.
//       </Typography>

//       <Box>
//         {/* Data usage options */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             alignItems: 'center',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Chia sẻ dữ liệu cho mục đích cá nhân hóa
//             </Typography>
//             <Typography variant="body2" color="text.secondary">
//               Cho phép Booking.com sử dụng dữ liệu của bạn để cá nhân hóa kết quả tìm kiếm và đề xuất.
//             </Typography>
//           </Box>
//           <FormControlLabel control={<Switch defaultChecked />} label="" />
//         </Box>

//         {/* Marketing preferences */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             alignItems: 'center',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Thông báo tiếp thị
//             </Typography>
//             <Typography variant="body2" color="text.secondary">
//               Nhận thông tin về ưu đãi và khuyến mãi từ Booking.com.
//             </Typography>
//           </Box>
//           <FormControlLabel control={<Switch defaultChecked />} label="" />
//         </Box>

//         {/* Data download */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold' }}>
//               Tải xuống dữ liệu cá nhân
//             </Typography>
//             <Typography variant="body2" color="text.secondary">
//               Nhận bản sao dữ liệu cá nhân mà Booking.com lưu trữ về bạn.
//             </Typography>
//           </Box>
//           <Button color="secondary" sx={{ textTransform: 'none' }}>
//             Yêu cầu dữ liệu
//           </Button>
//         </Box>

//         {/* Account deletion */}
//         <Box
//           sx={{
//             display: 'flex',
//             justifyContent: 'space-between',
//             py: 2,
//             borderBottom: '1px solid #e7e7e7'
//           }}
//         >
//           <Box>
//             <Typography variant="body1" component="div" sx={{ fontWeight: 'bold', color: 'error.main' }}>
//               Xóa tài khoản
//             </Typography>
//             <Typography variant="body2" color="text.secondary">
//               Xóa vĩnh viễn tài khoản và tất cả dữ liệu cá nhân của bạn khỏi Booking.com.
//             </Typography>
//           </Box>
//           <Button color="error" sx={{ textTransform: 'none' }}>
//             Xóa tài khoản
//           </Button>
//         </Box>
//       </Box>
//     </Paper>
//   );

//   // Render the appropriate content based on the activeSection
//   const renderContent = () => {
//     switch (activeSection) {
//       case 'personalInfo':
//         return <PersonalInfoContent />;
//       case 'security':
//         return <SecurityContent />;
//       case 'companions':
//         return <CompanionsContent />;
//       case 'settings':
//         return <GeneralSettingsContent />;
//       case 'payment':
//         return <PaymentMethodsContent />;
//       case 'privacy':
//         return <PrivacyContent />;
//       default:
//         return <PersonalInfoContent />;
//     }
//   };

//   return (
//     <Box sx={{ flexGrow: 1 }}>
//       {/* Breadcrumb */}
//       <Container maxWidth="lg" sx={{ mt: 2, mb: 2 }}>
//         <Typography variant="body2" component="div">
//           Tài khoản &gt;{' '}
//           {activeSection === 'personalInfo'
//             ? 'Thông tin cá nhân'
//             : activeSection === 'security'
//               ? 'Cài đặt bảo mật'
//               : activeSection === 'companions'
//                 ? 'Người đi cùng'
//                 : activeSection === 'settings'
//                   ? 'Cài đặt chung'
//                   : activeSection === 'payment'
//                     ? 'Phương thức thanh toán'
//                     : 'Quản lý quyền riêng tư và dữ liệu'}
//         </Typography>
//       </Container>

//       {/* Main content */}
//       <Container maxWidth="lg">
//         <Grid container spacing={3}>
//           {/* Left sidebar */}
//           <Grid item xs={12} md={3}>
//             <Paper>
//               <List component="nav">
//                 <ListItem button selected={activeSection === 'personalInfo'} onClick={() => handleSectionChange('personalInfo')}>
//                   <ListItemIcon>
//                     <PersonIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Thông tin cá nhân" />
//                 </ListItem>
//                 <ListItem button selected={activeSection === 'security'} onClick={() => handleSectionChange('security')}>
//                   <ListItemIcon>
//                     <LockIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Cài đặt bảo mật" />
//                 </ListItem>
//                 <ListItem button selected={activeSection === 'companions'} onClick={() => handleSectionChange('companions')}>
//                   <ListItemIcon>
//                     <PeopleIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Người đi cùng" />
//                 </ListItem>
//                 <ListItem button selected={activeSection === 'settings'} onClick={() => handleSectionChange('settings')}>
//                   <ListItemIcon>
//                     <SettingsIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Cài đặt chung" />
//                 </ListItem>
//                 <ListItem button selected={activeSection === 'payment'} onClick={() => handleSectionChange('payment')}>
//                   <ListItemIcon>
//                     <PaymentIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Phương thức thanh toán" />
//                 </ListItem>
//                 <ListItem button selected={activeSection === 'privacy'} onClick={() => handleSectionChange('privacy')}>
//                   <ListItemIcon>
//                     <SecurityIcon />
//                   </ListItemIcon>
//                   <ListItemText primary="Quản lý quyền riêng tư và dữ liệu" />
//                 </ListItem>
//               </List>
//             </Paper>
//           </Grid>

//           {/* Right content - dynamic based on selected section */}
//           <Grid item xs={12} md={9}>
//             {renderContent()}
//           </Grid>
//         </Grid>
//       </Container>

//       {/* Footer */}
//       <Box sx={{ mt: 5, bgcolor: '#f5f5f5', p: 2, textAlign: 'center' }}>
//         <Typography variant="caption" color="text.secondary">
//           https://account.booking.com/mysettings/security?aid=304142
//         </Typography>
//       </Box>
//     </Box>
//   );
// }

// export default UserProfile;
