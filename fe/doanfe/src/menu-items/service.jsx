// assets
import { CheckCircleOutlined } from '@ant-design/icons';
// icons
const icons = {
  CheckCircleOutlined
};

// ==============================|| MENU ITEMS - DASHBOARD ||============================== //

const service = {
  id: 'service',
  title: 'Dịch vụ',
  type: 'group',
  children: [
    {
      id: 'service',
      title: 'Dịch vụ',
      type: 'item',
      url: '/service',
      icon: icons.CheckCircleOutlined,
      breadcrumbs: false
    }
  ]
};

export default service;
