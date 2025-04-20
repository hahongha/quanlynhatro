// import { useState } from 'react';
// import ChangePasswordDialog from '../userProfile/ChangePasswordDialog';
// import { Button } from '@mui/material';

import PersonalInfoContent from '../userProfile/PersonalInfoContent';
import UserProfile from '../userProfile/userProfile';

// const Test = () => {
//   const [open, setOpen] = useState(false);

//   return (
//     <div>
//       <Button variant="contained" color="primary" onClick={() => setOpen(true)}>
//         Open Change Password Dialog
//       </Button>
//       <ChangePasswordDialog open={open} handleClose={() => setOpen(false)} />
//     </div>
//   );
// };

// export default Test;

function Test() {
  return (
    <>
      <UserProfile />
    </>
  );
}

export default Test;
