import { combineReducers } from 'redux';
import userReducer from './userReducer';
import roleReducer from './roleReducer';
import authorityReducer from './authorityReducer';
import roomTypeReducer from './roomTypeReducer';
import roomReducer from './roomReducer';
import renterReducer from './renterReducer';
import contractReducer from './contractReducer';
import contractMemberReducer from './contractMemberReducer';
import serviceReducer from './serviceReducer';
import imageReducer from './imageReducer';
import authReducer from './authReducer';

const RootReducer = combineReducers({
  auth: authReducer,
  user: userReducer,
  role: roleReducer,
  authority: authorityReducer,
  roomType: roomTypeReducer,
  room: roomReducer,
  renter: renterReducer,
  contract: contractReducer,
  contractMember: contractMemberReducer,
  service: serviceReducer,
  file: imageReducer
});

export default RootReducer;
