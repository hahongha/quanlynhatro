import { createBrowserRouter } from 'react-router-dom';

// project imports
import MainRoutes from './MainRoutes';
import LoginRoutes from './LoginRoutes';
import UserRouters from './UserRouters';
import ManagerRouters from './ManagerRouters';

// ==============================|| ROUTING RENDER ||============================== //

const router = createBrowserRouter([MainRoutes, LoginRoutes, UserRouters, ManagerRouters], {
  basename: import.meta.env.VITE_APP_BASE_NAME
});

export default router;
