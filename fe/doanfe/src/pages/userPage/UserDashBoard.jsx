import { Grid2 } from '@mui/material';
import { display } from '@mui/system';
import MainCard from 'components/MainCard';
import UserRenter from './UserRenter';
import UserContractMember from './UserContractMember';
function UserDashBoard() {
  return (
    <MainCard>
      <Grid2 container sx={{ p: 0, m: 0 }}>
        <Grid2 size={6} sx={{ p: 0, m: 0 }}>
          <UserRenter sx={{ p: 0, m: 0 }} />
        </Grid2>
        <Grid2 size={6} sx={{ p: 0, m: 0 }}>
          <UserContractMember />
        </Grid2>
      </Grid2>
    </MainCard>
  );
}

export default UserDashBoard;
