import { Box, Button, Grid2, MenuItem, Popover, TablePagination, Typography } from '@mui/material';
import MainCard from 'components/MainCard';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import { deleteUserRequest, getAllUserRequest, searchUserRequest } from '../../redux/actions/userAction';
import SearchBar from '../../components/SearchBar';
import { useNavigate } from 'react-router';
import AuthorityTable from './AuthorityTable';
function Authority() {
  const [keyword, setKeyword] = useState('');
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(5);
  const usersData = useSelector((state) => state.user.users);
  const totalRecords = useSelector((state) => state.user.totalRecords);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleInsert = () => {
    navigate('/user/insert');
  };
  useEffect(() => {
    dispatch(
      searchUserRequest({
        page,
        size: rowsPerPage,
        value: `%${keyword}%`
      })
    );
  }, [dispatch, page, rowsPerPage, keyword]);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleSearchChange = (event) => {
    setKeyword(event.target.value);
  };

  const notFound = !usersData.length && !!keyword;
  return (
    <MainCard title="Danh sách tài khoản" titleColor="primary.dark">
      <Grid2 container spacing={2} alignItems="center" mb={4}>
        <Grid2 size={6}>
          <SearchBar keyword={keyword} onChange={handleSearchChange} />
        </Grid2>
        <Grid2 size={6} container justifyContent="flex-end">
          <Button variant="contained" color="primary" onClick={handleInsert}>
            Tạo tài khoản mới
          </Button>
        </Grid2>
      </Grid2>
      <AuthorityTable userData={usersData}></AuthorityTable>
      <TablePagination
        page={page}
        component="div"
        count={totalRecords}
        rowsPerPage={rowsPerPage}
        onPageChange={handleChangePage}
        rowsPerPageOptions={[1, 2, 3, 5, 10, 25, 50, 100]}
        onRowsPerPageChange={handleChangeRowsPerPage}
        labelRowsPerPage="Số hàng mỗi trang"
        labelDisplayedRows={({ from, to, count }) => `${from}-${to} ${`trong`} ${count !== -1 ? count : `more than ${to}`}`}
        backIconButtonProps={{
          'aria-label': 'Previous Page'
        }}
        nextIconButtonProps={{
          'aria-label': 'Next Page'
        }}
      />
    </MainCard>
  );
}

export default Authority;
