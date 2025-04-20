// assets
import { DashboardOutlined } from '@ant-design/icons';
import PeopleIcon from '@mui/icons-material/People';
import AndroidIcon from '@mui/icons-material/Android';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import SecurityIcon from '@mui/icons-material/Security';
// icons
const icons = {
  DashboardOutlined,
  PeopleIcon,
  AndroidIcon,
  SecurityIcon,
  ManageAccountsIcon
};

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //

const admin = {
  id: 'account',
  title: 'Tài khoản',
  type: 'group',
  children: [
    {
      id: 'user',
      title: 'Tài khoản',
      type: 'item',
      url: '/user',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'role',
      title: 'Vai trò',
      type: 'item',
      url: '/role',
      icon: icons.PeopleIcon,
      breadcrumbs: false
    },
    {
      id: 'authority',
      title: 'Quyền hạn',
      type: 'item',
      url: '/authority',
      icon: icons.SecurityIcon,
      breadcrumbs: false
    }
  ]
};

export default admin;
