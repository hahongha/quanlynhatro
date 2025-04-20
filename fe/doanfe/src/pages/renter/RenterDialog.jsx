import { useState, useEffect } from 'react';
import {
  Button,
  Grid2,
  MenuItem,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  FormHelperText,
  FormControlLabel,
  Switch,
  Card,
  CardMedia,
  Box,
  Typography,
  IconButton,
  Avatar
} from '@mui/material';
import { CloseOutlined } from '@ant-design/icons';
import { useDispatch } from 'react-redux';
import { addRenterRequest, updateRenterRequest } from '../../redux/actions/renterAction';

const initialData = {
  id: null,
  fullName: null,
  gender: null,
  status: null,
  phone: null,
  dob: null,
  identification: null,
  placeOfOrigin: null,
  address: null,
  familyPhone: null,
  isRegister: true,
  user: {
    imageUrl: null,
    email: null,
    userName: null,
    userId: null,
    role: {
      roleName: 'USER'
    }
  }
};

const fieldLabels = {
  fullName: 'Họ và tên',
  phone: 'Số điện thoại',
  dob: 'Ngày sinh',
  identification: 'CMND/CCCD',
  placeOfOrigin: 'Quê quán',
  address: 'Địa chỉ',
  familyPhone: 'Số điện thoại người thân',
  'user.userName': 'Tên đăng nhập',
  'user.email': 'Email'
};

export default function RenterDialog({ open, onClose, renterData, onDelete }) {
  const [renter, setRenter] = useState(initialData);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    setRenter(renterData || initialData);
  }, [renterData]);

  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setRenter((prev) => {
      const keys = name.split('.');
      if (keys.length > 1) {
        return { ...prev, user: { ...prev.user, [keys[1]]: value } };
      }
      return { ...prev, [name]: value };
    });
  };

  const handleSwitchChange = (e) => {
    setRenter((prev) => ({ ...prev, isRegister: e.target.checked }));
  };

  const validate = () => {
    let tempErrors = {};
    Object.keys(fieldLabels).forEach((field) => {
      const keys = field.split('.');
      const value = keys.length > 1 ? renter.user[keys[1]] : renter[field];
      if (!value) tempErrors[field] = 'Trường này không được để trống';
    });
    setErrors(tempErrors);
    return Object.keys(tempErrors).length === 0;
  };

  const handleSave = () => {
    if (validate()) {
      if (renter.id) {
        dispatch(updateRenterRequest(renter));
      } else {
        dispatch(addRenterRequest(renter));
      }
    }
  };

  const [imageError, setImageError] = useState(false);
  return (
    <Dialog open={open} onClose={onClose} fullWidth maxWidth="sm">
      <DialogTitle>
        <Box display="flex" justifyContent="space-between">
          <Typography variant="h3" color="primary">
            Thông tin khách thuê
          </Typography>
          <IconButton onClick={onClose}>
            <CloseOutlined />
          </IconButton>
        </Box>
      </DialogTitle>
      <DialogContent>
        <Grid2 container spacing={2} paddingTop={3}>
          <Grid2 size={4}>
            <TextField
              name={'imageUrl'}
              label={'Avatar'}
              fullWidth
              value={renter.user.imageUrl ? renter.user.imageUrl || '' : renter.user.imageUrl || ''}
              onChange={handleChange}
              error={!!errors[renter.user.imageUrl]}
              helperText={errors[renter.user.imageUrl]}
            />
          </Grid2>
          <Grid2 size={4}>
            <Avatar src={renter.user.imageUrl} alt={renter.fullName} />
          </Grid2>
          {Object.keys(fieldLabels).map((field, index) => (
            <Grid2 size={12} key={index}>
              <TextField
                name={field}
                label={fieldLabels[field]}
                fullWidth
                value={field.includes('user.') ? renter.user[field.split('.')[1]] || '' : renter[field] || ''}
                onChange={handleChange}
                error={!!errors[field]}
                helperText={errors[field]}
              />
            </Grid2>
          ))}
          <Grid2 size={4}>
            <TextField
              select
              name="gender"
              label="Giới tính"
              fullWidth
              value={renter.gender || 'MALE'}
              onChange={handleChange}
              error={!!errors.gender}
            >
              <MenuItem value="">Chọn giới tính</MenuItem>
              <MenuItem value="MALE">Nam</MenuItem>
              <MenuItem value="FEMALE">Nữ</MenuItem>
            </TextField>
            {errors.gender && <FormHelperText error>{errors.gender}</FormHelperText>}
          </Grid2>
          <Grid2 size={4}>
            <TextField
              select
              name="status"
              label="Trạng thái"
              fullWidth
              value={renter.status || 'INACTIVE'}
              onChange={handleChange}
              error={!!errors.status}
            >
              <MenuItem value="">Chọn trạng thái</MenuItem>
              <MenuItem value="ACTIVE">Đang thuê</MenuItem>
              <MenuItem value="INACTIVE">Đã trả phòng</MenuItem>
            </TextField>
            {errors.status && <FormHelperText error>{errors.status}</FormHelperText>}
          </Grid2>
          <Grid2 size={4}>
            <FormControlLabel
              control={<Switch checked={renter.isRegister} onChange={handleSwitchChange} />}
              label="Đã đăng ký thường trú chưa?"
            />
          </Grid2>
        </Grid2>
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose} variant="outlined">
          Hủy
        </Button>
        <Button onClick={handleSave} variant="contained" color="success">
          Lưu Thông Tin
        </Button>
        <Button onClick={onDelete} variant="contained" color="error">
          Xóa
        </Button>
      </DialogActions>
    </Dialog>
  );
}
