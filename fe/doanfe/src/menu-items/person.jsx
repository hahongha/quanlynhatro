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

const person = {
  id: 'contract',
  title: 'Hợp đồng',
  type: 'group',
  children: [
    {
      id: 'renter',
      title: 'Người thuê',
      type: 'item',
      url: '/renter',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'contractmember',
      title: 'Người thuê cùng',
      type: 'item',
      url: '/contractMember',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'contract',
      title: 'Hợp đồng',
      type: 'item',
      url: '/contract',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    }
  ]
};

export default person;
