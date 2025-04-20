import { useEffect, useState } from 'react';
import { Button, Grid2, TablePagination } from '@mui/material';
import RoomCard from '../room/RoomCard';
import { useDispatch, useSelector } from 'react-redux';
import { addRoomRequest, searchRoomRequest, updateRoomRequest } from '../../redux/actions/roomAction';
import RoomDialog from '../room/RoomDialog';
import MainCard from 'components/MainCard';
import SearchBar from '../../components/SearchBar';
function Room() {
  const [keyword, setKeyword] = useState('');
  const [roomId, setRoomId] = useState('');
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(3);
  const rooms = useSelector((state) => state.room.rooms);
  const totalRecords = useSelector((state) => state.room.totalRecords);
  const dispatch = useDispatch();
  const [deleteDialog, setDeleteDialog] = useState(false);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [open, setOpen] = useState(false);

  useEffect(() => {
    dispatch(
      searchRoomRequest({
        page,
        size: rowsPerPage,
        value: `%${keyword}%`
      })
    );
  }, [dispatch, page, rowsPerPage, keyword]);
  const handleOpen = (room) => {
    setSelectedRoom({ ...room });
    setRoomId(room.id);
    setOpen(true);
  };
  const handleSearchChange = (event) => {
    setKeyword(event.target.value);
  };
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleClose = () => {
    setRoomId('');
    setSelectedRoom(null);
    setDeleteDialog(false);
    setOpen(false);
  };

  const handleSave = (updatedRoom) => {
    console.log('Lưu phòng: ', updatedRoom);
    {
      roomId ? dispatch(updateRoomRequest(updatedRoom)) : dispatch(addRoomRequest(updatedRoom));
    }
    setOpen(false);
    setSelectedRoom(null);
  };

  return (
    <MainCard title={'Danh sách các phòng'}>
      <Grid2 container spacing={2} alignItems="center">
        {/* Ô tìm kiếm */}
        <Grid2 size={{ xs: 12, sm: 4, md: 3 }}>
          <SearchBar value={keyword} onChange={handleSearchChange} />
        </Grid2>

        {/* Nút tạo phòng mới */}
        <Grid2 size={{ xs: 12, sm: 12, md: 3 }}>
          <Button variant="contained" color="primary" onClick={handleOpen}>
            Tạo phòng mới
          </Button>
        </Grid2>
      </Grid2>
      <Grid2 container justifyContent="center">
        {rooms.map((room) => (
          <RoomCard key={room.id} room={room} onClick={handleOpen} />
        ))}
      </Grid2>
      {selectedRoom && <RoomDialog open={open} room={selectedRoom} handleClose={handleClose} handleSave={handleSave} />}
      <TablePagination
        page={page}
        component="div"
        count={totalRecords}
        rowsPerPage={rowsPerPage}
        onPageChange={handleChangePage}
        rowsPerPageOptions={[1, 2, 3, 6, 9, 12, 18, 30]}
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

export default Room;
