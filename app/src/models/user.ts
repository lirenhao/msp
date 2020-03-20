import { Effect } from 'dva';
import { Reducer } from 'redux';
import { getMerInfo, getLogout } from '@/services/user';

export interface MerInfo {
  merNo?: string;
  merName?: string;
  // 0-请求中; 00-商户正常; 01-同意协议; 02-重置密码; 03-密码到期
  status?: '0' | '00' | '01' | '02' | '03';
}

export interface UserModelState {
  merInfo: MerInfo;
}

export interface UserModelType {
  namespace: 'user';
  state: UserModelState;
  effects: {
    fetchMerInfo: Effect;
    fetchLogout: Effect;
  };
  reducers: {
    setMerInfo: Reducer<UserModelState>;
  };
}

const UserModel: UserModelType = {
  namespace: 'user',

  state: {
    merInfo: {
      status: '0'
    },
  },

  effects: {
    *fetchMerInfo(_, { call, put }) {
      try {
        const response = yield call(getMerInfo);
        yield put({
          type: 'setMerInfo',
          payload: response,
        });
      } catch (error) {
        // TODO 获取商户信息失败
      }
    },
    *fetchLogout(_, { call, put }) {
      try {
        yield call(getLogout);
      } catch (error) {
        // TODO 获取商户信息失败
      }
    }
  },

  reducers: {
    setMerInfo(state, action) {
      return {
        ...state,
        merInfo: action.payload || {},
      };
    },
  },
};

export default UserModel;
