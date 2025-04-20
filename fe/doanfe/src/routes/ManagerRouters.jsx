import ManagerLayout from '../layout/Manager';
import { lazy } from 'react';

// project imports
import Loadable from 'components/Loadable';
const DashboardDefault = Loadable(lazy(() => import('pages/dashboard/default')));
const Color = Loadable(lazy(() => import('pages/component-overview/color')));
const Typography = Loadable(lazy(() => import('pages/component-overview/typography')));
const Shadow = Loadable(lazy(() => import('pages/component-overview/shadows')));
// ==============================|| MAIN ROUTING ||============================== //

const ManagerRouters = {
  path: '/manager',
  element: <ManagerLayout />,
  children: [
    {
      path: '/manager/color',
      element: <Color />
    },
    {
      path: '/manager/dashboard',
      element: <DashboardDefault />
    },
    {
      path: '/manager/typography',
      element: <Typography />
    },
    {
      path: '/manager/shadow',
      element: <Shadow />
    }
  ]
};

export default ManagerRouters;
