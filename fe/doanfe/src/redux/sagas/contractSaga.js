import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { put, call, takeEvery } from 'redux-saga/effects';
import {
  addContractFail,
  addContractSuccess,
  deleteContractFail,
  deleteContractSuccess,
  getAllContractFail,
  getAllContractSuccess,
  searchContractFail,
  searchContractRequest,
  searchContractSuccess,
  updateContractFail,
  updateContractSuccess
} from 'redux/actions/contractAction';
import {
  ADD_CONTRACT_REQUEST,
  ALL_CONTRACT_REQUEST,
  DELETE_CONTRACT_REQUEST,
  SEARCH_CONTRACT_REQUEST,
  UPDATE_CONTRACT_REQUEST
} from '../constaints/contractConstaints';
import { addContract, deleteContract, getAllContract, searchContract, updateContract } from '../api/contractApi';

function* getAllContractSaga() {
  try {
    const response = yield call(getAllContract);
    if (response?.data.code === '200') {
      yield put(getAllContractSuccess(response?.data));
    } else {
      yield put(getAllContractFail(response?.data?.message));
    }
  } catch (error) {
    yield put(getAllContractFail('Có lỗi xảy ra khi gọi API'));
  }
}

function* searchContractSaga(action) {
  try {
    const response = yield call(searchContract, action.payload);
    if (response?.data.code === '200') {
      yield put(searchContractSuccess(response?.data));
    } else {
      yield put(searchContractFail(response?.data?.message));
    }
  } catch (error) {
    yield put(searchContractFail('Có lỗi xảy ra khi gọi API'));
  }
}

function* addContractSaga(action) {
  try {
    const response = yield call(addContract, action.payload);
    if (response?.data?.code === '200') {
      yield put(addContractSuccess(response?.data));
      toast.success('Thêm vai trò thành công!');
      yield put(
        searchContractRequest({
          page: 0,
          size: 5,
          value: `%%`
        })
      );
    } else {
      yield put(addContractFail('Thêm vai trò thất bại!'));
      toast.error('Thêm vai trò thất bại!');
    }
  } catch (error) {
    yield put(addContractFail('Có lỗi xảy ra khi gọi API'));
    toast.error('Có lỗi xảy ra khi gọi API');
  }
}

function* deleteContractSaga(action) {
  try {
    const response = yield call(deleteContract, action.payload);
    if (response?.data?.code === '200') {
      yield put(deleteContractSuccess());
      toast.success('Xóa vai trò thành công!');
      yield put(
        searchContractRequest({
          page: 0,
          size: 5,
          value: `%%`
        })
      );
    } else {
      yield put(deleteContractFail('Xóa vai trò thất bại'));
      toast.error('Xóa vai trò thất bại');
    }
  } catch (error) {
    yield put(deleteContractFail('Có lỗi xảy ra khi gọi API'));
    toast.error('Có lỗi xảy ra khi gọi API');
  }
}

function* updateContractSaga(action) {
  try {
    const response = yield call(updateContract, action.payload);
    if (response?.data?.code === '200') {
      yield put(updateContractSuccess(response?.data));
      toast.success('Cập nhật vai trò thành công!');
      yield put(
        searchContractRequest({
          page: 0,
          size: 5,
          value: `%%`
        })
      );
    } else {
      yield put(updateContractFail('Cập nhật vai trò thất bại'));
      toast.error('Cập nhật vai trò thất bại!');
    }
  } catch (error) {
    yield put(updateContractFail('Có lỗi xảy ra khi gọi API'));
    toast.error('Có lỗi xảy ra khi gọi API');
  }
}

export default function* contractSaga() {
  yield takeEvery(ALL_CONTRACT_REQUEST, getAllContractSaga);
  yield takeEvery(ADD_CONTRACT_REQUEST, addContractSaga);
  yield takeEvery(DELETE_CONTRACT_REQUEST, deleteContractSaga);
  yield takeEvery(UPDATE_CONTRACT_REQUEST, updateContractSaga);
  yield takeEvery(SEARCH_CONTRACT_REQUEST, searchContractSaga);
}
