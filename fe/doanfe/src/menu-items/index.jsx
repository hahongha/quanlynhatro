// project import
import dashboard from './dashboard';
import pages from './page';
import utilities from './utilities';
import support from './support';
import admin from './admin';
import phong from './phong';
import person from './person';
import service from './service';
import renter from './renter';

// ==============================|| MENU ITEMS ||============================== //

const menuItems = {
  items: [renter, service, admin, phong, person, dashboard, pages, utilities, support]
};

export default menuItems;
