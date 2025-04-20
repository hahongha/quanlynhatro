import PropTypes from 'prop-types';
// material-ui
import Stack from '@mui/material/Stack';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
// project imports
import Dot from 'components/@extended/Dot';
import { IconButton } from '@mui/material';
import { DeleteOutlined, EditOutlined } from '@ant-design/icons';
import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { deleteUserRequest } from '../../redux/actions/userAction';
import CustomDialog from '../../components/CustomDialog';
import RoleDialog from './RoleDialog';

const headCells = [
  {
    id: 'id',
    align: 'left',
    disablePadding: true,
    label: 'Tên tài khoản'
  },
  {
    id: 'email',
    align: 'left',
    disablePadding: false,
    label: 'Email'
  },
  {
    id: 'status',
    align: 'left',
    disablePadding: false,
    label: 'Trạng thái'
  },
  {
    id: 'roleName',
    align: 'left',
    disablePadding: false,
    label: 'VaiTrò'
  }
];

// ==============================|| User TABLE - HEADER ||============================== //

function UserTableHead() {
  return (
    <TableHead>
      <TableRow>
        {headCells.map((headCell) => (
          <TableCell key={headCell.id} align={headCell.align} padding={headCell.disablePadding ? 'none' : 'normal'}>
            {headCell.label}
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
}

function UserStatus({ status }) {
  let color;
  let title;

  switch (status) {
    case 'ACTIVE':
      color = 'success';
      title = 'ACTIVE';
      break;
    case 'INACTIVE':
      color = 'error';
      title = 'INACTIVE';
      break;
    default:
      color = 'primary';
      title = 'None';
  }

  return (
    <Stack direction="row" sx={{ gap: 1, alignItems: 'center' }}>
      <Dot color={color} />
      <Typography>{title}</Typography>
    </Stack>
  );
}

// ==============================|| User TABLE ||============================== //

export default function RoleTable({ userData }) {
  const [open, setOpen] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);
  const [userId, setUserId] = useState('');
  const dispatch = useDispatch();

  const initialUser = {
    userId: '',
    userName: '',
    email: '',
    status: '',
    role: {
      id: '',
      roleName: ''
    }
  };
  const [user, setUser] = useState(initialUser);
  const handleOpen = (row) => {
    handleSetUser(row);
    setOpen(true);
  };

  const handleSetUser = (row) => {
    setUser(row);
    setUserId(user.userId);
  };

  const handleClose = () => {
    setOpen(false);
    setUserId('');
    setUser(initialUser);
    setOpenDelete(false);
  };

  const handleOpenDelete = (userId1) => {
    setOpenDelete(true);
    setUserId(userId1);
  };

  const handleDelete = () => {
    dispatch(deleteUserRequest(userId));
    handleClose();
  };

  return (
    <Box>
      <TableContainer
        sx={{
          width: '100%',
          overflowX: 'auto',
          position: 'relative',
          display: 'block',
          maxWidth: '100%',
          '& td, & th': { whiteSpace: 'nowrap' }
        }}
      >
        <Table aria-labelledby="tableTitle">
          <UserTableHead />
          <TableBody>
            {userData.map((row, index) => {
              const labelId = `enhanced-table-checkbox-${index}`;
              return (
                <TableRow hover role="checkbox" tabIndex={-1} key={row.userId}>
                  <TableCell>{row.userName}</TableCell>
                  <TableCell align="left">{row.email}</TableCell>
                  <TableCell align="left">
                    <UserStatus status={row.status} />
                  </TableCell>
                  <TableCell align="left">{row.role.roleName !== null ? row.role.roleName : 'Giá trị mặc định'}</TableCell>
                  <TableCell>
                    <IconButton onClick={() => handleOpen(row)}>
                      <EditOutlined sx={{ mr: 2 }} />
                    </IconButton>
                    <IconButton onClick={() => handleOpenDelete(row.userId)}>
                      <DeleteOutlined sx={{ mr: 2 }} />
                    </IconButton>
                  </TableCell>
                </TableRow>
              );
            })}
          </TableBody>
        </Table>
        <CustomDialog
          open={openDelete}
          handleClose={handleClose}
          handleDelete={handleDelete}
          title={'Xóa tài khoản'}
          content={'Bạn có chắc chắn muốn xóa tài khoản này?'}
        />

        <CustomDialog
          title={user.userId ? 'Chỉnh sửa tài khoản' : 'Thêm tài khoản'}
          open={open}
          handleClose={handleClose}
          handleDelete={handleDelete}
          content={
            <RoleDialog
              open={open}
              role={user.role}
              handleClose={handleClose}
              userId={user.userId}
              userName={user.userName}
              status={user.status}
              email={user.email}
            />
          }
        />
      </TableContainer>
    </Box>
  );
}
