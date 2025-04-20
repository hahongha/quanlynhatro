import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import {
  Button,
  MenuItem,
  Select,
  InputLabel,
  FormControl,
  Switch,
  FormControlLabel,
  Stack,
  Grid2,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent
} from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { getAllRoleRequest } from '../../redux/actions/roleAction';
import { addUserRequest, updateUserRequest } from '../../redux/actions/userAction';
import MainCard from 'components/MainCard';
import { useNavigate } from 'react-router';
// Schema validation với Yup
const validationSchema = Yup.object({
  userName: Yup.string().required('Vui lòng nhập họ và tên'),
  email: Yup.string().email('Email không hợp lệ').required('Vui lòng nhập email')
});

const RoleDialog = ({ userId, userName, email, role, status, handleClose }) => {
  const rolesData = useSelector((state) => state.role.all_role);
  const [ro, setRo] = useState(role ? role : '');
  const [id, setId] = useState(userId ? userId : '');
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getAllRoleRequest());
  }, [dispatch]);
  const navigate = useNavigate();

  const handleBack = () => {
    navigate('/user');
    handleClose();
  };
  return (
    <MainCard title={userId ? '' : 'Thêm mới tài khoản'}>
      <Formik
        initialValues={{
          userId: id,
          userName: userName ? userName : '',
          email: email ? email : '',
          role: {
            id: ro.id
          },
          status: status ? status : 'INACTIVE'
        }}
        validationSchema={validationSchema}
        onSubmit={(values, { setSubmitting }) => {
          {
            values.userId ? dispatch(updateUserRequest(values)) : dispatch(addUserRequest(values));
          }
          setSubmitting(false);
          handleClose();
        }}
      >
        {({ values, handleChange, handleBlur, isSubmitting, setFieldValue, touched, errors }) => (
          <Form>
            <Grid2 container spacing={3}>
              {/* Họ và Tên */}
              <Grid2 item size={12}>
                <Stack>
                  <InputLabel>Tên tài khoản</InputLabel>
                  <TextField
                    fullWidth
                    margin="dense"
                    name="userName"
                    value={values.userName}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    error={touched.userName && Boolean(errors.userName)}
                    helperText={touched.userName && errors.name}
                  />
                </Stack>
                <ErrorMessage name="userName" component="div" style={{ color: 'red' }} />
              </Grid2>
              {/* Email */}
              <Grid2 item size={12}>
                <Stack>
                  <InputLabel htmlFor="email">Email</InputLabel>
                  {/* <TextField placeholder="Nhập email"> */}
                  <TextField
                    fullWidth
                    margin="dense"
                    name="email"
                    value={values.email}
                    onChange={handleChange}
                    onBlur={handleBlur}
                    error={touched.email && Boolean(errors.email)}
                    helperText={touched.email && errors.email}
                  />
                </Stack>
                <ErrorMessage name="email" component="div" style={{ color: 'red' }} />
              </Grid2>

              {/* Vai trò - Select Dropdown */}
              <Grid2 item size={12}>
                <Stack>
                  <InputLabel>Vai trò</InputLabel>
                  <FormControl fullWidth>
                    <Select
                      labelId="role-label"
                      value={values.role.id}
                      onChange={(event) => {
                        const selectedId = event.target.value;
                        setRo(selectedId);
                        setFieldValue('role', { id: selectedId }); // Đúng cách để cập nhật object
                      }}
                      onBlur={handleBlur}
                    >
                      {rolesData.map((r) => (
                        <MenuItem key={r.id} value={r.id}>
                          {r.roleName}
                        </MenuItem>
                      ))}
                    </Select>
                  </FormControl>
                </Stack>
                <ErrorMessage name="role" component="div" style={{ color: 'red' }} />
              </Grid2>

              {/* Trạng thái - Switch */}
              <Grid2 item size={12}>
                <Stack>
                  <InputLabel>Trạng thái</InputLabel>
                  <FormControlLabel
                    control={
                      <Switch
                        checked={values.status === 'ACTIVE'}
                        onChange={(event) => setFieldValue('status', event.target.checked ? 'ACTIVE' : 'INACTIVE')}
                        color="primary"
                      />
                    }
                  />
                </Stack>
              </Grid2>
              {/* Nút Submit */}
              <Button type="submit" variant="contained" color="primary" disabled={isSubmitting}>
                {userId ? 'Chỉnh sửa' : 'Thêm mới'}
              </Button>
              <Button variant="contained" color="primary" onClick={handleBack}>
                Quay lại
              </Button>
            </Grid2>
          </Form>
        )}
      </Formik>
    </MainCard>
  );
};

export default RoleDialog;
