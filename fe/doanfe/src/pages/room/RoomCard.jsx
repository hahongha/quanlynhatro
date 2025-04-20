import { Box, Card, CardContent, CardMedia, Chip, Typography } from '@mui/material';

const RoomCard = ({ room, onClick }) => {
  return (
    <Card
      sx={{ maxWidth: 400, boxShadow: 3, m: 2, backgroundColor: room.status === 'ACTIVE' ? '#ffcccc' : 'inherit' }}
      onClick={() => onClick(room)}
    >
      <CardMedia component="img" height="300" image={room.images[0]} alt={room.description} />
      <CardContent>
        <Typography variant="h5" gutterBottom>
          {room.room_Type.name} - #{room.roomNumber}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {room.description}
        </Typography>
        <Box mt={2}>
          <Chip label={`Trạng thái: ${room.status}`} />
          <Chip label={`Diện tích: ${room.room_Type.size}m²`} sx={{ ml: 1 }} />
        </Box>
        {room.getNameRenter ? (
          <Typography variant="h5" gutterBottom>
            Người thuê: {room.getNameRenter}
          </Typography>
        ) : (
          ''
        )}
        <Box mt={2}>
          <Typography variant="body2">Chỉ số điện: {room.electricIndex}</Typography>
          <Typography variant="body2">Chỉ số nước: {room.waterIndex}</Typography>
        </Box>
      </CardContent>
    </Card>
  );
};

export default RoomCard;
