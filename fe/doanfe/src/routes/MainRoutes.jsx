import { lazy } from 'react';

// project imports
import Loadable from 'components/Loadable';
import DashboardLayout from 'layout/Dashboard';
import User from '../pages/user/User';
import Test from '../pages/test/Test';
import UserDialog from '../pages/user/UserDialog';
import Role from '../pages/role/Role';
import RoleDialog from '../pages/role/RoleDialog';
import Authority from '../pages/authority/Authority';
import RoomType from '../pages/roomType/roomType';
import Room from '../pages/room/Room';
import Renter from '../pages/renter/Renter';
import ContractMember from '../pages/contract_member/ContractMember';
import Contract from '../pages/contract/Contract';
import Service from '../pages/service/Service';
import UserTest from '../pages/userPage/UserTest';
import UserContractMember from '../pages/userPage/UserContractMember';
import UserContract from '../pages/userPage/UserContract';
import UserRenter from '../pages/userPage/UserRenter';
import UserDashBoard from '../pages/userPage/userDashBoard';
import UserBill from '../pages/userPage/UserBill';
import UserComplaint from '../pages/userPage/UserComplaint';
import UserPayment from '../pages/userPage/UserPayment';
import UserRoom from '../pages/userPage/UserRoom';
import Profile from '../pages/dashboard/Profile.jsx';
import UserEditForm from '../pages/dashboard/EditProfile.jsx';
// render- Dashboard
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard/default')));

// render - color
const Color = Loadable(lazy(() => import('pages/component-overview/color')));
const Typography = Loadable(lazy(() => import('pages/component-overview/typography')));
const Shadow = Loadable(lazy(() => import('pages/component-overview/shadows')));

// render - sample page
const SamplePage = Loadable(lazy(() => import('pages/extra-pages/sample-page')));

// ==============================|| MAIN ROUTING ||============================== //

const MainRoutes = {
  path: '/',
  element: <DashboardLayout />,
  children: [
    {
      path: '/',
      element: <DashboardDefault />
    },
    {
      path: 'dashboard',
      children: [
        {
          path: 'default',
          element: <DashboardDefault />
        }
      ]
    },
    {
      path: 'typography',
      element: <Typography />
    },
    {
      path: 'color',
      element: <Color />
    },
    {
      path: 'shadow',
      element: <Shadow />
    },
    {
      path: 'sample-page',
      element: <SamplePage />
    },
    {
      path: '/user',
      element: <User />
    },
    {
      path: '/user/insert',
      element: <UserDialog />
    },
    {
      path: '/test',
      element: <Test />
    },
    {
      path: '/role',
      element: <Role />
    },
    {
      path: '/role/insert',
      element: <RoleDialog />
    },
    {
      path: '/authority',
      element: <Authority />
    },
    {
      path: '/roomType',
      element: <RoomType />
    },
    {
      path: '/renter',
      element: <Renter />
    },
    {
      path: '/contractMember',
      element: <ContractMember />
    },
    {
      path: '/contract',
      element: <Contract />
    },
    {
      path: '/room',
      element: <Room />
    },
    {
      path: '/service',
      element: <Service />
    },
    {
      path: '/profile',
      element: <Profile />
    },
    {
      path: '/user/profile',
      element: <UserRenter />
    },
    {
      path: '/user/dashboard',
      element: <UserDashBoard />
    },
    {
      path: '/user/bill',
      element: <UserBill />
    },
    {
      path: '/user/complaint',
      element: <UserComplaint />
    },
    {
      path: '/user/contract',
      element: <UserContract />
    },
    {
      path: '/user/payment',
      element: <UserPayment />
    },
    {
      path: '/user/contractMember',
      element: <UserContractMember />
    },
    {
      path: '/user/room',
      element: <UserRoom />
    },
    {
      path: '/user/test',
      element: <UserTest />
    }
  ]
};

export default MainRoutes;
