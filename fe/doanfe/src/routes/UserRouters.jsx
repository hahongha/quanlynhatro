import UserLayout from '../layout/User';
import { lazy } from 'react';

// project imports
import Loadable from 'components/Loadable';
// import UserLayout from 'layout/User';
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard/default')));
const Color = Loadable(lazy(() => import('pages/component-overview/color')));
const Typography = Loadable(lazy(() => import('pages/component-overview/typography')));
const Shadow = Loadable(lazy(() => import('pages/component-overview/shadows')));
// ==============================|| MAIN ROUTING ||============================== //

const UserRouters = {
  path: '/user',
  element: <UserLayout />,
  children: [
    {
      path: '/user/color',
      element: <Color />
    },
    {
      path: '/user/dashboard',
      element: <DashboardDefault />
    },
    {
      path: '/user/typography',
      element: <Typography />
    },
    {
      path: '/user/shadow',
      element: <Shadow />
    }
  ]
};

export default UserRouters;
