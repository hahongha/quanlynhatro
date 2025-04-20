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

const renter = {
  id: 'renter',
  title: 'Khách thuê',
  type: 'group',
  children: [
    {
      id: 'testUser',
      title: 'test',
      type: 'item',
      url: '/user/test',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'roomUser',
      title: 'Phòng',
      type: 'item',
      url: '/user/room',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'contractMemberUser',
      title: 'Người thuê cùng',
      type: 'item',
      url: '/user/contractMember',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'paymentUser',
      title: 'Thanh toán',
      type: 'item',
      url: '/user/payment',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'contractUser',
      title: 'Hợp đồng',
      type: 'item',
      url: '/user/contract',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'complaintUser',
      title: 'Yêu cầu, khiếu nại',
      type: 'item',
      url: '/user/complaint',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'billUser',
      title: 'Hóa đơn',
      type: 'item',
      url: '/user/bill',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'dashBoard',
      title: 'Trang chủ',
      type: 'item',
      url: '/user/dashboard',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    },
    {
      id: 'renter',
      title: 'Thông tin khách thuê',
      type: 'item',
      url: '/user/profile',
      icon: icons.ManageAccountsIcon,
      breadcrumbs: false
    }
  ]
};

export default renter;
