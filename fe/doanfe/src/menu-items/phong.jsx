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

const phong = {
  id: 'room',
  title: 'Phòng',
  type: 'group',
  children: [
    {
      id: 'roomType',
      title: 'Loại Phòng',
      type: 'item',
      url: '/roomType',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'room',
      title: 'Phòng',
      type: 'item',
      url: '/room',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    }
  ]
};

export default phong;
